package nflsim;

public class QB {
private double YPG, PASSERRATING, TOPG, RZEFF, COMPPCT, YPA;
    
    public QB()
    {
        YPG = 0;
        PASSERRATING = 0;
        TOPG = 0;
        RZEFF = 0;
        COMPPCT = 0;
        YPA = 0;    
    }
    
    public void setStats(double ypg, double passerrating, double topg, double rzeff, double comppct, double ypa)
    {
        YPG = ypg;
        PASSERRATING = passerrating;
        TOPG = topg;
        RZEFF = rzeff;
        COMPPCT = comppct;
        YPA = ypa;
    }
    //Complete Efficiency rating for qb
    public double calcQBIndex()
    {
        double index = 0;
        index = (YPG/300 + YPA + COMPPCT - TOPG)*(2*RZEFF)*(PASSERRATING/90); 
        return index;
    }
}
