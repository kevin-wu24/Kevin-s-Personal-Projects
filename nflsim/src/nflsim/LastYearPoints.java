package nflsim;
import java.io.*;
import java.util.Hashtable;
import java.util.Dictionary;

public class LastYearPoints
{
	String subYear = "2019";
	String team = "";
	String qb = "";
	String teamName2 = "";
	double score = 0;

	
	public void setPoints(String TeamName) throws Exception
	{
		Hashtable<String, String> dictionary = new Hashtable<String, String>();
		
		dictionary.put("TB", "NE2019.txt");
		dictionary.put("IND", "LAC2019.txt");
		dictionary.put("CAR", "NO2019.txt");
		dictionary.put("MIA", "MIA2019.txt");
		dictionary.put("CIN", "CIN2019.txt");
		dictionary.put("LAC", "LAC2019.txt");
		
		GameSim game = new GameSim();
		for(int j = 0;j<32;j++)
		{
			if(game.TeamInfo[0][j].equals(TeamName))
			{
				team = game.TeamInfo[0][j];
				qb = game.TeamInfo[3][j];
				teamName2 = game.TeamInfo[2][j];
			}
		}
		
        	SoSRetriever sos = new SoSRetriever();
            sos.setSOSInfo();
            sos.calcSOS();
            sos.setSOSYear(subYear);
            double SOS = sos.getSOS(TeamName);
            double gamesWon = sos.getGamesWon(TeamName);
            double gamesPlayed = sos.getTotalGamesPlayed(TeamName);
            
            String ptsfor = "";
            String ptsagainst = "";
            String ydsfor = "";
            String ydsagainst = "";
            String turnovers = "";
            String Pensfor1st = "";
            String TimePD = "";
            String PPD = "";
            String TOsForced = "";
            String YdsPPGU = "";
            String RZEFF = "";
            String RZEFFA = "";
            String PR = "";
            double pr = 70;
            String CP = "";
            double cp = 0.6;
            String YPA = "";
            double ypa = 6;
            String SPG = "";
            String TOP = "";
            String Sec = "";
            String gameswon = "";
            String gamesplayed = "";
            String gamestied = "";
            
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
            
            BufferedReader br = new BufferedReader(new FileReader(TeamName+subYear+".txt"));
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
                            gamesplayed = gamesplayed + str.substring(i,i+1);
              
                    }
                    for(int i = str.indexOf("<strong>Record:</strong>")+29; i < str.indexOf("<strong>Record:</strong>")+31; i++)
                    {
                        if(Character.isDigit(str.charAt(i)) )  
                            gamestied = gamestied + str.substring(i,i+1);
              
                    }
                }
                if(str.indexOf(">Team Stats</th><td class=\"right \" data-stat=\"points\"")!= -1)
                {
                    for(int i = str.indexOf("data-stat=\"points\" >"); i<str.indexOf("data-stat=\"points\" >") + 24; i++)
                    {   
                            if(Character.isDigit(str.charAt(i)) )
                                ptsfor = ptsfor + str.substring(i,i+1);
                    }
                    
                    for(int i = str.indexOf("data-stat=\"total_yards\" >"); i<str.indexOf("data-stat=\"total_yards\" >") + 29; i++)
                    {   
                            if(Character.isDigit(str.charAt(i)) )
                                ydsfor = ydsfor + str.substring(i,i+1);
                    }
                    
                    for(int i = str.indexOf("data-stat=\"turnovers\" >"); i<str.indexOf("data-stat=\"turnovers\" >") + 25; i++)
                    {   
                            if(Character.isDigit(str.charAt(i)) )
                                turnovers = turnovers + str.substring(i,i+1);
                    }
                    
                    for(int i = str.indexOf("data-stat=\"pen_fd\" >"); i<str.indexOf("data-stat=\"pen_fd\" >") + 22; i++)
                    {   
                            if(Character.isDigit(str.charAt(i)) )
                                Pensfor1st = Pensfor1st + str.substring(i,i+1);
                    }
                    
                    for(int i = str.indexOf("data-stat=\"time_avg\" >"); i<str.indexOf("data-stat=\"time_avg\" >") + 26; i++)
                    {   
                            if(Character.isDigit(str.charAt(i))||str.substring(i,i+1).equals(":") )
                            {    
                                TimePD = TimePD + str.substring(i,i+1);
                                TimePD = TimePD.replaceAll(":",".");
                            }   
                    }
                    
                    for(int i = str.indexOf("data-stat=\"points_avg\" >"); i<str.indexOf("data-stat=\"points_avg\" >") + 28; i++)
                    {   
                            if(Character.isDigit(str.charAt(i))||str.substring(i,i+1).equals(".") )
                                PPD = PPD + str.substring(i,i+1);
                    }
                    
                }
                
                if(str.indexOf(">Opp. Stats</th><td class=\"right \" data-stat=\"points\"") != -1)
                {
                             for(int i = str.indexOf("data-stat=\"points\" >"); i<str.indexOf("data-stat=\"points\" >") + 24; i++)
                    {   
                            if(Character.isDigit(str.charAt(i)) )
                                ptsagainst = ptsagainst + str.substring(i,i+1);
                    }
                    
                    for(int i = str.indexOf("data-stat=\"total_yards\" >"); i<str.indexOf("data-stat=\"total_yards\" >") + 29; i++)
                    {   
                            if(Character.isDigit(str.charAt(i)) )
                                ydsagainst = ydsagainst + str.substring(i,i+1);
                    }
                    
                    for(int i = str.indexOf("data-stat=\"turnovers\" >"); i<str.indexOf("data-stat=\"turnovers\" >") + 25; i++)
                    {   
                            if(Character.isDigit(str.charAt(i)) )
                                TOsForced = TOsForced + str.substring(i,i+1);
                    }
                    
                    for(int i = str.indexOf("data-stat=\"yds_per_play_offense\" >"); i<str.indexOf("data-stat=\"yds_per_play_offense\" >") + 37; i++)
                    {   
                            if(Character.isDigit(str.charAt(i))||str.substring(i,i+1).equals(".") )
                                YdsPPGU = YdsPPGU + str.substring(i,i+1);
                    }
                }
                
                if(str.indexOf(">Team Stats</th><td class=\"right \" data-stat=\"third_down_att\" >") != -1)
                {
                    for(int i = str.indexOf("data-stat=\"red_zone_pct\" >"); i<str.indexOf("data-stat=\"red_zone_pct\" >") + 30; i++)
                    {   
                            if(Character.isDigit(str.charAt(i))||str.substring(i,i+1).equals(".") )
                                RZEFF = RZEFF + str.substring(i,i+1);
                    }
                }
                
                if(str.indexOf(">Opp. Stats</th><td class=\"right \" data-stat=\"third_down_att\" >") != -1)
                {
                    for(int i = str.indexOf("data-stat=\"red_zone_pct\" >"); i<str.indexOf("data-stat=\"red_zone_pct\" >") + 30; i++)
                    {   
                            if(Character.isDigit(str.charAt(i))||str.substring(i,i+1).equals(".") )
                                RZEFFA = RZEFFA + str.substring(i,i+1);
                    }
                }
                //QB here
                if(str.indexOf(qb) != -1)
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
                                PR = PR + str.substring(i,i+1);
                        }
                    }
                    
                    if(str.indexOf("data-stat=\"pass_cmp_perc\" >") != -1 && y < 50)
                    {
                        for(int i = str.indexOf("data-stat=\"pass_cmp_perc\" >"); i<str.indexOf("data-stat=\"pass_cmp_perc\" >") + 31; i++)
                        {   
                        y = y + 50;    
                            if(Character.isDigit(str.charAt(i))||str.substring(i,i+1).equals(".") )
                                CP = CP + str.substring(i,i+1);
                        }
                    }
                    
                    if(str.indexOf("data-stat=\"pass_yds_per_att\" >") != -1 && z < 50)
                    {
                        for(int i = str.indexOf("data-stat=\"pass_yds_per_att\" >"); i<str.indexOf("data-stat=\"pass_yds_per_att\" >") + 34; i++)
                        {   
                        z = z + 50;    
                            if(Character.isDigit(str.charAt(i))||str.substring(i,i+1).equals(".") )
                                YPA = YPA + str.substring(i,i+1);
                        }
                    }
                }
                     
                if(str.indexOf("Team Total</td><td class=\"right \" data-stat=\"age\" >") != -1 && str.indexOf("data-stat=\"sacks\" >") != -1)
                {
                    for(int i = str.indexOf("data-stat=\"sacks\" >"); i<str.indexOf("data-stat=\"sacks\" >") + 21; i++)
                    {   
                            if(Character.isDigit(str.charAt(i))||str.substring(i,i+1).equals(".") )
                                SPG = SPG + str.substring(i,i+1);
                    }
                }   
                if(str.indexOf(qb) == -1 && dictionary.containsKey(team))
                {
                	BufferedReader br1 = new BufferedReader(new FileReader((String)dictionary.get(team)));
                    while((str = br1.readLine()) != null)
                    {
                    	if(str.indexOf(qb) != -1)
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
                                        PR = PR + str.substring(i,i+1);
                                }
                            }
                            
                            if(str.indexOf("data-stat=\"pass_cmp_perc\" >") != -1 && y < 50)
                            {
                                for(int i = str.indexOf("data-stat=\"pass_cmp_perc\" >"); i<str.indexOf("data-stat=\"pass_cmp_perc\" >") + 31; i++)
                                {   
                                y = y + 50;    
                                    if(Character.isDigit(str.charAt(i))||str.substring(i,i+1).equals(".") )
                                        CP = CP + str.substring(i,i+1);
                                }
                            }
                            
                            if(str.indexOf("data-stat=\"pass_yds_per_att\" >") != -1 && z < 50)
                            {
                                for(int i = str.indexOf("data-stat=\"pass_yds_per_att\" >"); i<str.indexOf("data-stat=\"pass_yds_per_att\" >") + 34; i++)
                                {   
                                z = z + 50;    
                                    if(Character.isDigit(str.charAt(i))||str.substring(i,i+1).equals(".") )
                                        YPA = YPA + str.substring(i,i+1);
                                }
                            }
                        }
                    }
                }	
            }
            
            
            double GamesPlayed = Double.parseDouble(gameswon) + Double.parseDouble(gamesplayed) + Double.parseDouble(gamestied);
            double WinPct = Double.parseDouble(gameswon)/GamesPlayed;
            double PPG = Double.parseDouble(ptsfor)/GamesPlayed;
            double PPGA = Double.parseDouble(ptsagainst)/GamesPlayed;
            double PD = PPG-PPGA;
            double YPG = Double.parseDouble(ydsfor)/GamesPlayed;
            double YAPG = Double.parseDouble(ydsagainst)/GamesPlayed;
            double TPG = Double.parseDouble(turnovers)/GamesPlayed;
            double CPPG = Double.parseDouble(Pensfor1st)/GamesPlayed;
            double TPD = Double.parseDouble(TimePD);
            if(TPD > 3)
                TPD = 3 + (TPD-3)*100/60;
            else if(TPD > 2)
                TPD = 2 + (TPD-2)*100/60;
            else
                TPD = 1 + (TPD-1)*100/60;
            double ppd = Double.parseDouble(PPD);
            double TFPG = Double.parseDouble(TOsForced)/GamesPlayed;
            double YPP = Double.parseDouble(YdsPPGU);
            double RZE = Double.parseDouble(RZEFF)/100;
            double RZEA = Double.parseDouble(RZEFFA)/100;
            if(PR.length()>0)
                pr = Double.parseDouble(PR);
            if(CP.length()>0)
                cp = Double.parseDouble(CP)/100;
            if(YPA.length()>0)
                ypa = Double.parseDouble(YPA);
            double spg = Double.parseDouble(SPG)/GamesPlayed;
            
            
            BufferedReader br2 = new BufferedReader(new FileReader("TOP"+subYear+".txt"));
            while((str = br2.readLine()) != null)
            {
                if(str.indexOf(teamName2 + "</a></td>") != -1)
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
                                TOP = TOP + str.substring(i,i+1);
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
                                Sec = Sec + str.substring(i,i+1);
                            } 
                           
                            e++;
                        } 
                    }
                    double secs = Double.parseDouble(Sec)/60.0;
                    Sec = Double.toString(secs);
                    Sec = Sec.replaceFirst("0", "");
                    TOP = TOP + Sec;
                }
            }
            double top = Double.parseDouble(TOP);
            
            QB QB = new QB();
            QB.setStats(YPG, pr, TPG, RZE, cp, ypa);   
            Coach Coach = new Coach();
            Coach.setStats(YAPG, spg, TFPG, RZEA, YPP, CPPG);
            Team Team = new Team();
            Team.setStats(WinPct, 0.5, PD, ppd, SOS, top, TPD);
            Team.QBFactor(QB);
            Team.CoachFactor(Coach);
            score = Team.calcAvgPoints();
           
        }
	
	public double getPoints(String TeamName)
	{
		
		return score;
	}
	
}
