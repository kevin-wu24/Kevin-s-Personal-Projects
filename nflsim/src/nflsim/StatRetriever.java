package nflsim;
import java.io.*;
import java.net.*;
import javax.net.ssl.HttpsURLConnection;


public class StatRetriever
{
	public static void main(String[] args) throws Exception
	{
		GameSim g = new GameSim();
        for(int i =0; i < 32; i++)
        {
        	URL url = new URL(g.TeamInfo[1][i]);
            HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
            InputStream uin = con.getInputStream();
            InputStreamReader isr = new InputStreamReader(uin);
            BufferedReader in = new BufferedReader(isr);
            String inputLine;
            
            File file = new File(g.TeamInfo[0][i]+".txt");
            FileOutputStream fos = new FileOutputStream(file);
            PrintStream ps = new PrintStream(fos);
            System.setOut(ps);
                
            while((inputLine = in.readLine()) != null)
            {    
                System.out.println(inputLine);
              
            }    
            
            in.close();
        }
        
        URL TOPURL = new URL("https://www.teamrankings.com/nfl/stat/average-time-of-possession-net-of-ot");
        HttpsURLConnection con = (HttpsURLConnection)TOPURL.openConnection();
        InputStream uin = con.getInputStream();
        InputStreamReader isr = new InputStreamReader(uin);
        BufferedReader in = new BufferedReader(isr);
        String inputLine;
        
        File TOP = new File("TOP.txt");
        FileOutputStream fos = new FileOutputStream(TOP);
        PrintStream ps = new PrintStream(fos);
        System.setOut(ps);
            
        while((inputLine = in.readLine()) != null)
        {    
            System.out.println(inputLine);         
        }    
        
        in.close(); 
        
        URL perDriveBackup = new URL("https://www.footballoutsiders.com/stats/nfl/overall-drive-statsoff/2020");
        HttpsURLConnection con1 = (HttpsURLConnection)perDriveBackup.openConnection();
        InputStream input = con1.getInputStream();
        InputStreamReader inputReader = new InputStreamReader(input);
        BufferedReader reader = new BufferedReader(inputReader);
        
        File PerDriveStats = new File("DriveStats.txt");
        FileOutputStream outputStream = new FileOutputStream(PerDriveStats);
        PrintStream ps1 = new PrintStream(outputStream);
        System.setOut(ps1);
        while((inputLine = reader.readLine()) != null)
        {
        	System.out.println(inputLine);
        }
        reader.close();
	}
}
