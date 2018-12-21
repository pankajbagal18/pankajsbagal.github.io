package General;

public class YearWiseFields {
	long regId;
	String branch;
	float marks;
	String year;
	long pref1,pref2,pref3;
	public YearWiseFields() {
		
	}
	public YearWiseFields(long regId, String branch, float marks, String year, long pref1, long pref2, long pref3) {
		super();
		this.regId = regId;
		this.branch = branch;
		this.marks = marks;
		this.year = year;
		this.pref1 = pref1;
		this.pref2 = pref2;
		this.pref3 = pref3;
	}
	public long getPref1() {
		return pref1;
	}
	public void setPref1(long pref1) {
		this.pref1 = pref1;
	}
	public long getPref2() {
		return pref2;
	}
	public void setPref2(long pref2) {
		this.pref2 = pref2;
	}
	public long getPref3() {
		return pref3;
	}
	public void setPref3(long pref3) {
		this.pref3 = pref3;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public long getRegId() {
		return regId;
	}
	public void setRegId(long regId) {
		this.regId = regId;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public float getMarks() {
		return marks;
	}
	public void setMarks(float marks) {
		this.marks = marks;
	}
}