package nflsim;
import java.io.*;
import java.net.*;
import javax.net.ssl.HttpsURLConnection;

public class LastYearStatRetriever
{
	static String teamurl = "";
	
	public static void main(String[] args) throws Exception
	{
		GameSim game = new GameSim();		
        for(int i =0; i < 32; i++)
        {
        	teamurl = game.TeamInfo[1][i].replaceAll(game.year, game.previousYear);
        	URL url = new URL(teamurl);
            HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
            InputStream uin = con.getInputStream();
            InputStreamReader isr = new InputStreamReader(uin);
            BufferedReader in = new BufferedReader(isr);
            String inputLine;
            
            File file = new File(game.TeamInfo[0][i]+game.previousYear+".txt");
            FileOutputStream fos = new FileOutputStream(file);
            PrintStream ps = new PrintStream(fos);
            System.setOut(ps);
                
            while((inputLine = in.readLine()) != null)
            {    
                System.out.println(inputLine);
              
            }    
            
            in.close();
        }
        
        URL url1 = new URL("https://www.teamrankings.com/nfl/stat/average-time-of-possession-net-of-ot");
        HttpsURLConnection con = (HttpsURLConnection)url1.openConnection();
        InputStream uin = con.getInputStream();
        InputStreamReader isr = new InputStreamReader(uin);
        BufferedReader in = new BufferedReader(isr);
        String inputLine;
        
        File file = new File("TOP"+game.previousYear+".txt");
        FileOutputStream fos = new FileOutputStream(file);
        PrintStream ps = new PrintStream(fos);
        System.setOut(ps);
            
        while((inputLine = in.readLine()) != null)
        {    
            System.out.println(inputLine);
          
        }    
        
        in.close();
	}
}
