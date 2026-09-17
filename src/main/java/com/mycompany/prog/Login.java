/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prog;

import java.util.regex.Pattern;

/**
 *
 * @author Student
 */
public class Login {
      private String username;
    private String password;
    private String cellPhoneNumber;
    private String firstName;
    private String lastName;
    
    
  

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getCellPhoneNumber() { return cellPhoneNumber; }
    public void setCellPhoneNumber(String cellPhoneNumber) { this.cellPhoneNumber = cellPhoneNumber; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
 /**
     * Checks if username contains an underscore (_) and is no more than 5 characters long.
     */
    
    public boolean checkUserName() {
        return username != null && username.contains("_") && username.length() <= 5;
    }
/**
     * Checks if password meets standard complexity rules:
     * - At least 8 characters long
     * - Contains a capital letter
     * - Contains a number
     * - Contains a special character
     */
    public boolean checkPasswordComplexity() {
        if (this.password == null || this.password.length() < 8) {
            return false;
        }
        boolean hasCapital = Pattern.compile("[A-Z]").matcher(this.password).find();
        boolean hasDigit = Pattern.compile("[0-9]").matcher(this.password).find();
        boolean hasSpecial = Pattern.compile("[^a-zA-Z0-9]").matcher(this.password).find();

        return hasCapital && hasDigit && hasSpecial;
    }
     /**
     * Ensures cell phone number is correct length and contains international country code (+27).
     * Reference: Regular expression standard for South African (+27) international dialing format.
     */
    public boolean checkCellPhoneNumber() {
        if (this.cellPhoneNumber == null) return false;
        String cellRegex = "^\\+27[0-9]{9}$";
        return Pattern.matches(cellRegex, this.cellPhoneNumber);
            
    }
     
    public String registerUser() {
        if (!checkUserName()) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore "
                    + "and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; please ensure that the password contains at "
                    + "least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber()) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        return "Username successfully captured. Password successfully captured.Cell phone number successfully added.";
    } 
     /**
     * Verifies that entered login details match the stored user details.
   
     */
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return enteredUsername != null && enteredPassword != null &&
               enteredUsername.equals(this.username) &&
               enteredPassword.equals(this.password);
    }
 public String returnLoginStatus(boolean isLoggedIn) {
        if (isLoggedIn) {
            return "Welcome " + firstName + " " + lastName + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
    
    
    
    
    
}
