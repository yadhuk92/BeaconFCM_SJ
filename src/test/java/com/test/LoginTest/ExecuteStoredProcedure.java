package com.test.LoginTest;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.BasePackage.Base_Class;

public class ExecuteStoredProcedure {

    // Custom class to store the result
    public static class ProcedureResult {
        private String accountNo;
        private String mobileNo;
        private String message;

        public ProcedureResult(String accountNo, String mobileNo, String message) {
            this.accountNo = accountNo;
            this.mobileNo = mobileNo;
            this.message = message;
        }

        public String getAccountNo() {
            return accountNo;
        }

        public String getMobileNo() {
            return mobileNo;
        }

        public String getMessage() {
            return message;
        }
    }

    public static ProcedureResult executeStoredProcedure(String mobileNumber, String OSBAL) throws IOException {
        Connection con = null;
        CallableStatement callableStatement = null;
        String accountNo = null;
        String updatedMobileNo = null;
        String resultMessage = "";

        try {
            // Get the database connection
            con = Base_Class.OracleDBConnection();

            // Prepare the stored procedure call
            String procedureCall = "{call SP_CHECK_INVALID_MOBILE_ACCOUNT_FILTERATION(?, ?, ?, ?)}";
            callableStatement = con.prepareCall(procedureCall);

            // Set the input parameter
            callableStatement.setString(1, mobileNumber);
            callableStatement.setString(2, OSBAL);

            // Register the output parameters
            callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR); // o_account_no
            callableStatement.registerOutParameter(4, java.sql.Types.VARCHAR); // o_mob_no

            // Execute the stored procedure
            callableStatement.execute();

            // Retrieve the output parameters
            accountNo = callableStatement.getString(3);
            updatedMobileNo = callableStatement.getString(4);

            if (accountNo != null && updatedMobileNo != null) {
                resultMessage = "Procedure executed successfully.";
            } else {
                resultMessage = "No data found for the provided mobile number.";
            }

        } catch (SQLException e) {
            // Handle SQL exceptions
            resultMessage = "SQL Exception occurred: " + e.getMessage();
        } finally {
            // Close resources
            try {
                if (callableStatement != null) callableStatement.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                resultMessage = "Error while closing resources: " + e.getMessage();
            }
        }

        // Return results encapsulated in the custom object
        return new ProcedureResult(accountNo, updatedMobileNo, resultMessage);
    }

    public static void main(String[] args) throws IOException {
        // Example usage of the method
        ProcedureResult result = executeStoredProcedure("812990465A","12345");

        // Accessing individual values
        System.out.println("Message: " + result.getMessage());
        System.out.println("Account No: " + result.getAccountNo());
        System.out.println("Mobile No: " + result.getMobileNo());
    }
}