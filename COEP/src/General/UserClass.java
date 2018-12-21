package General;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class UserClass {

	public static void main(String[] argc) throws SQLException {
		UserClass u = new UserClass();
		QueryLayer q1 = new QueryLayer();
		ResultSet res1 = q1.getAllFromYear("firstYear");
		SortedList[] ls = u.sort(res1);
		q1.insertSorted(ls, "sortedFirstYear");
		QueryLayer q = new QueryLayer();
		ResultSet res = q.getAllSelectedFirstYear(80);
		System.out.println("RegId\tName\tEmail\tRank");
		while(res.next()) {
			Long regId = res.getLong(1);
			String name = res.getString(2);
			String email = res.getString(3);
			int rank = res.getInt(4);
			System.out.println(regId+"\t"+name+"\t\t"+email+"\t\t"+rank);
		}
	}
	
	public SortedList[] sort(ResultSet res) throws SQLException {
		res.last();
		SortedList[] ls = new SortedList[res.getRow()];
		res.first();
		int i=0;
		do{
			ls[i] = new SortedList();
			ls[i].setRegid(res.getLong(1));
			ls[i].setMarks(res.getFloat(5));
			i++;
		}while(res.next());
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
		for(int j=0;j<i;j++)
		{
			ls[j].setRank(j+1);
			System.out.println("reg id :- "+ls[j].getRegid()+" marks :- "+ls[j].getMarks()+" Rank :- "+ls[j].getRank());
		}
		return ls;
		 /**if(qLayer.isHostelFull(5)) {
	    	 System.out.println("Hostel is full");	 
		 }
	     else {
	    	 System.out.println("Hostel is not full");
	     }
		System.out.println("You can give preference from this list");
		for(int e=0;e<5;e++) {
			System.out.println("reg id :- "+ls[e].getRegid()+" marks :- "+ls[e].getMarks()+" Rank :- "+ls[e].getRank());			
		}
		
		System.out.println("HOW MANY ROOM PARTENERS YOU WANT? ");
 	    Scanner s = new Scanner(System.in);
		int rp=s.nextInt();
		long[] preferences = new long[rp+1];
		preferences[0] = ls[0].getRegid();
		for(int e=1;e<rp+1;e++)
			preferences[e]=s.nextLong();
		qLayer.allocateRoom(preferences);
		//givePreferences(12,preferences,80);
	}
	
	public void givePreferences(long regId,long[] pref,int maxSeat) {
		/*
		 * int[] flag=new int[200];
		int n;
	    for(int i=1;i<n+1;i++)
	    {
	    	flag[i]=0;
	    }
	    int[] roomie=new int[200];
	  

	    int count=0;
	    for(int i=1;i<n+1;i++)
	    {

	    	if(count<maxSeat)
	 		 {

	 		 if(flag[i]==0)
	 		 {   
	 			 flag[i]=1;
	 			 count++;
	 			 if(count<8)
	 			 {
	 	 		 System.out.println("TURN OF "+(i)+" CANDIDATE");
	 		     if(count+rp<=8 && rp<=3)
	 		     {
	             for(int j=0;j<rp;j++)
	             {
	     		     //System.out.println("ENTER RANK OF YOUR ROOM PARTNERS");
	     		  	// System.out.printf("SEAT NUMBER %d",i,"ROOM PARTNER");

	        	  	 System.out.println("ENTER RANK OF "+(j+1)+" ROOM PARTNER");
	            	 int k=s.nextInt();
	            	 flag[k]=1;
	                 roomie[k]=i;

	            	 count++;
	             }
	 		     }
	 		     else
	 		     {
	 		    	 if(count+rp>8)
	 		    	 {
	 	 		     System.out.println("SORRY :( HOSTEL IS FULL");
	 		    	 }
	 		    	 else if(rp>3)
	 		    	 {
	 	 	 		   System.out.println("YOU CANT HAVE MORE THAN 3 ROOM PARTNERS OF YOUR CHOICE");

	 		    	 }
	 		    	 //break;
	 		     }
	 			 }
	 		 }
	 		 
	 		 }
	 		 
	 		// System.out.println("SEAT ALLOCATED TO STUDENTS OF FOLLOWING RANKS");

	 	     /*for(int j=0;j<n;j++)
	 	     {
	 	    	 if(flag[j]==1)
	 	    	 {
	 	   		   System.out.println(rank[j+1]);

	 	    	 }
	 	     }*/
	 		 
	 	 }
		 /*System.out.println("FINAL SEAT ALLOCATION");

	     //System.out.println(rank[1]);

	     for(int i=1;i<=n;i++)
	     {
	    	 if(flag[i]==1)
	    	 {

	   		   System.out.print(rank[i]);
	     	   System.out.print("\tYOUR ROOM PARTNER IS\t");
	     	   System.out.println(roomie[i]);


	    	 }
	     }
	     QueryLayer qLayer = new QueryLayer();
	     qLayer.allocateRoom(pref);
	}*/

}

