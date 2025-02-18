package com.BasePackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public static void main(String[] args) {
        try {
            // Example usage
            String query = "SELECT Default_URL FROM acc_users WHERE user_id = 'IBU0000028'";
            String defaultURL = fetchSingleValueFromDB(query);
            System.out.println("Default URL: " + defaultURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

