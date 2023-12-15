package data;

import java.util.Date;

public class DataItem{
	Date ProcessedDate;
	int Hour,Min, Min_5,Min_15,Del_count, Fail_count;
	
	public DataItem( Date date,int Hour, int Min, int Min_5, int Min_15,int Del_count, int Fail_count) {
		this.ProcessedDate=date;
		this.Hour=Hour;
		this.Min =Min;
		this.Min_15=Min_15;
		this.Min_5=Min_5;
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

	public int getMin() {
		return Min;
	}
	public int getMin_5() {
		return Min_5;
	}

	public int getMin_15() {
		return Min_15;
	}

	public Date getDate() {
		return ProcessedDate;
	}
}