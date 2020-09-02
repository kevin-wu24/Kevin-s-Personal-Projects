package nflsim;

public class Game {
	private double homeScore,awayScore,homeLast,awayLast;
    private String homeName, awayName;
    /**
     * Constructor for objects of class Game
     */
    public Game()
    {
        // initialise instance variables
        homeScore = 0;
        awayScore = 0;
    }
    
    public void setNames(String m, String n)
    {
        homeName = m;
        awayName = n;
    }
    
    public String EstProbChanceWinning(double favML, double OpenSpread)
    {
        // put your code here
        String ans = "ML %: " + favML/(favML-100.0)*100.0 + "% ATS%: " + (-0.0303*OpenSpread + 0.5)*100 + "%";
        return ans;
    }
    
    public String simGame(Team Home, Team Away, int WeekNum) throws Exception
    {
    	LastYearPoints l = new LastYearPoints();
    	l.setPoints(homeName);
    	homeLast = l.getPoints(homeName);
    	l.setPoints(awayName);
    	awayLast = l.getPoints(awayName);
    	
    	if(WeekNum == 1)
        {       	
        	return homeName + " " + Math.round(homeLast+3*Home.calcEffWinPct()+1.5) +"     " + awayName + " " + Math.round(awayLast);
        }
    	else if(WeekNum <= 4)
    	{
    		return homeName + " " + Math.round((1.0/(double)WeekNum)*homeLast+Math.log(WeekNum)/Math.log(4)*Home.calcPoints()+3*Home.calcEffWinPct()+1.5) + "     " + awayName + " " + Math.round((1.0/(double)WeekNum)*awayLast+Math.log(WeekNum)/Math.log(4)*Away.calcPoints());
    	}
    	else
    	{
    		return homeName + " " + Math.round(Home.calcPoints()+3*Home.calcEffWinPct()+1.5) + "     " + awayName + " " + Math.round(Away.calcPoints());
    	}
    }
    
    public String avgGameWithEstSpread(Team Home, Team Away, int WeekNum) throws Exception
    {
    	if(WeekNum == 1)
        {
        	
        	return homeName + " " + Math.round(homeLast+3*Home.calcEffWinPct()+1.5) +"     " + awayName + " " + Math.round(awayLast)+ "\n -" + Math.round((Math.max(homeLast+3*Home.calcEffWinPct()+1.5,awayLast) - Math.min(homeLast+3*Home.calcEffWinPct()+1.5,awayLast))*2.0)/2.0;
        }
    	else if(WeekNum <= 4)
    	{
    		return homeName + " " + Math.round((1.0/(double)WeekNum)*homeLast+Math.log(WeekNum)/Math.log(4)*Home.calcAvgPoints()+3*Home.calcEffWinPct()+1.5) + "     " + awayName + " " + Math.round((1.0/(double)WeekNum)*awayLast+Math.log(WeekNum)/Math.log(4)*Away.calcAvgPoints())+ "\n -" + Math.round((Math.max((1.0/(double)WeekNum)*homeLast+Math.log(WeekNum)/Math.log(4)*Home.calcAvgPoints()+3*Home.calcEffWinPct()+1.5,(1.0/(double)WeekNum)*awayLast+Math.log(WeekNum)/Math.log(4)*Away.calcAvgPoints()) - Math.min((1.0/(double)WeekNum)*homeLast+Math.log(WeekNum)/Math.log(4)*Home.calcAvgPoints()+3*Home.calcEffWinPct()+1.5,(1.0/(double)WeekNum)*awayLast+Math.log(WeekNum)/Math.log(4)*Away.calcAvgPoints()))*2.0)/2.0;
    	}
    	return homeName + " " + Math.round(Home.calcAvgPoints()+3*Home.calcEffWinPct()+1.5) + "    " + awayName + " " + Math.round(Away.calcAvgPoints()) + "\n -" + Math.round((Math.max(Home.calcAvgPoints()+3*Home.calcEffWinPct()+1.5,Away.calcAvgPoints()) - Math.min(Home.calcAvgPoints()+3*Home.calcEffWinPct()+1.5,Away.calcAvgPoints()))*2.0)/2.0;
    }
}
