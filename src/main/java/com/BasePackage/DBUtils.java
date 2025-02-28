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

public class DBUtils {

    public static String fetchSingleValueFromDB(String query) throws SQLException, ClassNotFoundException, IOException {
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

    public static void main(String[] args) {
        try {
            // Example usage
			/*
			 * String query =
			 * "SELECT Default_URL FROM acc_users WHERE user_id = 'IBU0000028'"; String
			 * defaultURL = fetchSingleValueFromDB(query);
			 * System.out.println("Default URL: " + defaultURL);
			 */
        	
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
        	
			/*
			 * // Define the stored procedure name String procedureName =
			 * "ALLOCATION_DASHBOARD_DATA_LOAD_PKG.SPPROCESSCALLCENETERREGULARIZATIONSUMMARY";
			 * // No input parameters List<Object> inputParams = new ArrayList<>(); // No
			 * output parameters List<Integer> outputParamTypes = new ArrayList<>(); //
			 * Execute the stored procedure List<Object> result =
			 * ExecuteAnyOracleSQLStoredProcedure(procedureName, inputParams,
			 * outputParamTypes); // Print the output (if there were any)
			 * System.out.println("Stored procedure executed successfully. Output: " +
			 * result);
			 */
        	
        	String query="DELETE FROM mst_branch_acc_allocated WHERE ALLOCATED_DATE = TRUNC(SYSDATE)";
        	executeSQLStatement(query);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
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

