/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.prog;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class LoginTest {
    
    public LoginTest() {
    }
   @Test
    public void testCorrectUsername (){
        Login obj = new Login ();
        obj.setUsername("kyl_1");
        boolean results = obj.checkUserName() ;
        boolean Expected = true;
      assertEquals(results,Expected,"");
    }
    
    @Test
    public void testIncorrectUsername (){
        Login obj = new Login ();
        obj.setUsername("kyle!!!!!!!");
        boolean results = obj.checkUserName() ;
        boolean Expected = false;
      assertEquals(results,Expected,"");
    }
    
     @Test
    public void testCorrectPassword (){
        Login obj = new Login ();
        obj.setPassword("Ch&&sec@ke99") ;
        boolean results = obj.checkPasswordComplexity() ;
       boolean Expected = true ;
       assertEquals(results, Expected) ;
    }
    @Test
    public void testIncorrectPassword (){
        Login obj = new Login ();
        String password =     "password" ;
        boolean results = obj.checkPasswordComplexity() ;
        boolean Expected = false ;
        assertEquals(results, Expected) ;
    }
     @Test
    public void testCorrectCellNumber (){
        Login obj = new Login ();
       obj.setCellPhoneNumber("+27838968976");
        boolean results = obj.checkCellPhoneNumber() ;
        boolean  Expected = true ;
        assertEquals(results,Expected);
    }
    @Test
    public void testIncorrectCellNumber (){
        Login obj = new Login ();
        obj.setCellPhoneNumber("08966553"); 
        boolean results = obj.checkCellPhoneNumber() ;
         boolean  Expected = false;
        assertEquals(results,Expected);
    }
    @Test
    public void loginSucessful() {
        Login obj = new Login ();
        obj.setUsername("uzu_0");
        obj.setPassword("ch&&secake99");
        String correctUsername = "uzu_0" ;
        String correctPassword = "ch&&secake99" ;
        boolean results = obj.loginUser(correctUsername, correctPassword) ;
        assertTrue(results) ;
    }
    @Test
    public void loginUnsucessful() {
        Login obj = new Login ();
        boolean username = false ; 
      String InCorrectUsername = "kyle!!!!!!!" ; 
      String InCorrectPassword = "password"   ;
      boolean results = obj.loginUser(InCorrectPassword, InCorrectUsername) ;
              assertFalse (results) ;
    }
    @Test
    public void UsernameCorrectlyFormatted() {
        Login obj = new Login ();
     obj.setUsername( "abc_1");
     
     boolean results = obj.checkUserName();
     assertTrue (results) ;
               
    } 
    
    @Test
    public void UsernameInCorrectlyFormatted() {
        Login obj = new Login ();
     obj.setUsername( "abc_5441");
     boolean results = obj.checkUserName();
     assertFalse (results) ;
    }
    
    @Test
    public void PasswordRequirementsMet(){
    Login obj = new Login ();
    obj.setPassword("Ch&&sec@ke99");
    boolean hasCapital =true;
     boolean hasDigit = true;
     boolean hasSpecial = true ;
    boolean results = obj.checkPasswordComplexity();
    assertTrue (results) ;
   
    }
    @Test
     public void PasswordRequirementsNotMet(){
    Login obj = new Login ();
    obj.setPassword("password");
    boolean hasCapital =false;
     boolean hasDigit = false;
     boolean hasSpecial = false ;
    boolean results = obj.checkPasswordComplexity();
    assertFalse (results) ;
     }
     @Test
     public void CellphoneNumberCorrectlyFormatted (){
     Login obj = new Login ();
     obj.setCellPhoneNumber("+27893338967");
     boolean results = obj.checkCellPhoneNumber();
     assertTrue(results);
     
     }
    @Test
     public void CellphoneNumberIncorrectlyFormatted (){
     Login obj = new Login ();
     obj.setCellPhoneNumber("0893338967");
     boolean results = obj.checkCellPhoneNumber();
     assertFalse(results);
     }

    
}
