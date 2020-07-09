package nflsim;
import java.io.File;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Calendar;

public class GameSim {
	String year = "";
	int week = 1;
	String[][] TeamInfo = new String[4][32];
	
	public void setYearAndInfo()
	{
		year = "2019";
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
                 {"New England", "Buffalo", "Miami", "NY Jets", "Kansas City", "Oakland", "LA Chargers", "Denver", "Baltimore", "Pittsburgh", "Cleveland", "Cincinnati", "Houston", "Indianapolis", "Jacksonville", "Tennessee", "Dallas", "Philidelphia", "NY Giants", "Washington", "San Francisco", "Seattle", "LA Rams", "Arizona", "Green Bay", "Minnesota","Detroit", "Chiacago", "New Orleans", "Carolina", "Tampa Bay", "Atlanta"},
                 {"Jarrett Stidham", "Josh Allen", "Tua Tagovailoa", "Sam Darnold", "Patrick Mahomes", "Derek Carr", "Justin Herbert", "Drew Lock", "Lamar Jackson", "Ben Roethlisberger", "Baker Mayfield", "Joe Burrow", "Deshaun Watson", "Philip Rivers", "Garder Minshew", "Ryan Tannehill", "Dak Prescott", "Carson Wentz", "Daniel Jones", "Dwayne Haskins", "Jimmy Garoppolo", "Russel Wilson", "Jared Goff", "Kyler Murray", "Aaron Rodgers", "Kirk Cousins", "Matthew Stafford","Mitchell Trubisky", "Drew Brees","Teddy Bridgewater","Tom Brady","Matt Ryan"}};
		 for(int i = 0; i < 4; i ++)
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
        
        String homeptsfor = "";
        String homeptsagainst = "";
        String homeydsfor = "";
        String homeydsagainst = "";
        String hometurnovers = "";
        String homePensfor1st = "";
        String homeTimePD = "";
        String homePPD = "";
        String homeTOsForced = "";
        String homeYdsPPGU = "";
        String homeRZEFF = "";
        String homeRZEFFA = "";
        String homePR = "";
        double HomePR = 0;
        String homeCP = "";
        double HomeCP = 0;
        String homeYPA = "";
        double HomeYPA = 0;
        String homeSPG = "";
        String homeTOP = "";
        String homeSec = "";
        String homegameswon = "";
        String homegamesplayed = "";
        String homegamestied = "";
        
        String awayptsfor = "";
        String awayptsagainst = "";
        String awayydsfor = "";
        String awayydsagainst = "";
        String awayturnovers = "";
        String awayPensfor1st = "";
        String awayTimePD = "";
        String awayPPD = "";
        String awayTOsForced = "";
        String awayYdsPPGU = "";
        String awayRZEFF = "";
        String awayRZEFFA = "";
        String awayPR = "";
        double AwayPR = 0;
        String awayCP = "";
        double AwayCP = 0;
        String awayYPA = "";
        double AwayYPA = 0;
        String awaySPG = "";
        String awayTOP = "";
        String awaySec = "";
        String awaygameswon = "";
        String awaygamesplayed = "";
        String awaygamestied = "";
        
        String str;
        int x = 0;
        int y = 0;
        int z = 0;
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        int e = 0;
        int f = 0;
        int g = 0;
        double temp = 0;
        double temp1 = 0;
        
        BufferedReader br = new BufferedReader(new FileReader(HomeName+".txt"));
        while((str = br.readLine()) != null)
        {
            if(str.indexOf("<strong>Record:</strong>") != -1)
            {
                for(int i = str.indexOf("<strong>Record:</strong>"); i < str.indexOf("<strong>Record:</strong>")+27; i++)
                {
                    if(Character.isDigit(str.charAt(i)) )
                        homegameswon = homegameswon + str.substring(i,i+1);
                }
                for(int i = str.indexOf("<strong>Record:</strong>")+27; i < str.indexOf("<strong>Record:</strong>")+29; i++)
                {
                    if(Character.isDigit(str.charAt(i)) )  
                        homegamesplayed = homegamesplayed + str.substring(i,i+1);
          
                }
                for(int i = str.indexOf("<strong>Record:</strong>")+29; i < str.indexOf("<strong>Record:</strong>")+31; i++)
                {
                    if(Character.isDigit(str.charAt(i)) )  
                        homegamestied = homegamestied + str.substring(i,i+1);
          
                }
            }
            if(str.indexOf(">Team Stats</th><td class=\"right \" data-stat=\"points\"")!= -1)
            {
                for(int i = str.indexOf("data-stat=\"points\" >"); i<str.indexOf("data-stat=\"points\" >") + 24; i++)
                {   
                        if(Character.isDigit(str.charAt(i)) )
                            homeptsfor = homeptsfor + str.substring(i,i+1);
                }
                
                for(int i = str.indexOf("data-stat=\"total_yards\" >"); i<str.indexOf("data-stat=\"total_yards\" >") + 29; i++)
                {   
                        if(Character.isDigit(str.charAt(i)) )
                            homeydsfor = homeydsfor + str.substring(i,i+1);
                }
                
                for(int i = str.indexOf("data-stat=\"turnovers\" >"); i<str.indexOf("data-stat=\"turnovers\" >") + 25; i++)
                {   
                        if(Character.isDigit(str.charAt(i)) )
                            hometurnovers = hometurnovers + str.substring(i,i+1);
                }
                
                for(int i = str.indexOf("data-stat=\"pen_fd\" >"); i<str.indexOf("data-stat=\"pen_fd\" >") + 22; i++)
                {   
                        if(Character.isDigit(str.charAt(i)) )
                            homePensfor1st = homePensfor1st + str.substring(i,i+1);
                }
                
                for(int i = str.indexOf("data-stat=\"time_avg\" >"); i<str.indexOf("data-stat=\"time_avg\" >") + 26; i++)
                {   
                        if(Character.isDigit(str.charAt(i))||str.substring(i,i+1).equals(":") )
                        {    
                            homeTimePD = homeTimePD + str.substring(i,i+1);
                            homeTimePD = homeTimePD.replaceAll(":",".");
                        }   
                }
                
                for(int i = str.indexOf("data-stat=\"points_avg\" >"); i<str.indexOf("data-stat=\"points_avg\" >") + 28; i++)
                {   
                        if(Character.isDigit(str.charAt(i))||str.substring(i,i+1).equals(".") )
                            homePPD = homePPD + str.substring(i,i+1);
                }
                
            }
            
            if(str.indexOf(">Opp. Stats</th><td class=\"right \" data-stat=\"points\"") != -1)
            {
                         for(int i = str.indexOf("data-stat=\"points\" >"); i<str.indexOf("data-stat=\"points\" >") + 24; i++)
                {   
                        if(Character.isDigit(str.charAt(i)) )
                            homeptsagainst = homeptsagainst + str.substring(i,i+1);
                }
                
                for(int i = str.indexOf("data-stat=\"total_yards\" >"); i<str.indexOf("data-stat=\"total_yards\" >") + 29; i++)
                {   
                        if(Character.isDigit(str.charAt(i)) )
                            homeydsagainst = homeydsagainst + str.substring(i,i+1);
                }
                
                for(int i = str.indexOf("data-stat=\"turnovers\" >"); i<str.indexOf("data-stat=\"turnovers\" >") + 25; i++)
                {   
                        if(Character.isDigit(str.charAt(i)) )
                            homeTOsForced = homeTOsForced + str.substring(i,i+1);
                }
                
                for(int i = str.indexOf("data-stat=\"yds_per_play_offense\" >"); i<str.indexOf("data-stat=\"yds_per_play_offense\" >") + 37; i++)
                {   
                        if(Character.isDigit(str.charAt(i))||str.substring(i,i+1).equals(".") )
                            homeYdsPPGU = homeYdsPPGU + str.substring(i,i+1);
                }
            }
            
            if(str.indexOf(">Team Stats</th><td class=\"right \" data-stat=\"third_down_att\" >") != -1)
            {
                for(int i = str.indexOf("data-stat=\"red_zone_pct\" >"); i<str.indexOf("data-stat=\"red_zone_pct\" >") + 30; i++)
                {   
                        if(Character.isDigit(str.charAt(i))||str.substring(i,i+1).equals(".") )
                            homeRZEFF = homeRZEFF + str.substring(i,i+1);
                }
            }
            
            if(str.indexOf(">Opp. Stats</th><td class=\"right \" data-stat=\"third_down_att\" >") != -1)
            {
                for(int i = str.indexOf("data-stat=\"red_zone_pct\" >"); i<str.indexOf("data-stat=\"red_zone_pct\" >") + 30; i++)
                {   
                        if(Character.isDigit(str.charAt(i))||str.substring(i,i+1).equals(".") )
                            homeRZEFFA = homeRZEFFA + str.substring(i,i+1);
                }
            }
            
            if(str.indexOf(HomeQB) != -1)
            {
                x = x+1;
                y = y+1;
                z = z+1;
                if(str.indexOf("data-stat=\"pass_rating\" >") != -1 && x < 50)
                {
                    for(int i = str.indexOf("data-stat=\"pass_rating\" >"); i<str.indexOf("data-stat=\"pass_rating\" >") + 30; i++)
                    {   
                    x = x + 50;    
                        if(Character.isDigit(str.charAt(i))||str.substring(i,i+1).equals(".") )
                            homePR = homePR + str.substring(i,i+1);
                    }
                }
                
                if(str.indexOf("data-stat=\"pass_cmp_perc\" >") != -1 && y < 50)
                {
                    for(int i = str.indexOf("data-stat=\"pass_cmp_perc\" >"); i<str.indexOf("data-stat=\"pass_cmp_perc\" >") + 31; i++)
                    {   
                    y = y + 50;    
                        if(Character.isDigit(str.charAt(i))||str.substring(i,i+1).equals(".") )
                            homeCP = homeCP + str.substring(i,i+1);
                    }
                }
                
                if(str.indexOf("data-stat=\"pass_yds_per_att\" >") != -1 && z < 50)
                {
                    for(int i = str.indexOf("data-stat=\"pass_yds_per_att\" >"); i<str.indexOf("data-stat=\"pass_yds_per_att\" >") + 34; i++)
                    {   
                    z = z + 50;    
                        if(Character.isDigit(str.charAt(i))||str.substring(i,i+1).equals(".") )
                            homeYPA = homeYPA + str.substring(i,i+1);
                    }
                }
            }
         
            if(str.indexOf("Team Total</td><td class=\"right \" data-stat=\"age\" >") != -1 && str.indexOf("data-stat=\"sacks\" >") != -1)
            {
                for(int i = str.indexOf("data-stat=\"sacks\" >"); i<str.indexOf("data-stat=\"sacks\" >") + 21; i++)
                {   
                        if(Character.isDigit(str.charAt(i))||str.substring(i,i+1).equals(".") )
                            homeSPG = homeSPG + str.substring(i,i+1);
                }
            }
        }
        
        double HomeGamesPlayed = Double.parseDouble(homegameswon) + Double.parseDouble(homegamesplayed) + Double.parseDouble(homegamestied);
        double HomeWinPct = Double.parseDouble(homegameswon)/HomeGamesPlayed;
        double HomePPG = Double.parseDouble(homeptsfor)/HomeGamesPlayed;
        double HomePPGA = Double.parseDouble(homeptsagainst)/HomeGamesPlayed;
        double HomePD = HomePPG-HomePPGA;
        double HomeYPG = Double.parseDouble(homeydsfor)/HomeGamesPlayed;
        double HomeYAPG = Double.parseDouble(homeydsagainst)/HomeGamesPlayed;
        double HomeTPG = Double.parseDouble(hometurnovers)/HomeGamesPlayed;
        double HomeCPPG = Double.parseDouble(homePensfor1st)/HomeGamesPlayed;
        double HomeTPD = Double.parseDouble(homeTimePD);
        if(HomeTPD > 3)
            HomeTPD = 3 + (HomeTPD-3)*100/60;
        else if(HomeTPD > 2)
            HomeTPD = 2 + (HomeTPD-2)*100/60;
        else
            HomeTPD = 1 + (HomeTPD-1)*100/60;
        double HomePPD = Double.parseDouble(homePPD);
        double HomeTFPG = Double.parseDouble(homeTOsForced)/HomeGamesPlayed;
        double HomeYPP = Double.parseDouble(homeYdsPPGU);
        double HomeRZE = Double.parseDouble(homeRZEFF)/100;
        double HomeRZEA = Double.parseDouble(homeRZEFFA)/100;
        if(homePR.length()>0)
            HomePR = Double.parseDouble(homePR);
        if(homeCP.length()>0)
            HomeCP = Double.parseDouble(homeCP)/100;
        if(homeYPA.length()>0)
            HomeYPA = Double.parseDouble(homeYPA);
        double HomeSPG = Double.parseDouble(homeSPG)/HomeGamesPlayed;
        
        BufferedReader br1 = new BufferedReader(new FileReader(AwayName+".txt"));
        while((str = br1.readLine()) != null)
        {
            if(str.indexOf("<strong>Record:</strong>") != -1)
            {
                for(int i = str.indexOf("<strong>Record:</strong>"); i < str.indexOf("<strong>Record:</strong>")+27; i++)
                {
                    if(Character.isDigit(str.charAt(i)) )
                        awaygameswon = awaygameswon + str.substring(i,i+1);
                }
                for(int i = str.indexOf("<strong>Record:</strong>")+27; i < str.indexOf("<strong>Record:</strong>")+29; i++)
                {
                    if(Character.isDigit(str.charAt(i)) )
                    {    
                        awaygamesplayed = awaygamesplayed + str.substring(i,i+1);
                        
                    }
                }
                 for(int i = str.indexOf("<strong>Record:</strong>")+29; i < str.indexOf("<strong>Record:</strong>")+31; i++)
                {
                    if(Character.isDigit(str.charAt(i)) )  
                        awaygamestied = awaygamestied + str.substring(i,i+1);
          
                }
            }
            if(str.indexOf(">Team Stats</th><td class=\"right \" data-stat=\"points\"")!= -1)
            {
                for(int i = str.indexOf("data-stat=\"points\" >"); i<str.indexOf("data-stat=\"points\" >") + 24; i++)
                {   
                        if(Character.isDigit(str.charAt(i)) )
                            awayptsfor = awayptsfor + str.substring(i,i+1);
                }
                
                for(int i = str.indexOf("data-stat=\"total_yards\" >"); i<str.indexOf("data-stat=\"total_yards\" >") + 29; i++)
                {   
                        if(Character.isDigit(str.charAt(i)) )
                            awayydsfor = awayydsfor + str.substring(i,i+1);
                }
                
                for(int i = str.indexOf("data-stat=\"turnovers\" >"); i<str.indexOf("data-stat=\"turnovers\" >") + 25; i++)
                {   
                        if(Character.isDigit(str.charAt(i)) )
                            awayturnovers = awayturnovers + str.substring(i,i+1);
                }
                
                for(int i = str.indexOf("data-stat=\"pen_fd\" >"); i<str.indexOf("data-stat=\"pen_fd\" >") + 22; i++)
                {   
                        if(Character.isDigit(str.charAt(i)) )
                            awayPensfor1st = awayPensfor1st + str.substring(i,i+1);
                }
                
                for(int i = str.indexOf("data-stat=\"time_avg\" >"); i<str.indexOf("data-stat=\"time_avg\" >") + 26; i++)
                {   
                        if(Character.isDigit(str.charAt(i))||str.substring(i,i+1).equals(":") )
                        {    
                            awayTimePD = awayTimePD + str.substring(i,i+1);
                            awayTimePD = awayTimePD.replaceAll(":",".");
                        }   
                }
                
                for(int i = str.indexOf("data-stat=\"points_avg\" >"); i<str.indexOf("data-stat=\"points_avg\" >") + 28; i++)
                {   
                        if(Character.isDigit(str.charAt(i))||str.substring(i,i+1).equals(".") )
                            awayPPD = awayPPD + str.substring(i,i+1);
                }
                
            }
            
            if(str.indexOf(">Opp. Stats</th><td class=\"right \" data-stat=\"points\"") != -1)
            {
                         for(int i = str.indexOf("data-stat=\"points\" >"); i<str.indexOf("data-stat=\"points\" >") + 24; i++)
                {   
                        if(Character.isDigit(str.charAt(i)) )
                            awayptsagainst = awayptsagainst + str.substring(i,i+1);
                }
                
                for(int i = str.indexOf("data-stat=\"total_yards\" >"); i<str.indexOf("data-stat=\"total_yards\" >") + 29; i++)
                {   
                        if(Character.isDigit(str.charAt(i)) )
                            awayydsagainst = awayydsagainst + str.substring(i,i+1);
                }
                
                for(int i = str.indexOf("data-stat=\"turnovers\" >"); i<str.indexOf("data-stat=\"turnovers\" >") + 25; i++)
                {   
                        if(Character.isDigit(str.charAt(i)) )
                            awayTOsForced = awayTOsForced + str.substring(i,i+1);
                }
                
                for(int i = str.indexOf("data-stat=\"yds_per_play_offense\" >"); i<str.indexOf("data-stat=\"yds_per_play_offense\" >") + 37; i++)
                {   
                        if(Character.isDigit(str.charAt(i))||str.substring(i,i+1).equals(".") )
                            awayYdsPPGU = awayYdsPPGU + str.substring(i,i+1);
                }
            }
            
            if(str.indexOf(">Team Stats</th><td class=\"right \" data-stat=\"third_down_att\" >") != -1)
            {
                for(int i = str.indexOf("data-stat=\"red_zone_pct\" >"); i<str.indexOf("data-stat=\"red_zone_pct\" >") + 30; i++)
                {   
                        if(Character.isDigit(str.charAt(i))||str.substring(i,i+1).equals(".") )
                            awayRZEFF = awayRZEFF + str.substring(i,i+1);
                }
            }
            
            if(str.indexOf(">Opp. Stats</th><td class=\"right \" data-stat=\"third_down_att\" >") != -1)
            {
                for(int i = str.indexOf("data-stat=\"red_zone_pct\" >"); i<str.indexOf("data-stat=\"red_zone_pct\" >") + 30; i++)
                {   
                        if(Character.isDigit(str.charAt(i))||str.substring(i,i+1).equals(".") )
                            awayRZEFFA = awayRZEFFA + str.substring(i,i+1);
                }
            }
            
            if(str.indexOf(AwayQB) != -1)
            {
                a = a+1;
                b = b+1;
                c = c+1;
                if(str.indexOf("data-stat=\"pass_rating\" >") != -1 && a < 50)
                {
                    for(int i = str.indexOf("data-stat=\"pass_rating\" >"); i<str.indexOf("data-stat=\"pass_rating\" >") + 30; i++)
                    {   
                    a = a + 50;    
                        if(Character.isDigit(str.charAt(i))||str.substring(i,i+1).equals(".") )
                            awayPR = awayPR + str.substring(i,i+1);
                    }
                }
            
                if(str.indexOf("data-stat=\"pass_cmp_perc\" >") != -1 &&  b < 50)
                {
                    for(int i = str.indexOf("data-stat=\"pass_cmp_perc\" >"); i<str.indexOf("data-stat=\"pass_cmp_perc\" >") + 31; i++)
                    {   
                    b = b + 50;    
                        if(Character.isDigit(str.charAt(i))||str.substring(i,i+1).equals(".") )
                            awayCP = awayCP + str.substring(i,i+1);
                    }
                }
            
                if(str.indexOf("data-stat=\"pass_yds_per_att\" >") != -1 && c < 50)
                {
                    for(int i = str.indexOf("data-stat=\"pass_yds_per_att\" >"); i<str.indexOf("data-stat=\"pass_yds_per_att\" >") + 34; i++)
                    {   
                    c = c + 50;    
                        if(Character.isDigit(str.charAt(i))||str.substring(i,i+1).equals(".") )
                            awayYPA = awayYPA + str.substring(i,i+1);
                    }
                }
            
            }
            
            if(str.indexOf("Team Total</td><td class=\"right \" data-stat=\"age\" >") != -1 && str.indexOf("data-stat=\"sacks\" >") != -1)
            {
                for(int i = str.indexOf("data-stat=\"sacks\" >"); i<str.indexOf("data-stat=\"sacks\" >") + 21; i++)
                {   
                        if(Character.isDigit(str.charAt(i))||str.substring(i,i+1).equals(".") )
                            awaySPG = awaySPG + str.substring(i,i+1);
                }
            }
        }
        
        double AwayGamesPlayed = Double.parseDouble(awaygamesplayed) + Double.parseDouble(awaygameswon) + Double.parseDouble(awaygamestied);
        double AwayWinPct = Double.parseDouble(awaygameswon)/AwayGamesPlayed;
        double AwayPPG = Double.parseDouble(awayptsfor)/AwayGamesPlayed;
        double AwayPPGA = Double.parseDouble(awayptsagainst)/AwayGamesPlayed;
        double AwayPD = AwayPPG-AwayPPGA;
        double AwayYPG = Double.parseDouble(awayydsfor)/AwayGamesPlayed;
        double AwayYAPG = Double.parseDouble(awayydsagainst)/AwayGamesPlayed;
        double AwayTPG = Double.parseDouble(awayturnovers)/AwayGamesPlayed;
        double AwayCPPG = Double.parseDouble(awayPensfor1st)/AwayGamesPlayed;
        double AwayTPD = Double.parseDouble(awayTimePD);
        if(AwayTPD > 3)
            AwayTPD = 3 + (AwayTPD-3)*100/60;
        else if(AwayTPD > 2)
            AwayTPD = 2 + (AwayTPD-2)*100/60;
        else
            AwayTPD = 1 + (AwayTPD-1)*100/60;
        double AwayPPD = Double.parseDouble(awayPPD);
        double AwayTFPG = Double.parseDouble(awayTOsForced)/AwayGamesPlayed;
        double AwayYPP = Double.parseDouble(awayYdsPPGU);
        double AwayRZE = Double.parseDouble(awayRZEFF)/100;
        double AwayRZEA = Double.parseDouble(awayRZEFFA)/100;
        if(awayPR.length()>0)
            AwayPR = Double.parseDouble(awayPR);
        if(awayCP.length()>0)
            AwayCP = Double.parseDouble(awayCP)/100;
        if(awayYPA.length()>0)
            AwayYPA = Double.parseDouble(awayYPA);
        double AwaySPG = Double.parseDouble(awaySPG)/AwayGamesPlayed;
        
        BufferedReader br2 = new BufferedReader(new FileReader(top));
        while((str = br2.readLine()) != null)
        {
            if(str.indexOf(HomeName1 + "</a></td>") != -1)
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
                            homeTOP = homeTOP + str.substring(i,i+1);
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
                            homeSec = homeSec + str.substring(i,i+1);
                        } 
                       
                        e++;
                    } 
                }
                double secs = Double.parseDouble(homeSec)/60.0;
                homeSec = Double.toString(secs);
                homeSec = homeSec.replaceFirst("0", "");
                homeTOP = homeTOP + homeSec;
            }
            
            if(str.indexOf(AwayName1 + "</a></td>") != -1)
            {
                f = 1;
                g = 1;
            }
            if(str.indexOf("<td class=\"text-right\" data-sort=") != -1 && f == 1 && g == 1)
            {
                while(f < 2)
                {
                    for(int i = str.indexOf("<td class=\"text-right\" data-sort=")+42; i<str.indexOf("<td class=\"text-right\" data-sort=")+45; i++)
                    {   
                        if(Character.isDigit(str.charAt(i)))
                        {    
                            awayTOP = awayTOP + str.substring(i,i+1);
                        }
                        
                        f++;
                    } 
                }
                
                while(g < 2)
                {
                    for(int i = str.indexOf("<td class=\"text-right\" data-sort=")+46; i<str.indexOf("<td class=\"text-right\" data-sort=")+48; i++)
                    {   
                        if(Character.isDigit(str.charAt(i)))
                        {
                            awaySec = awaySec + str.substring(i,i+1);
                        } 
                        g++;
                    } 
                    if(awaySec.equals(""))
                        awaySec = "0";
                }
                double secs = Double.parseDouble(awaySec)/60.0;
                awaySec = Double.toString(secs);
                awaySec = awaySec.replaceFirst("0", "");
                awayTOP = awayTOP + awaySec;
            }
        }
        double HomeTOP = Double.parseDouble(homeTOP);
        double AwayTOP = Double.parseDouble(awayTOP);

        
        System.out.println(HomePD);
        System.out.println(HomeYPG);
        System.out.println(HomeYAPG);
        System.out.println(HomeTPG);
        System.out.println(HomeCPPG);
        System.out.println(HomeTPD);
        System.out.println(HomePPD);
        System.out.println(HomeTFPG);
        System.out.println(HomeYPP);
        System.out.println(HomeRZE);
        System.out.println(HomeRZEA);
        System.out.println(HomePR);
        System.out.println(HomeCP);
        System.out.println(HomeYPA);
        System.out.println(HomeSPG);
        System.out.println(HomeTOP);
        System.out.println(HomeGamesPlayed);
        System.out.println(HomeWinPct);
        
        System.out.println("\n");
        
        System.out.println(AwayPD);
        System.out.println(AwayYPG);
        System.out.println(AwayYAPG);
        System.out.println(AwayTPG);
        System.out.println(AwayCPPG);
        System.out.println(AwayTPD);
        System.out.println(AwayPPD);
        System.out.println(AwayTFPG);
        System.out.println(AwayYPP);
        System.out.println(AwayRZE);
        System.out.println(AwayRZEA);
        System.out.println(AwayPR);
        System.out.println(AwayCP);
        System.out.println(AwayYPA);
        System.out.println(AwaySPG);
        System.out.println(AwayTOP);
        System.out.println(AwayGamesPlayed);
        System.out.println(AwayWinPct);
        System.out.println("\n");
         
        QB homeQB = new QB();
        homeQB.setStats(HomeYPG, HomePR, HomeTPG, HomeRZE, HomeCP, HomeYPA);
        QB awayQB = new QB();
        awayQB.setStats(AwayYPG, AwayPR, AwayTPG, AwayRZE, AwayCP, AwayYPA); 
        
        Coach homeCoach = new Coach();
        homeCoach.setStats(HomeYAPG, HomeSPG, HomeTFPG, HomeRZEA, HomeYPP, HomeCPPG);
        Coach awayCoach = new Coach();
        awayCoach.setStats(AwayYAPG, AwaySPG, AwayTFPG, AwayRZEA, AwayYPP, AwayCPPG);
        
        Team homeTeam = new Team();
        homeTeam.setStats(HomeWinPct, HomeHistoryToWin, HomePD, HomePPD, HomeSOS, HomeTOP, HomeTPD);
        homeTeam.QBFactor(homeQB);
        homeTeam.CoachFactor(homeCoach);
        Team awayTeam = new Team();
        awayTeam.setStats(AwayWinPct, AwayHistoryToWin, AwayPD, AwayPPD, AwaySOS, AwayTOP, AwayTPD);
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
}
