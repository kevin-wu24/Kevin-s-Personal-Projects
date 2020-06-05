package nflsim;

public class lastyeartest {
	public static void main(String[] args) throws Exception
	{
		LastYearPoints l = new LastYearPoints();
		l.setPoints("LAC");
		System.out.println(l.getPoints("LAC"));
	}
}
