package com.test.LoginTest;

import com.BasePackage.Login_Class;

public class TestAnyMethod {
    public static void main(String[] args) {
    	// Create an instance of LoginService
        Login_Class LoginClass = new Login_Class();

        // Define user ID and password
        String userID = "IBU0001521";
        String password = "ses@987";

        try {
            // Call the CoreLoginWithInputs method
        	LoginClass.CoreLoginWithInputs(userID, password);
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }
}