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
    
}
