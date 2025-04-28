package com.BasePackage;

import java.io.IOException;
import java.sql.*;

public class ExecuteStoredProcedure {

	// Method to execute the stored procedure and return DBMS_OUTPUT lines
    public static String callLoadAndValidateAccountsSP() throws IOException {
        Connection conn = null;
        CallableStatement callableStatement = null;
        CallableStatement enableDbmsOutput = null;
        CallableStatement getLineStmt = null;
        StringBuilder result = new StringBuilder();

        try {
            // Step 1: Use the existing database connection
            conn = Base_Class.OracleDBConnection();

            // Step 2: Enable DBMS_OUTPUT
            String enableOutputSQL = "BEGIN DBMS_OUTPUT.ENABLE(1000000); END;";
            enableDbmsOutput = conn.prepareCall(enableOutputSQL);
            enableDbmsOutput.execute();

            // Step 3: Execute the stored procedure
            String callSP = "{ CALL load_and_validate_accounts }";
            callableStatement = conn.prepareCall(callSP);
            callableStatement.execute();

            // Step 4: Fetch DBMS_OUTPUT using DBMS_OUTPUT.GET_LINE
            String getLineSQL = "BEGIN DBMS_OUTPUT.GET_LINE(?, ?); END;";
            getLineStmt = conn.prepareCall(getLineSQL);

            while (true) {
                getLineStmt.registerOutParameter(1, Types.VARCHAR); // Line content
                getLineStmt.registerOutParameter(2, Types.INTEGER); // Status
                getLineStmt.execute();

                String line = getLineStmt.getString(1);
                int status = getLineStmt.getInt(2);

                if (status != 0) { // No more lines to read
                    break;
                }
                if (line != null) {
                    result.append(line).append("\n");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            result.append("Error: ").append(e.getMessage());
        } finally {
            // Step 5: Close resources
            try {
                if (getLineStmt != null) getLineStmt.close();
                if (callableStatement != null) callableStatement.close();
                if (enableDbmsOutput != null) enableDbmsOutput.close();
                // Do not close `conn` if it is managed by Base_Class
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return result.toString().trim();
    }

    public static void main(String[] args) throws IOException {
        // Example usage
        String report = callLoadAndValidateAccountsSP();
        System.out.println(report);
    }

}
