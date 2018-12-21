package admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import General.QueryLayer;
import General.SortedList;
import General.UserClass;

public class AdminSort {
	
	
	public  void updateTables() throws SQLException
	{
		
		
				UserClass u = new UserClass();
				QueryLayer q1 = new QueryLayer();
//				ResultSet res1 = q1.getAllFromYear("firstYear");
//				SortedList[] ls = u.sort(res1);
//				q1.insertSorted(ls, "sortedFirstYear",80,ls.length);
				SortedList[] ls;
				ls=q1.sortBranchWise("itFirst");
				q1.insertSortedBranchWise(ls, "itRanklist");
				ls=q1.sortBranchWise("civilFirst");
				q1.insertSortedBranchWise(ls, "civilRanklist");
			
				ls=q1.sortBranchWise("mechFirst");
				q1.insertSortedBranchWise(ls, "mechRanklist");
				ls=q1.sortBranchWise("compFirst");
				q1.insertSortedBranchWise(ls, "compRanklist");
				ls=q1.sortBranchWise("eleFirst");
				q1.insertSortedBranchWise(ls, "eleRanklist");
				ls=q1.sortBranchWise("entcFirst");
				q1.insertSortedBranchWise(ls, "entcRanklist");
				q1.insertInMainList("sortedFirstYear");
				q1.insertInWaitingList();
		
	}

}
