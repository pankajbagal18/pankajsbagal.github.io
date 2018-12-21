package General;

public class SortedList {
long regid;
float marks;
int rank;

public SortedList(long regid, float marks, int rank) {
	super();
	this.regid = regid;
	this.marks = marks;
	this.rank = rank;
}
public SortedList() {
	// TODO Auto-generated constructor stub
}
public long getRegid() {
	return regid;
}
public void setRegid(long regid) {
	this.regid = regid;
}
public float getMarks() {
	return marks;
}
public void setMarks(float marks) {
	this.marks = marks;
}
public int getRank() {
	return rank;
}
public void setRank(int rank) {
	this.rank = rank;
}

}
