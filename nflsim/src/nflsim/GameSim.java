package nflsim;
import java.io.File;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

public class GameSim 
{
	// After week 1, change year to new year
	String year = "2020";
	String previousYear = "2020";
	int week = 1;
	String[][] TeamInfo = {{"NE", "BUF", "MIA", "NYJ", "KC", "LV", "LAC", "DEN","BAL","PIT","CLE","CIN","HOU","IND","JAX","TEN","DAL","PHI","NYG","WAS","SF","SEA","LAR","ARI","GB","MIN","DET","CHI","NO","CAR","TB","ATL"},
            {"https://www.pro-football-reference.com/teams/nwe/"+year+".htm","https://www.pro-football-reference.com/teams/buf/"+year+".htm","https://www.pro-football-reference.com/teams/mia/"+year+".htm","https://www.pro-football-reference.com/teams/nyj/"+year+".htm","https://www.pro-football-reference.com/teams/kan/"+year+".htm","https://www.pro-football-reference.com/teams/rai/"+year+".htm","https://www.pro-football-reference.com/teams/sdg/"+year+".htm","https://www.pro-football-reference.com/teams/den/"+year+".htm",
        "https://www.pro-football-reference.com/teams/rav/"+year+".htm","https://www.pro-football-reference.com/teams/pit/"+year+".htm","https://www.pro-football-reference.com/teams/cle/"+year+".htm","https://www.pro-football-reference.com/teams/cin/"+year+".htm","https://www.pro-football-reference.com/teams/htx/"+year+".htm","https://www.pro-football-reference.com/teams/clt/"+year+".htm","https://www.pro-football-reference.com/teams/jax/"+year+".htm","https://www.pro-football-reference.com/teams/oti/"+year+".htm",
        "https://www.pro-football-reference.com/teams/dal/"+year+".htm","https://www.pro-football-reference.com/teams/phi/"+year+".htm","https://www.pro-football-reference.com/teams/nyg/"+year+".htm","https://www.pro-football-reference.com/teams/was/"+year+".htm","https://www.pro-football-reference.com/teams/sfo/"+year+".htm","https://www.pro-football-reference.com/teams/sea/"+year+".htm","https://www.pro-football-reference.com/teams/ram/"+year+".htm","https://www.pro-football-reference.com/teams/crd/"+year+".htm",
        "https://www.pro-football-reference.com/teams/gnb/"+year+".htm","https://www.pro-football-reference.com/teams/min/"+year+".htm","https://www.pro-football-reference.com/teams/det/"+year+".htm","https://www.pro-football-reference.com/teams/chi/"+year+".htm","https://www.pro-football-reference.com/teams/nor/"+year+".htm","https://www.pro-football-reference.com/teams/car/"+year+".htm","https://www.pro-football-reference.com/teams/tam/"+year+".htm","https://www.pro-football-reference.com/teams/atl/"+year+".htm"},
       {"New England", "Buffalo", "Miami", "NY Jets", "Kansas City", "Las Vegas", "LA Chargers", "Denver", "Baltimore", "Pittsburgh", "Cleveland", "Cincinnati", "Houston", "Indianapolis", "Jacksonville", "Tennessee", "Dallas", "Philadelphia", "NY Giants", "Washington", "San Francisco", "Seattle", "LA Rams", "Arizona", "Green Bay", "Minnesota","Detroit", "Chicago", "New Orleans", "Carolina", "Tampa Bay", "Atlanta"},
       {"Cam Newton", "Josh Allen", "Tua Tagovailoa", "Zach Wilson", "Patrick Mahomes", "Derek Carr", "Justin Herbert", "Teddy Bridgewater", "Lamar Jackson", "Ben Roethlisberger", "Baker Mayfield", "Joe Burrow", "Deshaun Watson", "Carson Wentz", "Trevor Lawrence", "Ryan Tannehill", "Dak Prescott", "Jalen Hurts", "Daniel Jones", "Ryan Fitzpatrick", "Jimmy Garoppolo", "Russel Wilson", "Matthew Stafford", "Kyler Murray", "Aaron Rodgers", "Kirk Cousins", "Jared Goff", "Andy Dalton", "Jameis Winston","Sam Darnold","Tom Brady","Matt Ryan"},
       {"Patriots", "Bills", "Dolphins", "Jets", "Chiefs", "Raiders", "Chargers", "Broncos", "Ravens", "Steelers", "Browns", "Bengals", "Texans", "Colts", "Jaguars", "Titans", "Cowboys", "Eagles", "Giants", "Football Team", "49ers", "Seahawks", "Rams", "Cardinals", "Packers", "Vikings", "Lions", "Bears", "Saints", "Panthers", "Buccaneers", "Falcons"}};;
	
	public void simGame() throws Exception
    {
        String HomeNameLonger = "";
        String AwayNameLonger = "";
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Home Team Name:");
        String HomeName = input.nextLine();
        System.out.println("Enter Away Team Name:");
        String AwayName = input.nextLine();
        System.out.println("Enter " + HomeName + " QB Name:");
        String HomeQBName = input.nextLine();
        System.out.println("Enter " + AwayName + " QB Name:");
        String AwayQBName = input.nextLine();
        
        System.out.println("Enter Vegas Opening Line ML (- number for favored team):");
        double OpeningML = input.nextDouble();
        System.out.println("Enter Vegas Opening Line ATS (- number for favored team):");
        double OpeningSpread = input.nextDouble();
        
        System.out.println("Enter week number (int):");
        week = input.nextInt();
     
        System.out.println("Enter " + HomeName + " Matchup History Under Current Coach (Wins/Games):");
        double HomeHistoryToWin = input.nextDouble();        
        System.out.println("Enter " + AwayName + " Matchup History Under Current Coach (Wins/Games):");
        double AwayHistoryToWin = input.nextDouble();
                
        File top = new File("TOP.txt");
        for(int i = 0; i < 32; i++)
        {
        	if(HomeName.equals(TeamInfo[0][i]))
        	{
        		HomeNameLonger = TeamInfo[2][i];
        	}
        	if(AwayName.equals(TeamInfo[0][i]))
        	{
        		AwayNameLonger = TeamInfo[2][i];
        	}
        }
        

        
	        SoSRetriever sos = new SoSRetriever();
	        sos.setSOSInfo();
	        sos.calcSOS();
	        double HomeSOS = sos.getSOS(HomeName);
	        double AwaySOS = sos.getSOS(AwayName);
	        double HomeGamesWon = sos.getGamesWon(HomeName);
	        double HomeGamesPlayed = sos.getTotalGamesPlayed(HomeName);
	        double AwayGamesWon = sos.getGamesWon(AwayName);
	        double AwayGamesPlayed = sos.getTotalGamesPlayed(AwayName);
	           
	        QB homeQB = createQB(HomeName, HomeQBName, HomeGamesPlayed);
	        QB awayQB = createQB(AwayName, AwayQBName, AwayGamesPlayed);
	        
	        Coach homeCoach = createCoach(HomeName, HomeGamesPlayed);
	        Coach awayCoach = createCoach(AwayName, AwayGamesPlayed);
	        
	        Team homeTeam = createTeam(HomeName, HomeNameLonger, HomeGamesPlayed, HomeGamesWon, HomeSOS, HomeHistoryToWin, "TOP.txt");
	        homeTeam.QBFactor(homeQB);
	        homeTeam.CoachFactor(homeCoach);
	        System.out.println("HomeQB rating: " + homeTeam.getQB());
	        System.out.println("HomeCoach rating: " + homeTeam.getCoach());
	        Team awayTeam = createTeam(AwayName, AwayNameLonger, AwayGamesPlayed, AwayGamesWon, AwaySOS, AwayHistoryToWin, "TOP.txt");
	        awayTeam.QBFactor(awayQB);
	        awayTeam.CoachFactor(awayCoach);
	        System.out.println("AwayQB rating: " + awayTeam.getQB());
	        System.out.println("AwayCoach rating: " + awayTeam.getCoach());
	        
	        Game game1 = new Game();
	        game1.setNames(HomeName, AwayName);
	        System.out.println(HomeName + " QB: " + HomeQBName);
	        System.out.println(AwayName + " QB: " + AwayQBName);
	        System.out.println("Estimated Probability of Favored Team Winning by Vegas' Opening ML and Spread");
	        System.out.println(game1.EstProbChanceWinning(OpeningML, OpeningSpread));
	        System.out.println(game1.simGame(homeTeam, awayTeam, week));
	        System.out.println(game1.simGame(homeTeam, awayTeam, week));
	        System.out.println(game1.simGame(homeTeam, awayTeam, week));
	        System.out.println("Shows possible game results.");
	        System.out.println(game1.avgGameWithEstSpread(homeTeam, awayTeam, week));
	        System.out.println("Shows long run score over 1000 simulations and ideal spread for favorite.");      
        
    }
	
	public QB createQB(String TeamName, String QBName, double GamesPlayed) throws Exception
	{
		QB TeamQB = new QB();
	    StringBuilder TotalYardsStr = new StringBuilder();
	    StringBuilder OffenseTurnoversStr = new StringBuilder();
	    StringBuilder OffenseRedZoneEfficiencyStr = new StringBuilder();
	    StringBuilder PasserRatingStr = new StringBuilder();
	    double PasserRating = 0;
	    StringBuilder CompletionPercentageStr = new StringBuilder();
	    double CompletionPercentage = 0;
	    StringBuilder YardsPerAttemptStr = new StringBuilder();
	    double YardsPerAttempt = 0;
	    
	    BufferedReader br = new BufferedReader(new FileReader(TeamName+".txt"));
	    String str = "";
        while((str = br.readLine()) != null)
        {
            if(str.indexOf(">Team Stats</th><td class=\"right \" data-stat=\"points\"")!= -1)
            {
                for(int i = str.indexOf("data-stat=\"total_yards\" >"); i<str.indexOf("data-stat=\"total_yards\" >") + 29; i++)
                {   
                        if(Character.isDigit(str.charAt(i)) )
                        {
                            TotalYardsStr = TotalYardsStr.append(str.substring(i,i+1));
                        }
                }
                
                for(int i = str.indexOf("data-stat=\"turnovers\" >"); i<str.indexOf("data-stat=\"turnovers\" >") + 25; i++)
                {   
                        if(Character.isDigit(str.charAt(i)) )
                        {
                        	OffenseTurnoversStr = OffenseTurnoversStr.append(str.substring(i,i+1));
                        }
                }                       
            }           
            if(str.indexOf(">Team Stats</th><td class=\"right \" data-stat=\"third_down_att\" >") != -1)
            {
                for(int i = str.indexOf("data-stat=\"red_zone_pct\" >"); i<str.indexOf("data-stat=\"red_zone_pct\" >") + 30; i++)
                {   
                        if(Character.isDigit(str.charAt(i))||str.substring(i,i+1).equals(".") )
                        {
                            OffenseRedZoneEfficiencyStr = OffenseRedZoneEfficiencyStr.append(str.substring(i,i+1));
                        }
                }
            }            
            if(str.indexOf(QBName) != -1)
            {
                if(str.indexOf("data-stat=\"pass_rating\" >") != -1)
                {
                    for(int i = str.indexOf("data-stat=\"pass_rating\" >"); i<str.indexOf("data-stat=\"pass_rating\" >") + 30; i++)
                    {       
                        if(Character.isDigit(str.charAt(i))||str.substring(i,i+1).equals(".") )
                        {
                            PasserRatingStr = PasserRatingStr.append(str.substring(i,i+1));
                        }
                    }
                }
                
                if(str.indexOf("data-stat=\"pass_cmp_perc\" >") != -1)
                {
                    for(int i = str.indexOf("data-stat=\"pass_cmp_perc\" >"); i<str.indexOf("data-stat=\"pass_cmp_perc\" >") + 31; i++)
                    {      
                        if(Character.isDigit(str.charAt(i))||str.substring(i,i+1).equals(".") )
                        {
                            CompletionPercentageStr = CompletionPercentageStr.append(str.substring(i,i+1));
                        }
                    }
                }
                
                if(str.indexOf("data-stat=\"pass_yds_per_att\" >") != -1)
                {
                    for(int i = str.indexOf("data-stat=\"pass_yds_per_att\" >"); i<str.indexOf("data-stat=\"pass_yds_per_att\" >") + 34; i++)
                    {      
                        if(Character.isDigit(str.charAt(i))||str.substring(i,i+1).equals(".") )
                        {
                        	YardsPerAttemptStr = YardsPerAttemptStr.append(str.substring(i,i+1));
                        }
                    }
                }
            }
        }
        double YardsPerGame = Double.parseDouble(TotalYardsStr.toString())/GamesPlayed;
        double TurnoversPerGame;
        try
        {
        	TurnoversPerGame = Double.parseDouble(OffenseTurnoversStr.toString())/GamesPlayed;
        }
        catch(NumberFormatException e)
        {
        	TurnoversPerGame = 0.0;
        }
        double RedZoneEfficiency = Double.parseDouble(OffenseRedZoneEfficiencyStr.toString())/100;
        if(PasserRatingStr.length()>0)
        {
            PasserRating = Double.parseDouble(PasserRatingStr.toString());
        }
        if(CompletionPercentageStr.length()>0)
        {
        	CompletionPercentage = Double.parseDouble(CompletionPercentageStr.toString())/100;
        }
        if(YardsPerAttemptStr.length()>0)
        {
        	YardsPerAttempt = Double.parseDouble(YardsPerAttemptStr.toString());
        }
        TeamQB.setStats(YardsPerGame, PasserRating, TurnoversPerGame, RedZoneEfficiency, CompletionPercentage, YardsPerAttempt);
		return TeamQB;
	}
	
	public Coach createCoach(String TeamName, double GamesPlayed) throws Exception
	{
		Coach TeamCoach = new Coach();
		StringBuilder YardsGivenUp = new StringBuilder();
		StringBuilder CrucialPenalties = new StringBuilder();
		StringBuilder TurnoversForced = new StringBuilder();
		StringBuilder YardsPerPlayGivenUpStr = new StringBuilder();
		StringBuilder DefensiveRedZoneEfficiencyStr = new StringBuilder();
		StringBuilder TotalSacks = new StringBuilder();

        BufferedReader br = new BufferedReader(new FileReader(TeamName+".txt"));
        String str = "";
        while((str = br.readLine()) != null)
        {
            if(str.indexOf(">Team Stats</th><td class=\"right \" data-stat=\"points\"")!= -1)
            {

                for(int i = str.indexOf("data-stat=\"pen_fd\" >"); i<str.indexOf("data-stat=\"pen_fd\" >") + 22; i++)
                {   
                        if(Character.isDigit(str.charAt(i)) )
                        {
                            CrucialPenalties = CrucialPenalties.append(str.substring(i,i+1));
                        }
                }
            }
            
            if(str.indexOf(">Opp. Stats</th><td class=\"right \" data-stat=\"points\"") != -1)
            {
                for(int i = str.indexOf("data-stat=\"total_yards\" >"); i<str.indexOf("data-stat=\"total_yards\" >") + 29; i++)
                {   
                        if(Character.isDigit(str.charAt(i)) )
                        {
                            YardsGivenUp = YardsGivenUp.append(str.substring(i,i+1));
                        }
                }
                
                for(int i = str.indexOf("data-stat=\"turnovers\" >"); i<str.indexOf("data-stat=\"turnovers\" >") + 25; i++)
                {   
                        if(Character.isDigit(str.charAt(i)) )
                        {
                            TurnoversForced = TurnoversForced.append(str.substring(i,i+1));
                        }
                }
                
                for(int i = str.indexOf("data-stat=\"yds_per_play_offense\" >"); i<str.indexOf("data-stat=\"yds_per_play_offense\" >") + 37; i++)
                {   
                        if(Character.isDigit(str.charAt(i))||str.substring(i,i+1).equals(".") )
                        {
                            YardsPerPlayGivenUpStr = YardsPerPlayGivenUpStr.append(str.substring(i,i+1));
                        }
                }
            }
            if(str.indexOf(">Opp. Stats</th><td class=\"right \" data-stat=\"third_down_att\" >") != -1)
            {
                for(int i = str.indexOf("data-stat=\"red_zone_pct\" >"); i<str.indexOf("data-stat=\"red_zone_pct\" >") + 30; i++)
                {   
                        if(Character.isDigit(str.charAt(i))||str.substring(i,i+1).equals(".") )
                        {
                            DefensiveRedZoneEfficiencyStr = DefensiveRedZoneEfficiencyStr.append(str.substring(i,i+1));
                        }
                }
            }       
            if(str.indexOf("Team Total</td><td class=\"right \" data-stat=\"age\" >") != -1 && str.indexOf("data-stat=\"sacks\" >") != -1)
            {
                for(int i = str.indexOf("data-stat=\"sacks\" >"); i<str.indexOf("data-stat=\"sacks\" >") + 21; i++)
                {   
                        if(Character.isDigit(str.charAt(i))||str.substring(i,i+1).equals(".") )
                        {
                            TotalSacks = TotalSacks.append(str.substring(i,i+1));
                        }
                }
            }
        }
        double YardsGivenUpPerGame = Double.parseDouble(YardsGivenUp.toString())/GamesPlayed;
        double CrucialPenaltiesPerGame;
        try 
        {
        	CrucialPenaltiesPerGame = Double.parseDouble(CrucialPenalties.toString())/GamesPlayed;
        }
        catch(NumberFormatException e)
        {
        	CrucialPenaltiesPerGame = 0;
        }
        double TurnoversForcedPerGame;
        try
        {
        	TurnoversForcedPerGame = Double.parseDouble(TurnoversForced.toString())/GamesPlayed;
        }
        catch(NumberFormatException e)
        {
        	TurnoversForcedPerGame = 0;
        }
        double YardsPerPlayGivenUp = Double.parseDouble(YardsPerPlayGivenUpStr.toString());
        double DefensiveRedZoneEfficiency = Double.parseDouble(DefensiveRedZoneEfficiencyStr.toString())/100;
        double SacksPerGame;
        try
        {
        	SacksPerGame = Double.parseDouble(TotalSacks.toString())/GamesPlayed;
        }
        catch(NumberFormatException e)
        {
        	SacksPerGame = 0;
        }
        TeamCoach.setStats(YardsGivenUpPerGame, SacksPerGame, TurnoversForcedPerGame, DefensiveRedZoneEfficiency, YardsPerPlayGivenUp, CrucialPenaltiesPerGame);
		return TeamCoach;
	}
	
	public Team createTeam(String TeamName, String TeamNameLonger, double GamesPlayed, double GamesWon, double StrengthOfSchedule, double HistoryToWin, String TOP) throws Exception
	{
		Team thisTeam = new Team();
        StringBuilder TotalPointsStr = new StringBuilder();
        StringBuilder TotalPointsGivenUp = new StringBuilder();
        String TimePerDriveStr = "";
        StringBuilder PointsPerDriveStr = new StringBuilder();
        StringBuilder TimeOfPossessionStr = new StringBuilder();
        
        BufferedReader br = new BufferedReader(new FileReader(TeamName+".txt"));
        String str = "";
        while((str = br.readLine()) != null)
        {
            if(str.indexOf(">Team Stats</th><td class=\"right \" data-stat=\"points\"")!= -1)
            {
                for(int i = str.indexOf("data-stat=\"points\" >"); i<str.indexOf("data-stat=\"points\" >") + 24; i++)
                {   
                        if(Character.isDigit(str.charAt(i)) )
                        {
                            TotalPointsStr = TotalPointsStr.append(str.substring(i,i+1));
                        }
                }                
                for(int i = str.indexOf("data-stat=\"time_avg\" >"); i<str.indexOf("data-stat=\"time_avg\" >") + 26; i++)
                {   
                        if(Character.isDigit(str.charAt(i))||str.substring(i,i+1).equals(":") )
                        {    
                            TimePerDriveStr = TimePerDriveStr + str.substring(i,i+1);
                            TimePerDriveStr = TimePerDriveStr.replaceAll(":",".");
                        }   
                }
                
                for(int i = str.indexOf("data-stat=\"points_avg\" >"); i<str.indexOf("data-stat=\"points_avg\" >") + 28; i++)
                {   
                        if(Character.isDigit(str.charAt(i))||str.substring(i,i+1).equals(".") )
                        {
                            PointsPerDriveStr = PointsPerDriveStr.append(str.substring(i,i+1));
                        }
                }
                
            }
            
            if(str.indexOf(">Opp. Stats</th><td class=\"right \" data-stat=\"points\"") != -1)
            {
                         for(int i = str.indexOf("data-stat=\"points\" >"); i<str.indexOf("data-stat=\"points\" >") + 24; i++)
                {   
                        if(Character.isDigit(str.charAt(i)) )
                        {
                            TotalPointsGivenUp = TotalPointsGivenUp.append(str.substring(i,i+1));
                        }
                }
            }            
        }
        BufferedReader br2 = new BufferedReader(new FileReader(TOP));
        int d = 0;
        while((str = br2.readLine()) != null)
        {
        	 if(str.indexOf(TeamNameLonger + "</a></td>") != -1)
             {
                 d = 1;
             }    
             if(str.indexOf("<td class=\"text-right\" data-sort=") != -1 && d == 1)    
             {
                 while(d < 2)
                 {
                     for(int i = str.indexOf("<td class=\"text-right\" data-sort=")+40; i<str.indexOf("<td class=\"text-right\" data-sort=")+49; i++)
                     {   
                         if(Character.isDigit(str.charAt(i))||str.charAt(i) == ':'|| str.charAt(i) == '>')
                         {    
                             TimeOfPossessionStr = TimeOfPossessionStr.append(str.substring(i,i+1));
                         }
                     } 
                     d++;
                 }
                 if(TimeOfPossessionStr.indexOf(">") != -1)
                 {
                	 TimeOfPossessionStr = TimeOfPossessionStr.delete(0,TimeOfPossessionStr.indexOf(">")+1);
                 }
                 String TOPstr = TimeOfPossessionStr.toString();
                 TOPstr = TOPstr.replace(':', '.');
                 TimeOfPossessionStr = TimeOfPossessionStr.replace(0, TimeOfPossessionStr.length(), TOPstr);
             }
        }
        double WinPercentage = GamesWon/GamesPlayed;
        double TotalPoints = Double.parseDouble(TotalPointsStr.toString());
        double PointDifferential = TotalPoints/GamesPlayed - Double.parseDouble(TotalPointsGivenUp.toString())/GamesPlayed;
        double TimeOfPossession = Double.parseDouble(TimeOfPossessionStr.toString()); 
        int minutes = ((int)TimeOfPossession)%100;
        TimeOfPossession = minutes + (TimeOfPossession - minutes) * 100 / 60;
        System.out.println("Time of Possession: " + TimeOfPossession);
        double PointsPerDrive = 0;
        double TimePerDrive = 0;
        try
        {
        	PointsPerDrive = Double.parseDouble(PointsPerDriveStr.toString());
        	TimePerDrive = Double.parseDouble(TimePerDriveStr.toString());
        	int firstDigit = ((int)TimePerDrive)%10;
        	TimePerDrive = firstDigit + (TimePerDrive - firstDigit) * 100 / 60;
        }
        catch(NumberFormatException e)
        {
            int numDrives = 0;
            boolean nameFound = false;
            String numDrivesStr = "";
        	BufferedReader reader = new BufferedReader(new FileReader("DriveStats.txt"));
            while((str = reader.readLine()) != null && !nameFound)
            {
            	if(str.contains(TeamName))
            	{
            		str = reader.readLine();
            		for(int i = 16; i < str.length(); i++)
            		{
            			if(Character.isDigit(str.charAt(i)))
            			{
            				numDrivesStr = numDrivesStr + str.charAt(i);
            			}
            		}
            		nameFound = true;
            	}
            }
            numDrives = Integer.parseInt(numDrivesStr);
            TimePerDrive = TimeOfPossession/numDrives;
            System.out.println("Time Per Drive: " + TimePerDrive);
            PointsPerDrive = TotalPoints/numDrives;
            System.out.println("Points Per Drive: " + PointsPerDrive);
        }
        thisTeam.setStats(WinPercentage, HistoryToWin, PointDifferential, PointsPerDrive, StrengthOfSchedule, TimeOfPossession, TimePerDrive);
		return thisTeam;
	}

}
