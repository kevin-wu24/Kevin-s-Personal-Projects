package nflsim;
import java.io.BufferedReader;
import java.io.FileReader;

public class SoSRetriever extends GameSim
{
	double[][] SOS = new double[3][32];
	String[][] teams = new String[4][32];
	
	public static void main(String[] args) throws Exception
	{
		SoSRetriever sos = new SoSRetriever();
		sos.setSOSInfo();
		sos.calcSOS();

	}
	
	public void setSOSInfo()
	{
		GameSim g = new GameSim();
		g.setYearAndInfo();
		for(int i=0; i<4;i++)
		{
			for(int j=0;j<32;j++)
			{
				if(g.TeamInfo[i][j].contains("NY"))
				{
					teams[i][j] = g.TeamInfo[i][j];
					teams[i][j].replaceAll("NY", "New York");
				}
				if(g.TeamInfo[i][j].contains("LA"))
				{
					teams[i][j] = g.TeamInfo[i][j];
					teams[i][j].replaceAll("LA", "Los Angeles");
				}
				teams[i][j] = g.TeamInfo[i][j];
			}
		}
	}
	
	public void setSOSYear(String y)
	{
		
		GameSim g = new GameSim();
		g.setYearAndInfo();
		for(int i = 0; i < 32;i++)
		{
			teams[1][i].replaceAll(g.year, y);
		}
	}
	
	public void calcSOS() throws Exception
	{
			
		for(int j = 0; j < 32; j ++)
		{
			String str = "";
			String gameswon = "";
			String gameslost = "";
			String gamestied = "";
			BufferedReader br = new BufferedReader(new FileReader(teams[0][j]+".txt"));
	        while((str = br.readLine()) != null)
	        {
	        	if(str.indexOf("<strong>Record:</strong>") != -1)
	        	{
	        		for(int i = str.indexOf("<strong>Record:</strong>"); i < str.indexOf("<strong>Record:</strong>")+27; i++)
	                {
	                    if(Character.isDigit(str.charAt(i)) )
	                        gameswon = gameswon + str.substring(i,i+1);
	                }
	                for(int i = str.indexOf("<strong>Record:</strong>")+27; i < str.indexOf("<strong>Record:</strong>")+29; i++)
	                {
	                    if(Character.isDigit(str.charAt(i)) )  
	                        gameslost = gameslost + str.substring(i,i+1);
	          
	                }
	                for(int i = str.indexOf("<strong>Record:</strong>")+29; i < str.indexOf("<strong>Record:</strong>")+31; i++)
	                {
	                    if(Character.isDigit(str.charAt(i)) )  
	                        gamestied = gamestied + str.substring(i,i+1);
	          
	                }
	        	}
	        }
	        SOS[0][j] = Double.parseDouble(gameswon);
	        SOS[1][j] = Double.parseDouble(gameswon)+Double.parseDouble(gameslost)+Double.parseDouble(gamestied);
	        
		}
		
		for(int i = 0; i < 32; i++)
		{
			String str = "";
			double totalWins = 0;
			double totalGames = 0;
			BufferedReader br = new BufferedReader(new FileReader(teams[0][i]+".txt"));
	        while((str = br.readLine()) != null)
	        {
	        	if(str.indexOf("stat=\"team_record\" >") != -1 && str.substring(str.indexOf("stat=\"team_record\" >"),str.indexOf("stat=\"team_record\" >")+25).contains("-"))
	        	{
	        		for(int j = 0; j < 32; j++)
	        		{
	        			if(str.contains(teams[2][j]))
	        			{
	        				totalWins+= SOS[0][j];
	        				totalGames+= SOS[1][j];
	        			}
	        		}
	        	}
	        }
	        SOS[2][i] = totalWins/totalGames;
		}
	}
	
	public double getSOS(String TeamName)
	{
		for(int i = 0; i <32; i ++)
		{
			if(teams[0][i].equals(TeamName))
				return SOS[2][i];
		}
		return 0.0;
	}
	
}
