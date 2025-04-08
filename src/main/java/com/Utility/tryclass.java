package com.Utility;

import java.io.IOException;
import java.sql.SQLException;

public class tryclass {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
//		String query="DELETE FROM mst_branch_acc_allocated WHERE ALLOCATED_DATE = TRUNC(SYSDATE)";
//		DBUtils.executeSQLStatement(query);
		String query1 = "DELETE FROM mst_callcentre_accounts WHERE ASSIGNMENT_DATE = TRUNC(SYSDATE)";
		DBUtils.executeSQLStatement(query1);
		String query2 = "DELETE FROM mst_col_agency_acc_allocated WHERE ALLOCATED_DATE = TRUNC(SYSDATE)";
		DBUtils.executeSQLStatement(query2);

//		use code
		String query = "DELETE FROM mst_branch_acc_allocated WHERE ALLOCATED_DATE = TRUNC(SYSDATE)";
		DBUtils.executeSQLStatement(query);

		String query3 = " delete from mst_branch_acc_allocated ";
		DBUtils.executeSQLStatement(query3);

		String query4 = "delete from br_user_account_link";
		DBUtils.executeSQLStatement(query4);
	}

}
