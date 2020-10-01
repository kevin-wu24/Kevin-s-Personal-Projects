package nflsim;

public class Game {
	private double homeLast, awayLast;
    private String homeName, awayName;

    public Game()
    {
        homeLast = 0;
        awayLast = 0;
    }
    
    public void setNames(String m, String n)
    {
        homeName = m;
        awayName = n;
    }
    
    public String EstProbChanceWinning(double favML, double OpenSpread)
    {
        return "ML %: " + favML/(favML-100.0)*100.0 + "% ATS%: " + (-0.0303*OpenSpread + 0.5)*100 + "%";
    }
    
    public String simGame(Team Home, Team Away, int WeekNum) throws Exception
    {   
    	if(WeekNum < 4)
    	{	
    		LastYearPoints lastYearPoints = new LastYearPoints();
    		lastYearPoints.setPoints(homeName);
    		homeLast = lastYearPoints.getPoints(homeName);
    		lastYearPoints.setPoints(awayName);
    		awayLast = lastYearPoints.getPoints(awayName);
    	}  						
    	return homeName + " " + calcScore(Home, WeekNum, "home") + "     " + awayName + " " + calcScore(Away, WeekNum, "away");
    }
    
    public String avgGameWithEstSpread(Team Home, Team Away, int WeekNum) throws Exception
    {
    	return homeName + " " + calcAvgScore(Home, WeekNum, "home") + "     " + awayName + " " + calcAvgScore(Away, WeekNum, "away")
    		+ "\n -" + Math.round((Math.max(calcAvgScore(Home, WeekNum, "home"),calcAvgScore(Away, WeekNum, "away")) - Math.min(calcAvgScore(Home, WeekNum, "home"),calcAvgScore(Away, WeekNum, "away")))*2.0)/2.0;
    }
    
    public double calcScore(Team inputTeam, int WeekNum, String str) throws Exception
    {
    	if(str.equals("home"))
    	{
        	if(WeekNum < 4)
        	{
        		if(WeekNum == 1)
        		{
        			return Math.round(homeLast+3*inputTeam.calcEffWinPct()+1.5);
        		}
        		else
        		{
        			return Math.round((1.0/(double)WeekNum)*homeLast+Math.log(WeekNum)/Math.log(4)*inputTeam.calcPoints()+3*inputTeam.calcEffWinPct()+1.5);
        		}
        	}
        	else
        	{
        		return Math.round(inputTeam.calcPoints()+3*inputTeam.calcEffWinPct()+1.5);
        	}
    	}
    	else if(str.equals("away"))
    	{
    		if(WeekNum < 4)
        	{
        		if(WeekNum == 1)
        		{
        			return Math.round(awayLast);
        		}
        		else
        		{
        			return Math.round((1.0/(double)WeekNum)*awayLast+Math.log(WeekNum)/Math.log(4)*inputTeam.calcPoints());
        		}
        	}
        	else
        	{
        		return Math.round(inputTeam.calcPoints());
        	} 	
    	}
    	else
    	{
    		return 0;
    	}
    }
    
    public double calcAvgScore(Team inputTeam, int WeekNum, String str) throws Exception 
    {
    	int sum = 0;
    	for(int i = 0; i < 1000; i++)
    	{
    		sum += calcScore(inputTeam, WeekNum, str);
    	}
    	return sum/1000;
    }
    
}
