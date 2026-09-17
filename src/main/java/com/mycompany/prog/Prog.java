/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.prog;

import java.util.Scanner;

/**
 *
 * @author Student
 */
public class Prog {

    public static void main(String[] args) {
        
         Scanner scanner = new Scanner(System.in);
        Login userLogin = new Login();

        System.out.println("   ACCOUNT REGISTRATION    ");
            
        
        
        System.out.print("Enter First Name: ");
       String firstName = scanner.nextLine();

        System.out.print("Enter Last Name: ");
       String lastName = scanner.nextLine();
       
        // 1. Username Input & Immediate Verification
        boolean validUsername = false;
        while (!validUsername) {
            System.out.print("Enter Username: ");
              userLogin.setUsername(scanner.nextLine());
            validUsername = userLogin.checkUserName();

            if (validUsername) {
                System.out.println("Username successfully captured.");
            } else {
                System.out.println("Username is not correctly formatted; please ensure that your username "
                        + "contains an underscore and is no more than five characters in length.");
            }
        }

        // 2. Password Input & Immediate Verification
        boolean validPassword = false;
        while (!validPassword) {
            System.out.print("Enter Password: ");
            userLogin.setPassword(scanner.nextLine()); 
            validPassword = userLogin.checkPasswordComplexity();

            if (validPassword) {
                System.out.println("Password successfully captured.");
            } else {
                System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, "
                        + "a capital letter, a number, and a special character.");
            }
        }

        // 3. Cell Phone Input & Immediate Verification
        boolean validCell = false;
        while (!validCell) {
            System.out.print("Enter SA Cell Phone Number (e.g., +27821234567): ");
            userLogin.setCellPhoneNumber(scanner.nextLine());
            validCell = userLogin.checkCellPhoneNumber();

            if (validCell) {
                System.out.println("Cell phone number successfully added.");
            } else {
                System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
            }
        }

        // Complete Registration Process
        userLogin.registerUser();

        // 4. Login Loop & Feedback Messaging
        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.println("    USER LOGIN    ");
            System.out.print("Enter Username: ");
            String loginUsername = scanner.nextLine();

            System.out.print("Enter Password: ");
            String loginPassword = scanner.nextLine();

            // Verify authentication status
            loggedIn = userLogin.loginUser(loginUsername, loginPassword);

            // Display success or failure message
            String statusMessage = userLogin.returnLoginStatus(loggedIn);
            System.out.println(" " + statusMessage + " ") ;
        }

        
    }
    }

