package com.Utility;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.sql.Types;

import com.BasePackage.Base_Class;
import oracle.jdbc.OracleTypes;

public class DBUtils {

    public static String fetchSingleValueFromDB(String query) 
    		throws SQLException, ClassNotFoundException, IOException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String result = null;

        try {
            // Establish the database connection
            con = Base_Class.OracleDBConnection();

            // Prepare the SQL statement
            pstmt = con.prepareStatement(query);

            // Execute the query
            rs = pstmt.executeQuery();

            // Retrieve the single value from the result set
            if (rs.next()) {
                result = rs.getString(1); // Assuming the result is in the first column
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Rethrow exception to handle it further up the chain
        } finally {
            // Close resources
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        /*try {
            // Example usage
            String query = "SELECT Default_URL FROM acc_users WHERE user_id = 'IBU0000028'";
            String defaultURL = fetchSingleValueFromDB(query);
            System.out.println("Default URL: " + defaultURL);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        
    	/*String procedureCall = "{CALL SP_GET_USER_OTHERBRANCH_ACCOUNTS(?, ?)}";
        String userId = "IBU0001196"; // Input parameter

        try {
            List<String> AcNo = callStoredProcedureWithRefCursor(procedureCall, userId);
            String AccoutNumber = String.join(", ", AcNo);
            System.out.println("User Branch Account Number: " + AccoutNumber);
        } catch (Exception e) {
            System.err.println("Failed to execute stored procedure: " + e.getMessage());
        }*/
    	
    	 // Example usage with a SELECT statement
        /*String selectQuery = "SELECT column_name FROM table_name WHERE condition";
        String selectResult = executeSQLStatement(selectQuery);
        System.out.println("Select Query Result: " + selectResult);*/
    	
    	String CoreUserName="IBU0001521";
    	//String CoreUserName="IBU0000028";
    	String query ="select Default_URL from acc_users where user_id = '"+CoreUserName+"'";
	    String defaultURL = DBUtils.fetchSingleValueFromDB(query);
	    System.out.println("Default URL: " + defaultURL);

        // Example usage of executeSQLStatement with a TRUNCATE statement
		/*
		 * String truncateQuery = "TRUNCATE TABLE mst_callcentre_accounts"; String
		 * truncateResult = executeSQLStatement(truncateQuery);
		 * System.out.println("Truncate Query Result: " + truncateResult);
		 */
		 
        
        // Example usage for ExecuteAnyOracleSQLStoredProcedure
		/*
		 * List<Object> inputParams = Arrays.asList("John Doe", "john.doe@example.com",
		 * 9876543210L); List<Integer> outputTypes = Arrays.asList(Types.VARCHAR,
		 * Types.VARCHAR);
		 * 
		 * List<Object> results =
		 * ExecuteAnyOracleSQLStoredProcedure("HOUserIDGenerator", inputParams,
		 * outputTypes); System.out.println("Generated User ID: " + results.get(0));
		 * System.out.println("Default Password: " + results.get(1));
		 */
    	
    }
    
    public static List<String> callStoredProcedureWithRefCursor(String procedureCall, String parameter)
            throws SQLException, ClassNotFoundException, IOException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        List<String> results = new ArrayList<>();

        try {
            // Establish the database connection
            con = Base_Class.OracleDBConnection();

            // Prepare the callable statement
            cstmt = con.prepareCall(procedureCall);

            // Validate and set the input parameter
            if (parameter != null) {
                cstmt.setString(1, parameter); // Set the first parameter as input
            } else {
                cstmt.setNull(1, java.sql.Types.VARCHAR); // Handle null input
            }

            // Register the output parameter as a REF_CURSOR
            cstmt.registerOutParameter(2, OracleTypes.CURSOR);

            // Execute the callable statement
            cstmt.execute();

            // Retrieve the REF_CURSOR
            rs = (ResultSet) cstmt.getObject(2);

            // Process the result set
            while (rs.next()) {
                // Assuming the result contains a column 'ACCOUNT_NO'
                results.add(rs.getString("ACCOUNT_NO"));
            }

        } catch (SQLException e) {
            System.err.println("Error while calling stored procedure: " + e.getMessage());
            throw e; // Propagate the exception
        } finally {
            // Ensure resources are closed to prevent memory leaks
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ignored) {
                }
            }
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (SQLException ignored) {
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignored) {
                }
            }
        }
        return results;
    }
    
    public static String executeSQLStatement(String query) 
            throws SQLException, ClassNotFoundException, IOException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String result = null;

        try {
            // Establish the database connection
            con = Base_Class.OracleDBConnection();

            // Prepare the SQL statement
            pstmt = con.prepareStatement(query);

            // Determine the type of SQL query
            if (query.trim().toLowerCase().startsWith("select")) {
                // Execute the query if it's a SELECT statement
                rs = pstmt.executeQuery();

                // Retrieve the single value from the result set
                if (rs.next()) {
                    result = rs.getString(1); // Assuming the result is in the first column
                }
            } else if (query.trim().toLowerCase().startsWith("truncate")) {
                // Execute TRUNCATE statement
                pstmt.executeUpdate(); // TRUNCATE does not return affected rows
                result = "TRUNCATE command executed successfully.";
            } else {
                // Execute other non-query statements (like INSERT, UPDATE, DELETE)
                int affectedRows = pstmt.executeUpdate();
                result = "Query executed successfully. Rows affected: " + affectedRows;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Rethrow exception to handle it further up the chain
        } finally {
            // Close resources
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
    
    public static List<Object> ExecuteAnyOracleSQLStoredProcedure(String procedureName, List<Object> inputParams, List<Integer> outputParamTypes) throws IOException {
        Connection conn = null;
        CallableStatement stmt = null;
        List<Object> outputValues = new ArrayList<>();

        try {
            // Establish database connection using Base_Class method
            conn = Base_Class.OracleDBConnection();

            // Build procedure call string dynamically
            StringBuilder procedureCall = new StringBuilder("{ call " + procedureName + "(");
            int totalParams = inputParams.size() + outputParamTypes.size();
            procedureCall.append("?,".repeat(Math.max(0, totalParams - 1))).append("?) }");

            stmt = conn.prepareCall(procedureCall.toString());

            // Set input parameters
            for (int i = 0; i < inputParams.size(); i++) {
                stmt.setObject(i + 1, inputParams.get(i));
            }

            // Register output parameters
            int outputIndex = inputParams.size() + 1;
            for (Integer sqlType : outputParamTypes) {
                stmt.registerOutParameter(outputIndex++, sqlType);
            }

            // Execute stored procedure
            stmt.execute();

            // Retrieve output parameters
            for (int i = 0; i < outputParamTypes.size(); i++) {
                outputValues.add(stmt.getObject(inputParams.size() + 1 + i));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return outputValues;
    }  
    
}