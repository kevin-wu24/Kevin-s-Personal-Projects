package nflsim;
import java.io.File;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Calendar;

public class GameSim {
	String year = "";
	String previousYear = "";
	int week = 1;
	String[][] TeamInfo = new String[5][32];
	
	public GameSim()
	{
		year = "" + (Calendar.getInstance().get(Calendar.YEAR)-1);
		previousYear = year;
		Calendar current = Calendar.getInstance();
		Calendar week1 = Calendar.getInstance();
		week1.set(2020, 9, 14);
		Calendar week17 = Calendar.getInstance();
		week17.set(2021, 1, 15);
		if(current.after(week1) && current.before(week17))
		{
			year = "2020";
		}
		 String[][] teams = {{"NE", "BUF", "MIA", "NYJ", "KC", "OAK", "LAC", "DEN","BAL","PIT","CLE","CIN","HOU","IND","JAX","TEN","DAL","PHI","NYG","WSH","SF","SEA","LAR","ARI","GB","MIN","DET","CHI","NO","CAR","TB","ATL"},
                 {"https://www.pro-football-reference.com/teams/nwe/"+year+".htm","https://www.pro-football-reference.com/teams/buf/"+year+".htm","https://www.pro-football-reference.com/teams/mia/"+year+".htm","https://www.pro-football-reference.com/teams/nyj/"+year+".htm","https://www.pro-football-reference.com/teams/kan/"+year+".htm","https://www.pro-football-reference.com/teams/rai/"+year+".htm","https://www.pro-football-reference.com/teams/sdg/"+year+".htm","https://www.pro-football-reference.com/teams/den/"+year+".htm",
                  "https://www.pro-football-reference.com/teams/rav/"+year+".htm","https://www.pro-football-reference.com/teams/pit/"+year+".htm","https://www.pro-football-reference.com/teams/cle/"+year+".htm","https://www.pro-football-reference.com/teams/cin/"+year+".htm","https://www.pro-football-reference.com/teams/htx/"+year+".htm","https://www.pro-football-reference.com/teams/clt/"+year+".htm","https://www.pro-football-reference.com/teams/jax/"+year+".htm","https://www.pro-football-reference.com/teams/oti/"+year+".htm",
                  "https://www.pro-football-reference.com/teams/dal/"+year+".htm","https://www.pro-football-reference.com/teams/phi/"+year+".htm","https://www.pro-football-reference.com/teams/nyg/"+year+".htm","https://www.pro-football-reference.com/teams/was/"+year+".htm","https://www.pro-football-reference.com/teams/sfo/"+year+".htm","https://www.pro-football-reference.com/teams/sea/"+year+".htm","https://www.pro-football-reference.com/teams/ram/"+year+".htm","https://www.pro-football-reference.com/teams/crd/"+year+".htm",
                  "https://www.pro-football-reference.com/teams/gnb/"+year+".htm","https://www.pro-football-reference.com/teams/min/"+year+".htm","https://www.pro-football-reference.com/teams/det/"+year+".htm","https://www.pro-football-reference.com/teams/chi/"+year+".htm","https://www.pro-football-reference.com/teams/nor/"+year+".htm","https://www.pro-football-reference.com/teams/car/"+year+".htm","https://www.pro-football-reference.com/teams/tam/"+year+".htm","https://www.pro-football-reference.com/teams/atl/"+year+".htm"},
                 {"New England", "Buffalo", "Miami", "NY Jets", "Kansas City", "Las Vegas", "LA Chargers", "Denver", "Baltimore", "Pittsburgh", "Cleveland", "Cincinnati", "Houston", "Indianapolis", "Jacksonville", "Tennessee", "Dallas", "Philadelphia", "NY Giants", "Washington", "San Francisco", "Seattle", "LA Rams", "Arizona", "Green Bay", "Minnesota","Detroit", "Chiacago", "New Orleans", "Carolina", "Tampa Bay", "Atlanta"},
                 {"Cam Newton", "Josh Allen", "Tua Tagovailoa", "Sam Darnold", "Patrick Mahomes", "Derek Carr", "Justin Herbert", "Drew Lock", "Lamar Jackson", "Ben Roethlisberger", "Baker Mayfield", "Joe Burrow", "Deshaun Watson", "Philip Rivers", "Garder Minshew", "Ryan Tannehill", "Dak Prescott", "Carson Wentz", "Daniel Jones", "Dwayne Haskins", "Jimmy Garoppolo", "Russel Wilson", "Jared Goff", "Kyler Murray", "Aaron Rodgers", "Kirk Cousins", "Matthew Stafford","Mitchell Trubisky", "Drew Brees","Teddy Bridgewater","Tom Brady","Matt Ryan"},
                 {"Patriots", "Bills", "Dolphins", "Jets", "Chiefs", "Raiders", "Chargers", "Broncos", "Ravens", "Steelers", "Browns", "Bengals", "Texans", "Colts", "Jaguars", "Titans", "Cowboys", "Eagles", "Giants", "Football Team", "49ers", "Seahawks", "Rams", "Cardinals", "Packers", "Vikings", "Lions", "Bears", "Saints", "Panthers", "Buccaneers", "Falcons"}};
		 for(int i = 0; i < 5; i ++)
		 {
			 for(int j = 0; j<32; j++)
			 {
				 TeamInfo[i][j] = teams[i][j];
			 }
			 
		 }
	}
	
	public void simGame() throws Exception
    {
        String HomeName1 = "";
        String AwayName1 = "";
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Home Team Name:");
        String HomeName = input.nextLine();
        System.out.println("Enter Away Team Name:");
        String AwayName = input.nextLine();
        System.out.println("Enter " + HomeName + " QB Name:");
        String HomeQB = input.nextLine();
        System.out.println("Enter " + AwayName + " QB Name:");
        String AwayQB = input.nextLine();
        
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
        		HomeName1 = TeamInfo[2][i];
        	}
        	if(AwayName.equals(TeamInfo[0][i]))
        	{
        		AwayName1 = TeamInfo[2][i];
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
           
        QB homeQB = createQB(HomeName, HomeQB, HomeGamesPlayed);
        QB awayQB = createQB(AwayName, AwayQB, AwayGamesPlayed);
        
        Coach homeCoach = createCoach(HomeName, HomeGamesPlayed);
        Coach awayCoach = createCoach(AwayName, AwayGamesPlayed);
        
        Team homeTeam = createTeam(HomeName, HomeName1, HomeGamesPlayed, HomeGamesWon, HomeSOS, HomeHistoryToWin, "TOP.txt");
        homeTeam.QBFactor(homeQB);
        homeTeam.CoachFactor(homeCoach);
        Team awayTeam = createTeam(AwayName, AwayName1, AwayGamesPlayed, AwayGamesWon, AwaySOS, AwayHistoryToWin, "TOP.txt");
        awayTeam.QBFactor(awayQB);
        awayTeam.CoachFactor(awayCoach);
        
        Game game1 = new Game();
        game1.setNames(HomeName, AwayName);
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
        double TurnoversPerGame = Double.parseDouble(OffenseTurnoversStr.toString())/GamesPlayed;
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
        double CrucialPenaltiesPerGame = Double.parseDouble(CrucialPenalties.toString())/GamesPlayed;
        double TurnoversForcedPerGame = Double.parseDouble(TurnoversForced.toString())/GamesPlayed;
        double YardsPerPlayGivenUp = Double.parseDouble(YardsPerPlayGivenUpStr.toString());
        double DefensiveRedZoneEfficiency = Double.parseDouble(DefensiveRedZoneEfficiencyStr.toString())/100;
        double SacksPerGame = Double.parseDouble(TotalSacks.toString())/GamesPlayed;
        TeamCoach.setStats(YardsGivenUpPerGame, SacksPerGame, TurnoversForcedPerGame, DefensiveRedZoneEfficiency, YardsPerPlayGivenUp, CrucialPenaltiesPerGame);
		return TeamCoach;
	}
	
	public Team createTeam(String TeamName, String TeamNameLonger, double GamesPlayed, double GamesWon, double StrengthOfSchedule, double HistoryToWin, String TOP) throws Exception
	{
		Team thisTeam = new Team();
        StringBuilder TotalPoints = new StringBuilder();
        StringBuilder TotalPointsGivenUp = new StringBuilder();
        String TimePerDriveStr = "";
        StringBuilder PointsPerDriveStr = new StringBuilder();
        String TimeOfPossessionStr = "";
        String TimeSeconds = "";
        
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
                            TotalPoints = TotalPoints.append(str.substring(i,i+1));
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
        int e = 0;
        while((str = br2.readLine()) != null)
        {
        	 if(str.indexOf(TeamNameLonger + "</a></td>") != -1)
             {
                 d = 1;
                 e = 1;
             }    
             if(str.indexOf("<td class=\"text-right\" data-sort=") != -1 && d == 1 && e == 1)    
             {
                 while(d < 2)
                 {
                     for(int i = str.indexOf("<td class=\"text-right\" data-sort=")+42; i<str.indexOf("<td class=\"text-right\" data-sort=")+45; i++)
                     {   
                         if(Character.isDigit(str.charAt(i)))
                         {    
                             TimeOfPossessionStr = TimeOfPossessionStr + str.substring(i,i+1);
                         }
                         
                         d++;
                     } 
                 }
                 
                 while(e < 2)
                 {
                     for(int i = str.indexOf("<td class=\"text-right\" data-sort=")+46; i<str.indexOf("<td class=\"text-right\" data-sort=")+48; i++)
                     {   
                         if(Character.isDigit(str.charAt(i)))
                         {
                             TimeSeconds = TimeSeconds + str.substring(i,i+1);
                         } 
                        
                         e++;
                     } 
                 }
                 double secs = Double.parseDouble(TimeSeconds.toString())/60.0;
                 String homeSec = Double.toString(secs);
                 homeSec = homeSec.replaceFirst("0", "");
                 TimeOfPossessionStr = TimeOfPossessionStr + homeSec;
             }
        }
        double WinPercentage = GamesWon/GamesPlayed;
        double PointDifferential = Double.parseDouble(TotalPoints.toString())/GamesPlayed - Double.parseDouble(TotalPointsGivenUp.toString())/GamesPlayed;
        double PointsPerDrive = Double.parseDouble(PointsPerDriveStr.toString());
        double TimePerDrive = Double.parseDouble(TimePerDriveStr.toString());
        if(TimePerDrive > 3)
        {
        	TimePerDrive = 3 + (TimePerDrive-3)*100/60;
        }
        else if(TimePerDrive > 2)
        {
        	TimePerDrive = 2 + (TimePerDrive-2)*100/60;
        }
        else
        {
        	TimePerDrive = 1 + (TimePerDrive-1)*100/60;
        }
        double TimeOfPossession = Double.parseDouble(TimeOfPossessionStr.toString());
        thisTeam.setStats(WinPercentage, HistoryToWin, PointDifferential, PointsPerDrive, StrengthOfSchedule, TimeOfPossession, TimePerDrive);
		return thisTeam;
	}

}
