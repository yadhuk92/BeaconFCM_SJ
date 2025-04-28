package com.BasePackage;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    	
    	List<Object> inputParams = Arrays.asList("IBU0001196"); 
    	List<Integer> outputTypes = Arrays.asList(Types.VARCHAR,Types.VARCHAR,Types.VARCHAR);
    	List<Object> results = ExecuteAnyOracleSQLStoredProcedure("SP_QA_GET_COLLECTION_AGENCY_INFO",inputParams,outputTypes);
    	System.out.println("Collection agency ID: " + results.get(0));
    	System.out.println("Collection agency name: " + results.get(1));
    	System.out.println("Status message: " + results.get(2));
        
		/*
		 * String procedureName = "SP_DELETE_ALERT_TEMPLATES"; List<Object> inputParams
		 * = Arrays.asList("Test"); List<Integer> outputParamTypes =
		 * Arrays.asList(Types.VARCHAR); // Example output parameters
		 * 
		 * List<Object> outputValues = ExecuteAnyOracleSQLStoredProcedure(procedureName,
		 * inputParams, outputParamTypes);
		 * 
		 * System.out.println("Output Values: " + outputValues);
		 */
    	
    	 // Example usage with a SELECT statement
        /*String selectQuery = "SELECT column_name FROM table_name WHERE condition";
        String selectResult = executeSQLStatement(selectQuery);
        System.out.println("Select Query Result: " + selectResult);*/

        // Example usage with a TRUNCATE statement
//        String truncateQuery = "TRUNCATE TABLE mst_callcentre_accounts";
//        String truncateResult = executeSQLStatement(truncateQuery);
//        System.out.println("Truncate Query Result: " + truncateResult);
        
        //String deleteQuery = "DELETE FROM agent_account_link WHERE col_agency_id = (SELECT COLLECTION_AGENCY_ID FROM MST_COLLECTION_AGENCY WHERE COLLECTION_AGENCY_NAME = 'qwer' FETCH FIRST 1 ROW ONLY)";
//        String deleteQuery = "DELETE FROM agent_account_link \r\n"
//        		+ "WHERE col_agency_id IN (\r\n"
//        		+ "    SELECT COLLECTION_AGENCY_ID FROM MST_COLLECTION_AGENCY \r\n"
//        		+ "    WHERE COLLECTION_AGENCY_NAME = 'qwer'\r\n"
//        		+ ")";
        // Call the method from DBUtils
        //executeSQLStatement(deleteQuery); 

    	
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
    
    public static String executeSQLStatement(String query) throws SQLException, ClassNotFoundException, IOException {
    	
    	Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String result = null;

        try {
            // Establish the database connection
            con = Base_Class.OracleDBConnection();

            // Disable auto-commit to manage transactions manually
            con.setAutoCommit(false); 

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
                con.commit(); // Commit changes for TRUNCATE
                result = "TRUNCATE command executed successfully.";
            } else {
                // Execute other non-query statements (like INSERT, UPDATE, DELETE)
                int affectedRows = pstmt.executeUpdate();
                con.commit(); // Commit changes for DML operations
                result = "Query executed successfully. Rows affected: " + affectedRows;
            }
        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback(); // Rollback in case of an error
                    System.out.println("Transaction rolled back due to an error.");
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
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
    
    public static String executeUpdateQuery(String updateQuery) 

            throws SQLException, ClassNotFoundException, IOException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String result = null;

        try {
            // Establish the database connection
            con = Base_Class.OracleDBConnection();

            // Disable auto-commit (if necessary)
            con.setAutoCommit(false);

            // Prepare the SQL statement
            pstmt = con.prepareStatement(updateQuery);

            // Execute the UPDATE statement
            int affectedRows = pstmt.executeUpdate();

            // Commit the changes
            con.commit();

            // Return success message
            result = "Update query executed successfully. Rows affected: " + affectedRows;
        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback(); // Rollback in case of error
                } catch (SQLException ex) {
                    System.err.println("Rollback failed: " + ex.getMessage());
                }
            }
            e.printStackTrace();
            throw e; // Rethrow exception for further handling
        } finally {
            // Close resources
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
        
     // Handle null input
        if (inputParams == null) {
            inputParams = new ArrayList<>();
        }

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