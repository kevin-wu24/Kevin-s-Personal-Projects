package nflsim;

public class Team {
private double WinPct, HistoryToWin, PointDiff, PPDrive, StrengthOSched, TimeOPoss, TimePDrive, QB, Coach;
    
    public Team()
    {
        WinPct = 0;
        HistoryToWin = 0;
        PointDiff = 0;
        PPDrive =0;
        StrengthOSched = 0;
        TimeOPoss = 0;
        TimePDrive = 0;
    }
    
    public void setStats(double WP, double HTW, double Pd, double PPd, double Sos, double Top, double Tpd)
    {
        WinPct = WP;
        HistoryToWin = HTW;
        PointDiff = Pd;
        PPDrive =PPd;
        StrengthOSched = Sos;
        TimeOPoss = Top;
        TimePDrive = Tpd;
    }
    //+ indicates QB advantage for your team, - indicates QB advantage for other team
    public void QBFactor(QB a)
    {
       QB = a.calcQBIndex();
    }
    // same as QBSway but with coaching matchups
    public void CoachFactor(Coach a)
    {
        Coach = a.calcCoachIndex();
    }
    
    public double calcEffWinPct()
    {
        double p = 1;
        if(StrengthOSched < 0.49)
            p = p-(0.5-StrengthOSched);
        if(StrengthOSched > 0.5)
            p = p+(StrengthOSched-0.5);
            
        return p*WinPct;
    }
    
    public double calcPoints()
    {
        // put your code here
        double temp = 0;
        temp = calcEffWinPct()*PointDiff*1.1 + (HistoryToWin + QB + Coach)+((Math.random()*(TimeOPoss/TimePDrive))*PPDrive+PointDiff*0.1);
        if(temp > 30)
        {
            temp = 30 + Math.log(temp);
        }
        return temp; 
    }
    
       
    public double calcAvgPoints()
    {
        double avg = 0;
        for(int i = 0; i < 1000; i++)
        {
            avg+=calcPoints();
        }
        return avg/1000;
        
    }
}
