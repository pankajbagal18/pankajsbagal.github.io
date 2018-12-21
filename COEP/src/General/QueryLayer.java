package General;

import java.sql.*;

import admin.Query;

public class QueryLayer {
	MyConnection mcon = null;
	Connection con = null;
	PreparedStatement pst = null;
	String Userdatabase="Hackathon1";
	public QueryLayer() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean insertYearWise(YearWiseFields fields) throws SQLException {
		String query="";
		boolean state = false;
		switch(fields.getYear()) {
		case "FIRST":
			query="firstYear";
			break;
		case "SECOND":
			query="secondYear";
			break;
		case "THIRD":
			query="thirdYear";
			break;
		case "FOURTH":
			query="fourthYear";
			break;
		default:
			state = false;
		}
		query = "INSERT INTO "+query+" VALUES (?,?,?,?,?,?)";
		mcon = new MyConnection(Userdatabase);
		con = mcon.getMyConnection();
		pst = con.prepareStatement(query);
		pst.setLong(1,fields.getRegId());
		pst.setString(2,fields.getBranch());
		pst.setFloat(3, fields.getMarks());
		pst.setLong(4, fields.getPref1());
		pst.setLong(5, fields.getPref2());
		pst.setLong(6, fields.getPref3());
		int count = pst.executeUpdate();
		state = (count>0);
		return state;
	}
	
	//year is table name
	//1. sortedFirstYear
	//2. sortedSecondYear
	//3. sortedThirdYear
	//4. sortedFourthYear
	public boolean insertSorted(SortedList[] ls,String year) throws SQLException {
		boolean state = false;
		mcon = new MyConnection(Userdatabase);
		con = mcon.getMyConnection();
		PreparedStatement st = null;
		String sql = "INSERT INTO "+year+" VALUES(?,?)";
	//	String sql = "INSERT INTO sortedFirstYear VALUES(?,?)";
		String sql1="DELETE FROM "+year;
		st=con.prepareStatement(sql1);
		st.executeUpdate();
		st = con.prepareStatement(sql);
		for(SortedList l:ls) {
			st.setLong(1, l.getRegid());
			st.setLong(2, l.getRank());
			st.executeUpdate();
		}
		state = true;
		return state;
	}
	
	//year is table name
	//1. firstYear
	//2. secondYear
	//3. thirdYear
	//4. fourthYear
	//rank is table name
	//values
	//1. sortedFirstYear
	//2. sortedSecondYear
	//3. sortedThirdYear
	//4. sortedFourthYear
	public ResultSet getAllSelected(int limit,String year,String rank) throws SQLException {
		mcon = new MyConnection(Userdatabase);
		con = mcon.getMyConnection();
		ResultSet res = null;
		/*String sql = "select "+year+".id,"+year+".name,"+rank+".rank FROM "
					+year+" INNER JOIN "+rank+" ON "+rank+".regId="+year+".id order by "+rank+
					".rank limit "+limit+"";*/
		String sql = "select firstYear.id,firstYear.name,firstYear.email_id,sortedFirstYear.rank FROM firstYear INNER JOIN sortedFirstYear ON sortedFirstYear.regId=firstYear.id order by sortedFirstYear.rank limit 80";
		System.out.println(sql);
		//select firstYear.id,firstYear.name,sortedFirstYear.rank 
		//FROM firstYear INNER JOIN sortedFirstYear ON 
		//sortedFirstYear.regId=firstYear.id order by sortedFirstYear.rank limit 80;
		PreparedStatement pst = con.prepareStatement(sql);
		res = pst.executeQuery();
		return res;
	}
	
	public ResultSet getAllSelectedFirstYear(int limit) throws SQLException{
		ResultSet res = null;
		mcon = new MyConnection(Userdatabase);
		con = mcon.getMyConnection();
		String sql = "select firstYear.id,firstYear.name,firstYear.email_id,sortedFirstYear.rank,firstYear.branch FROM firstYear INNER JOIN sortedFirstYear ON sortedFirstYear.regId=firstYear.id order by sortedFirstYear.rank limit 80";
		PreparedStatement pst = con.prepareStatement(sql);
		res = pst.executeQuery();
		return res;
	}
	
	
	public ResultSet getAllSelectedSecondYear(int limit) throws SQLException{
		ResultSet res = null;
		mcon = new MyConnection(Userdatabase);
		con = mcon.getMyConnection();
		String sql = "select secondYear.id,secondYear.name,secondYear.email_id,sortedSecondYear.rank FROM secondYear INNER JOIN sortedSecondYear ON sortedSecondYear.regId=secondYear.id order by sortedSecondYear.rank limit 80";
		PreparedStatement pst = con.prepareStatement(sql);
		res = pst.executeQuery();
		return res;
	}
	//year is table name
	//1. firstYear
	//2. secondYear
	//3. thirdYear
	//4. fourthYear
	public ResultSet getAllFromYear(String year) throws SQLException {
		ResultSet res=null;
		String sql = "SELECT * FROM  "+year;
		mcon = new MyConnection(Userdatabase);
		con = mcon.getMyConnection();
		pst = con.prepareStatement(sql);
		res = pst.executeQuery();
		return res;
	}
	
	public void allocateRoom(long regId[]) throws SQLException {
		mcon = new MyConnection(Userdatabase);
		con = mcon.getMyConnection();
		String sql = "";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet res = st.executeQuery("SELECT COUNT(DISTINCT roomId) FROM allocated");
		if(res.next()) {
			int newId = res.getInt(1);
			newId++;
			st = con.prepareStatement("INSERT INTO allocated VALUES (?,?)");
			for(int i=0;i<regId.length;i++) {
				st.setLong(1,newId);
				st.setLong(2, regId[i]);
				st.executeUpdate();
			}
		}
	}
	
	//year is table name
	//1. firstYear
	//2. secondYear
	//3. thirdYear
	//4. fourthYear
	//outPut
	//1. id, 2. name, 3.address, 4.cont_no, 5.email_id, 6.branch
	public ResultSet getStudentProfile(Long regId,String year) throws SQLException {
		ResultSet res = null;
		System.out.println(regId);
		mcon = new MyConnection(Userdatabase);
		con = mcon.getMyConnection();
		String sql = "SELECT id,name,address,cont_no,email_id,branch,category FROM "+year+" where id="+regId;
		PreparedStatement pst = con.prepareStatement(sql);
		res = pst.executeQuery();
		return res;
	}
	
	public boolean isInMeritList(long regId,String year) throws SQLException
	{
		
		int count=0;
		ResultSet res = null;
		mcon = new MyConnection(Userdatabase);
		con = mcon.getMyConnection();
		String sql = "SELECT regId FROM "+year+" where regId="+regId+" and rank<81";
		PreparedStatement pst = con.prepareStatement(sql);
		res = pst.executeQuery();
		while(res.next())
			count++;
		if(count==1)
			return true;
		
		return false;
	}
	
	public boolean isHostelFull(int maxSeats) throws SQLException {
		boolean state = false;
		mcon = new MyConnection(Userdatabase);
		con = mcon.getMyConnection();
		String sql = "SELECT COUNT(DISTINCT regId) FROM allocated";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet res = st.executeQuery();
		if(res.next()) {
			if(res.getInt(1)>=maxSeats) {
				state = true;
			}
			else {
				state = false;
			}
		}
		return state;
	}
	public int getRank(long regid) throws SQLException
	{
		mcon = new MyConnection(Userdatabase);
		con = mcon.getMyConnection();
		String sql,sql1;
		sql="select rank from sortedFirstYear where regId="+regid;
		sql1="select rank from waitingList where id="+regid;
		pst = con.prepareStatement(sql);
		ResultSet rs=pst.executeQuery();
		int k=0;
		if(rs.next())
		{
			// k=rs.getInt("rank");
			return rs.getInt("rank");
		}
		pst = con.prepareStatement(sql1);
		 rs=pst.executeQuery();
		
		if(rs.next())
		{
			// k=rs.getInt("rank");
			return rs.getInt("rank");
		}
		return 0;
	}
	public int getGivePref(long regid) throws SQLException{
		mcon = new MyConnection(Userdatabase);
		con = mcon.getMyConnection();
		String sql;
		sql="select * from givenPreferences where id="+regid;
		pst = con.prepareStatement(sql);
		ResultSet rs=pst.executeQuery();
		int k=0;
		if(rs.next())
		{
			if(rs.getLong("pref1")!=0)
				k++;
			if(rs.getLong("pref2")!=0)
				k++;
			if(rs.getLong("pref3")!=0)
				k++;
			
		}
		return k;
	}
	public int getRecPref(long regid) throws SQLException{
		mcon = new MyConnection(Userdatabase);
		con = mcon.getMyConnection();
		String sql;
		sql="select * from receivedPreferences where id="+regid;
		pst = con.prepareStatement(sql);
		ResultSet rs=pst.executeQuery();
		int k=0;
		if(rs.next())
		{
			if(rs.getLong("pref1")!=0)
				k++;
			if(rs.getLong("pref2")!=0)
				k++;
			if(rs.getLong("pref3")!=0)
				k++;
			
		}
		return k;
	}
	public void insertPreferences(long regid,long[] pref) throws SQLException {
		mcon = new MyConnection(Userdatabase);
		con = mcon.getMyConnection();
		String sql;
		int len=0;
		System.out.println(pref.length);
		SNR(regid);
		for(long i:pref)
		{
			if(i!=0)
				len++;
		}
		PreparedStatement pst = null;
		if(len==3) {
			String sql1="UPDATE givenPreferences SET pref1=NULL,pref2=NULL,pref3=NULL WHERE id="+regid;
			pst = con.prepareStatement(sql1);
			pst.executeUpdate();
			sql = "UPDATE givenPreferences SET pref1=?,pref2=?,pref3=? WHERE id=?";
			pst = con.prepareStatement(sql);
			
		}
		else if(len==2) {
			String sql1="UPDATE givenPreferences SET pref1=NULL,pref2=NULL,pref3=NULL WHERE id="+regid;
			pst = con.prepareStatement(sql1);
			pst.executeUpdate();
			sql = "UPDATE givenPreferences SET pref1=?,pref2=? WHERE id=?";
			pst = con.prepareStatement(sql);
		}
		else {
			String sql1="UPDATE givenPreferences SET pref1=NULL,pref2=NULL,pref3=NULL WHERE id="+regid;
			pst = con.prepareStatement(sql1);
			pst.executeUpdate();
			sql = "UPDATE givenPreferences SET pref1=? WHERE id=?";
			pst = con.prepareStatement(sql);
		}
		
		for(int i=0;i<len;i++) {
			pst.setLong(i+1, pref[i]);
		}
		pst.setLong(len+1, regid);
		pst.executeUpdate();
		
		sql = "SELECT * FROM receivedPreferences WHERE id=?";
		//String sql2="UPDATE receivedPreferences SET pref1=0,pref2=0,pref3=0";
		pst = con.prepareStatement(sql);
		ResultSet res = null;
		for(int i=0;i<len;i++) {
			System.out.println(pref[i]);
			pst.setLong(1, pref[i]);
			res = pst.executeQuery();
			res.next();
			if(res.getLong(2)==0) {
				//PreparedStatement pst3 = con.prepareStatement(sql2);
				//pst3.executeUpdate();
				String sql1 = "UPDATE receivedPreferences SET pref1=? WHERE id=?";
				PreparedStatement pst2 = con.prepareStatement(sql1);
				pst2.setLong(1, regid);
				pst2.setLong(2, pref[i]);
				pst2.executeUpdate();
			}
			else if(res.getLong(3)==0) {
				//PreparedStatement pst3 = con.prepareStatement(sql2);
				//pst3.executeUpdate();
				String sql1 = "UPDATE receivedPreferences SET pref2=? WHERE id=?";
				PreparedStatement pst2 = con.prepareStatement(sql1);
				pst2.setLong(1, regid);
				pst2.setLong(2, pref[i]);
				pst2.executeUpdate();
			}
			else if(res.getLong(4)==0) {
			//	PreparedStatement pst3 = con.prepareStatement(sql2);
			//	pst3.executeUpdate();
				String sql1 = "UPDATE receivedPreferences SET pref3=? WHERE id=?";
				PreparedStatement pst2 = con.prepareStatement(sql1);
				pst2.setLong(1, regid);
				pst2.setLong(2, pref[i]);
				pst2.executeUpdate();
			}
		}
	}
	
	
	
	public void acceptRequest(long regid1,long regid2) throws SQLException {
		mcon = new MyConnection(Userdatabase);
		con = mcon.getMyConnection();
		String sql = "SELECT * FROM givenPreferences WHERE id=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setLong(1,regid2);
		ResultSet res = st.executeQuery();
		res.next();
		if(res.getLong(2)==regid1)
		{
			String sql1 = "UPDATE givenPreferences SET preflag1=? WHERE id=?";
			PreparedStatement pst2 = con.prepareStatement(sql1);
			pst2.setInt(1, 1);
			pst2.setLong(2, regid2);
			pst2.executeUpdate();
		}
		else if(res.getLong(3)==regid1)
		{
			String sql1 = "UPDATE givenPreferences SET preflag2=? WHERE id=?";
			PreparedStatement pst2 = con.prepareStatement(sql1);
			pst2.setInt(1, 1);
			pst2.setLong(2, regid2);
			pst2.executeUpdate();
		}
		else if(res.getLong(4)==regid1)
		{
			String sql1 = "UPDATE givenPreferences SET preflag3=? WHERE id=?";
			PreparedStatement pst2 = con.prepareStatement(sql1);
			pst2.setInt(1, 1);
			pst2.setLong(2, regid2);
			pst2.executeUpdate();
		}
	}
	
	public ResultSet showReceivedPreferences(long regid) throws SQLException {
		long[] pref = new long[3];
		mcon = new MyConnection(Userdatabase);
		con = mcon.getMyConnection();
		String sql = "SELECT * FROM receivedPreferences WHERE id=?";
		PreparedStatement st = con.prepareStatement(sql);		
		st.setLong(1, regid);
		ResultSet res = st.executeQuery();
		/*if(res.next()) {
			if(res.getLong(2)!=0) {
				pref[0] = res.getLong(2);
			}
			if(res.getLong(3)!=0) {
				pref[1] = res.getLong(3);
			}
			if(res.getLong(4)!=0) {
				pref[2] = res.getLong(4);
			}
		}*/
		return res;
	}
	
	public ResultSet showGivenPreferences(long id) throws SQLException {
		long[] pref = new long[3];
		mcon = new MyConnection(Userdatabase);
		con = mcon.getMyConnection();
		String sql = "SELECT * FROM givenPreferences WHERE id=?";
		PreparedStatement st = con.prepareStatement(sql);		
		st.setLong(1, id);
		ResultSet res = st.executeQuery();
		/*if(res.next()) {
			if(res.getLong(2)!=0) {
				pref[0] = res.getLong(2);
			}
			if(res.getLong(3)!=0) {
				pref[1] = res.getLong(3);
			}
			if(res.getLong(4)!=0) {
				pref[2] = res.getLong(4);
			}
		}*/
		return res;
	}
	public long[] resubmitPref(long id) throws SQLException
	{
		long[] pref=new long[3];
		String sql="SELECT pref1,pref2,pref3 FROM givenPreferences where id="+id;
		mcon = new MyConnection(Userdatabase);
		con = mcon.getMyConnection();
		PreparedStatement st = con.prepareStatement(sql);	
		ResultSet res=st.executeQuery();
		while(res.next())
		{
			if(res.getLong("pref1")!=0)
				pref[0]=res.getLong("pref1");
			if(res.getLong("pref2")!=0)
				pref[1]=res.getLong("pref2");
			if(res.getLong("pref3")!=0)
				pref[2]=res.getLong("pref3");
			
		}
		return pref;
	}
	
	public void SNR(long id) throws SQLException
	{
		String sql1="update receivedPreferences set pref1=NULL where pref1="+id;
		String sql2="update receivedPreferences set pref2=NULL where pref2="+id;
		String sql3="update receivedPreferences set pref3=NULL where pref3="+id;
		
		mcon = new MyConnection(Userdatabase);
		con = mcon.getMyConnection();
		PreparedStatement st = con.prepareStatement(sql1);		
		 st.executeUpdate();
		 st = con.prepareStatement(sql2);		
		 st.executeUpdate();
		 st = con.prepareStatement(sql3);		
		 st.executeUpdate();
	}
	
	
	public void allocateRoom() throws SQLException {
		mcon = new MyConnection(Userdatabase);
		con = mcon.getMyConnection();
		String sql = "SELECT * FROM givenPreferences";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet res = st.executeQuery();
		int count=0;
		ResultSet res1 = null;
		int roomid;
		long[] id = new long[4];
		while(res.next()) {
			count=0;
			if(res.getInt(8)==0) {
					
				if(res.getInt(5)!=0) {
					count++;
				}
				if(res.getInt(6)!=0) {
					count++;
				}
				if(res.getInt(7)!=0) {
					count++;
				}
				System.out.println("count :- "+count+"\tid :- "+res.getLong(1));
				if(count>=2) {
					String sql1 = "SELECT COUNT(DISTINCT roomId) FROM allocatedFirstYear";
					PreparedStatement st2 = con.prepareStatement(sql1);
					res1 = st2.executeQuery();
					res1.next();
					roomid = res1.getInt(1) + 1;
					id[0] = res.getLong(1);
					id[1] = res.getLong(2);
					id[2] = res.getLong(3);
					id[3] = res.getLong(4);
					String sql2 = "INSERT INTO allocatedFirstYear VALUES(?,?)";
					PreparedStatement st3 = con.prepareStatement(sql2);
					for(int i=1;i<=4;i++) {
						st3.setInt(1, roomid);
						if(id[i-1]!=0) {
							st3.setLong(2, id[i-1]);
							st3.executeUpdate();
						}
						else continue;
					}
					String sql3 = "UPDATE givenPreferences SET allocated=? WHERE id=?";
					PreparedStatement st4 = con.prepareStatement(sql3);
					st4.setInt(1, 1);
					for(int i=1;i<=4;i++)
					{
						if(id[i-1]!=0) {
							st4.setLong(2, id[i-1]);
							st4.executeUpdate();
						}
					}

				}
			}
			else
			{
				continue;
			}
		}
	}
	
	public void allocateSecondTime() throws SQLException  {
		mcon = new MyConnection(Userdatabase);
		con = mcon.getMyConnection();
		String sql = "SELECT * FROM givenPreferences WHERE allocated=?";
		int flag=0;
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, 0);
		ResultSet res = st.executeQuery();
		long[] ls = new long[4];
		int i=0;
		while(res.next()){
			if(res.getInt(8)==0) {
				ls[i] = res.getLong(1);
				if(i==3) {
					String sql1 = "INSERT INTO allocatedFirstYear VALUES(?,?)";
					PreparedStatement st1 = con.prepareStatement(sql1);
					
					String sql2 = "SELECT COUNT(DISTINCT roomId) FROM allocatedFirstYear";
					PreparedStatement st2 = con.prepareStatement(sql2);
					ResultSet res2 = st2.executeQuery();
					res2.next();
					int roomid = res2.getInt(1) + 1;
					for(int j=1;j<=4;j++) {
						st1.setInt(1, roomid);
						st1.setLong(2, ls[j-1]);
						st1.executeUpdate();
						String sql3 = "UPDATE givenPreferences SET allocated=? WHERE id=?";
						PreparedStatement st4 = con.prepareStatement(sql3);
						st4.setInt(1, 1);
						st4.setLong(2, ls[j-1]);
						st4.executeUpdate();
					}
					i=0;
					flag=1;
				}
				i++;
				if(flag==1)
				{
					flag=0;
					i=0;
				}
			}
		}
	}

	public long[] sendAllocationStatus(long regid) throws SQLException {
		long[] id = new long[5];
		mcon = new MyConnection(Userdatabase);
		con = mcon.getMyConnection();
		String sql = "SELECT * FROM allocatedFirstYear WHERE regid=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setLong(1, regid);
		ResultSet res = st.executeQuery();
		res.next();
		id[0] = res.getInt(1);
		String sql2 = "SELECT * FROM allocatedFirstYear WHERE roomid=?";
		PreparedStatement st1 = con.prepareStatement(sql2);
		st1.setInt(1,res.getInt(1));
		ResultSet res1 = st1.executeQuery();
		int i=1;
		while(res1.next()) {
			id[i] = res1.getLong(2);
			i++;
		}
		return id;
	}
	public SortedList[] sortBranchWise(String branch) throws SQLException {
		String sql = "SELECT * FROM "+branch;
		mcon = new MyConnection(Userdatabase);
		con = mcon.getMyConnection();
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet res = st.executeQuery();
		res.last();
		int no = res.getRow();
		SortedList[] ls = new SortedList[no];
		res.first();
		int i=0;
		String category="";
		int count=0;
		do {
			category=res.getString(3);
			if(category.equals("OPEN")) {
				ls[i] = new SortedList();
				ls[i].setRegid(res.getLong(1));
				ls[i].setMarks(res.getInt(2));
				System.out.println("regid :- "+ls[i].getRegid()+"marks :- "+ls[i].getMarks());
				count++;
				i++;
			}
		}while(res.next());
		System.out.println("--------------------");
		int n=i;
		for(int j=0;j<n-1;j++) {
			for(int k=0;k<n-j-1;k++) {
				if(ls[k].getMarks()<ls[k+1].getMarks()) {
					SortedList l = ls[k];
					ls[k] = ls[k+1];
					ls[k+1] = l;
				}
			}
		}
		for(int j=0;j<count;j++) {
			ls[j].setRank(j+1);
		}
		for(int j=0;j<count;j++) {
			System.out.println("regid :- "+ls[j].getRegid()+"marks :- "+ls[j].getMarks()+"rank :- "+ls[j].getRank());
		}
		
		System.out.println("====================================");
		
		res.first();
		SortedList[] ls2 = new SortedList[no];
		int count1=0;
		i=0;
		do {
			category=res.getString(3);
			if(category.equals("OTHER")) {
				ls2[i] = new SortedList();
				ls2[i].setRegid(res.getLong(1));
				ls2[i].setMarks(res.getInt(2));
				System.out.println("regid :- "+ls2[i].getRegid()+"marks :- "+ls2[i].getMarks());
				count1++;
				i++;
			}
		}while(res.next());
		System.out.println("--------------------");
		n=i;
		for(int j=0;j<n-1;j++) {
			for(int k=0;k<n-j-1;k++) {
				if(ls2[k].getMarks()<ls2[k+1].getMarks()) {
					SortedList l = ls2[k];
					ls2[k] = ls2[k+1];
					ls2[k+1] = l;
				}
			}
		}
		for(int j=0;j<count1;j++) {
			ls2[j].setRank(j+1);
		}
		for(int j=0;j<count1;j++) {
			System.out.println("regid :- "+ls2[j].getRegid()+"marks :- "+ls2[j].getMarks()+"rank :- "+ls2[j].getRank());
		}
		
		System.out.println("count :- "+count);
		System.out.println("count1 :- "+count1);
		SortedList[] ls3 = new SortedList[no];
		int count3=0;
		if(count>=6) {
			for(int j=0;j<6;j++) {
				int index = count3++;
				ls3[index] = new SortedList();
				ls3[index].setRegid(ls[j].getRegid());
				ls3[index].setMarks(ls[j].getMarks());
				
			}
			if(count1>=4) {
				for(int j=0;j<4;j++) {
					int index = count3++;
					ls3[index] = new SortedList();
					ls3[index].setRegid(ls2[j].getRegid());
					ls3[index].setMarks(ls2[j].getMarks());
					
				}	
			}
			else {
				for(int j=0;j<count1;j++) {
					int index = count3++;
					ls3[index] = new SortedList();
					ls3[index].setRegid(ls2[j].getRegid());
					ls3[index].setMarks(ls2[j].getMarks());
				}
			}
			
			for(int j=6;j<count;j++) {
				int index = count3++;
				ls3[index] = new SortedList();
				ls3[index].setRegid(ls[j].getRegid());
				ls3[index].setMarks(ls[j].getMarks());
			}
			if(count1>=4) {
				for(int j=4;j<count1;j++) {
					int index = count3++;
					ls3[index] = new SortedList();
					ls3[index].setRegid(ls2[j].getRegid());
					ls3[index].setMarks(ls2[j].getMarks());
				}
			}
		}
		else {
			for(int j=0;j<count;j++) {
				int index = count3++;
				ls3[index] = new SortedList();
				ls3[index].setRegid(ls[j].getRegid());
				ls3[index].setMarks(ls[j].getMarks());				
				
			}
			
			if(count1>=6) {
				for(int j=count;j<10;j++) {
					int index = count3++;
					ls3[index] = new SortedList();
					ls3[index].setRegid(ls2[j].getRegid());
					ls3[index].setMarks(ls2[j].getMarks());
				}
			}
			else {
				for(int j=count;j<count+count1;j++) {
					int index =count3++;
					ls3[index] = new SortedList();
					ls3[index].setRegid(ls2[j].getRegid());
					ls3[index].setMarks(ls2[j].getMarks());
					
				}
			}
			
		}
		System.out.println("count3 :- "+count3);
		for(int j=0;j<no;j++) {
			ls3[j].setRank(j+1);
		}
		return ls3;
		//		int flag=0;
//		SortedList[] ls2 = new SortedList[no];
//		if(count>=6) {
//			for(int j=0;j<6;j++) {
//				ls2[j] = ls[i];
//			}
//		}
//		else {
//			for(int j=0;j<count;j++) {
//				ls2[j] = ls[i];
//			}
//			flag=1;
//		}
//		res.first();
//		i=0;
//		SortedList[] ls3 = new SortedList[no];
//		do {
//			category=res.getString(3);
//			if(category=="OTHER") {
//				ls3[i].setRegid(res.getLong(1));
//				ls3[i].setMarks(res.getInt(2));
//				i++;
//			}
//		}while(res.next());
//		res.first();
//		n=i;
//		for(int j=0;j<n-1;j++) {
//			for(int k=0;k<n-j-1;k++) {
//				if(ls[k].getMarks()<ls3[k+1].getMarks()) {
//					SortedList l = ls3[k];
//					ls3[k] = ls3[k+1];
//					ls3[k+1] = l;
//				}
//			}
//		}
//		
//		int start;
//		if(flag==1) {
//			start=count;
//		}
//		else {
//			start=6;
//		}
//		i=0;
//		while(start!=10) {
//			ls2[start] = ls3[i];
//			i++;
//			start++;
//		}
//		for(int j=0;j<10;j++) {
//			ls2[j].setRank(j+1);
//		}
//		return ls2;
//		do{
//			ls[i] = new SortedList();
//			ls[i].setRegid(res.getLong(1));
//			i++;
//		}while(res.next());
//		int n=i;
//		for(int j=0;j<n-1;j++) {
//			for(int k=0;k<n-j-1;k++) {
//				if(ls[k].getMarks()<ls[k+1].getMarks()) {
//					SortedList l = ls[k];
//					ls[k] = ls[k+1];
//					ls[k+1] = l;
//				}
//			}
//		}
//		for(int j=0;j<i;j++)
//		{
//			ls[j].setRank(j+1);
//			System.out.println("reg id :- "+ls[j].getRegid()+" marks :- "+ls[j].getMarks()+" Rank :- "+ls[j].getRank());
//		}
//		return ls;
	
	}
	
	public void insertSortedBranchWise(SortedList[] ls,String table) throws SQLException {
		String sql = "INSERT INTO "+table+" VALUES(?,?)";
		String sql1="DELETE FROM "+table;
		mcon = new MyConnection(Userdatabase);
		con = mcon.getMyConnection();
		
		PreparedStatement st = con.prepareStatement(sql1);
		st.executeUpdate();
		 st = con.prepareStatement(sql);
		for(SortedList l : ls) {
			st.setLong(1, l.getRegid());
			st.setInt(2, l.getRank());
			st.executeUpdate();
		}
	}
	
	public void insertInMainList(String table) throws SQLException{
		String sql = "INSERT INTO "+table+" VALUES(?,?)";

		mcon = new MyConnection(Userdatabase);
		con = mcon.getMyConnection();
		String sql3 = "DELETE FROM givenPreferences";
		String sql4 = "DELETE FROM receivedPreferences";
		String sql5 = "DELETE FROM "+table;
		PreparedStatement st3 = con.prepareStatement(sql3);
		PreparedStatement st4 = con.prepareStatement(sql4);
		PreparedStatement st5 = con.prepareStatement(sql5);
		st3.executeUpdate();
		st4.executeUpdate();
		st5.executeUpdate();
		
		
		PreparedStatement st = con.prepareStatement(sql);
		String[] sql1 = {"SELECT * FROM civilRanklist limit 10","SELECT * FROM mechRanklist limit 10","SELECT * FROM compRanklist limit 10"
				,"SELECT * FROM itRanklist limit 10","SELECT * FROM entcRanklist limit 10","SELECT * FROM eleRanklist limit 10"};
		ResultSet res = null;
		PreparedStatement st1 = null;
		for(int i=0;i<6;i++) {
			System.out.println(sql1[i]);
			st1 = con.prepareStatement(sql1[i]);
			res = st1.executeQuery();
			while(res.next()) {
			st.setLong(1, res.getLong(1));
			st.setInt(2, res.getInt(2));
			st.executeUpdate();
			}
		}
	}
	
	public void insertInWaitingList() throws SQLException {
		String sql = "INSERT INTO waitingList VALUES(?,?)";
		String sql2="DELETE FROM waitingList";

		mcon = new MyConnection(Userdatabase);
		con = mcon.getMyConnection();
		PreparedStatement st = con.prepareStatement(sql);
		String[] sql1 = {"SELECT * FROM civilRanklist","SELECT * FROM mechRanklist","SELECT * FROM compRanklist"
				,"SELECT * FROM itRanklist","SELECT * FROM entcRanklist","SELECT * FROM eleRanklist"};
		ResultSet res = null;
		PreparedStatement st1 = null;
		st1 = con.prepareStatement(sql2);
		st1.executeUpdate();
		int count=0;
		for(int i=0;i<6;i++) {
			st1 = con.prepareStatement(sql1[i]);
			res = st1.executeQuery();
			count=0;
			while(res.next()) {
				count++;
				if(count>10) {
					st.setLong(1, res.getLong(1));
					st.setInt(2, res.getInt(2));
					st.executeUpdate();	
				}
			}
		}
	}
	public ResultSet showBranchWise(String branch) throws SQLException
	{
		mcon = new MyConnection(Userdatabase);
		con = mcon.getMyConnection();
		String sql = "SELECT * FROM "+branch+" limit 10";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		
		return rs;

	}
	public ResultSet showBranchWiseWL(String branch) throws SQLException
	{
		mcon = new MyConnection(Userdatabase);
		con = mcon.getMyConnection();
		String sql = "SELECT * FROM "+branch+" limit 10,50 ";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		
		return rs;

	}
	
	public ResultSet adminQuery() throws SQLException {
		mcon = new MyConnection(Userdatabase);
		con = mcon.getMyConnection();
		String sql = "SELECT * FROM allocatedFirstYear ";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		
		return rs;
	}
	public void allocateThirdTime() throws SQLException {
		String sql = "SELECT roomid FROM allocatedFirstYear group by roomid having count(roomid)<4";
		mcon = new MyConnection(Userdatabase);
		con = mcon.getMyConnection();
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet res = st.executeQuery();
		String sql2 = "SELECT * FROM givenPreferences";
		PreparedStatement st2 = con.prepareStatement(sql2);
		ResultSet res1 = null;
		int roomid;
		while(res.next()) {
			roomid = res.getInt(1);
			res1 = st2.executeQuery();;
			while(res1.next()) {
				if(res1.getLong(8)==0) {
					String sql3 = "INSERT INTO allocatedFirstYear VALUES(?,?)";
					PreparedStatement st3 = con.prepareStatement(sql3);
					st3.setInt(1, roomid);
					st3.setLong(2, res1.getLong(1));
					st3.executeUpdate();
					String sql4 = "UPDATE givenPreferences SET allocated=? WHERE id=?";
					PreparedStatement st4 = con.prepareStatement(sql4);
					st4.setInt(1, 1);
					st4.setLong(2, res1.getLong(1));
					st4.executeUpdate();
					break;
				}
			}
		}
	}
	
	public String[] getSelectedEmails() throws SQLException {
		String sql = "select email_id from firstYear where id in (SELECT id FROM givenPreferences where allocated=1)";
		mcon = new MyConnection(Userdatabase);
		con = mcon.getMyConnection();
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet res = st.executeQuery();
		res.last();
		String[] add = new String[res.getRow()];
		res.first();
		int i=0;
		do {
			add[i] = res.getString(1);
			i++;
		}while(res.next());
		return add;
	}
	
	
	
}
