package nflsim;

public class Coach {
private double YAPG, TOFPG, SACKSPG, YPP, RZEFF, CriticalPenaltiesPG;
    
    public Coach()
    {
        YAPG = 0;
        SACKSPG = 0;
        TOFPG = 0;
        RZEFF = 0;
        CriticalPenaltiesPG = 0;
        YPP = 0;
    }
    
    public void setStats(double ypg, double sackspg, double topg, double rzeff, double YPPlay, double CPPG)
    {
        YAPG = ypg;
        SACKSPG = sackspg;
        TOFPG = topg;
        RZEFF = rzeff;
        CriticalPenaltiesPG = CPPG;
        YPP = YPPlay;
    }
    //Complete Efficiency rating for qb
    public double calcCoachIndex()
    {
    	if(RZEFF == 0)
    	{
    		RZEFF = 0.2;
    	}
        double index = 0;
        index = (TOFPG + SACKSPG+YPP/3.5-CriticalPenaltiesPG)/(2*RZEFF)/(YAPG/375); 
        if(index < 0)
            index = 0.0;
        return index;
    }
}
