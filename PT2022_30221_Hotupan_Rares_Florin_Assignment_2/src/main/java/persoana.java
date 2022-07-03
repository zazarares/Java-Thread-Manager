
public class persoana {
	private static int gindex=1;
	private int index;
	private int arrivaltime;
	private int arrivaltimecopy;
	private int servicetime;
	private int servicetimecopy;
	persoana(){
		index=gindex;
		arrivaltime=0;
		servicetime=0;
		gindex++;
	}
	persoana(int arrivaltime,int servicetime)
	{
		index=gindex;
		this.arrivaltime=arrivaltime;
		this.servicetime=servicetime;
		arrivaltimecopy=arrivaltime;
		servicetimecopy=servicetime;
		gindex++;

	}
	protected void generatetimes(int minservice,int maxservice,int minarrival,int maxarrival)
	{
		arrivaltime=(int) ((Math.random() * (maxarrival - minarrival)) + minarrival);
		servicetime=(int) ((Math.random() * (maxservice - minservice)) + minservice);
		arrivaltimecopy=arrivaltime;
		servicetimecopy=servicetime;
	}
	protected int getarrival()
	{
		return arrivaltime;
	}
	protected int getarrivalcopy()
	{
		return arrivaltimecopy;
	}
	protected void setarrival()
	{
		arrivaltime=-1;
	}
	protected int getservice()
	{
		return servicetime;
	}
	protected void setservice()
	{
		servicetime--;
	}
	protected int getindex()
	{
		return index;
	}
	protected int getservicecopy()
	{
		return servicetimecopy;
	}
}
