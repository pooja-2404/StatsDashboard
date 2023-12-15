package data;
public class DataItemHour{
	
	int Hour,Del_count, Fail_count;
	
	public DataItemHour( int Hour,int Del_count, int Fail_count) {
		
		this.Hour=Hour;
		this.Del_count=Del_count;
		this.Fail_count=Fail_count;
	}

	public int getdcount() {
		return Del_count;
	}

	public int getfcount() {
		return Fail_count;
	}
	public int getHour() {
		return Hour;
	}

	
}