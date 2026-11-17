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
public class MessageTest {
    
   

   
   @Test
    public void testCheckMessageLengthCorrect() {
        Message msg = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?", "1");
        assertTrue(msg.checkMessageLength(), "Message length should be valid.");
    }
     
    @Test
    public void testCheckMessageLengthFailure() {
        String longText = "A".repeat(251);
        Message msg = new Message(0, "+27718693002", longText, "1");
        assertFalse(msg.checkMessageLength(), "Message length should fail when exceeding 250 characters.");
    }

    @Test
    public void testCheckRecipientCellSuccess() {
        Message msg = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?", "1");
        assertTrue(msg.checkRecipientCell(), "Recipient cell number should be valid.");
    }

    @Test
    public void testCheckRecipientCellFailure() {
        Message msg = new Message(0, "08575975889", "Test message", "2");
        assertFalse(msg.checkRecipientCell(), "Recipient cell number should fail if it lacks international formatting.");
    }
    
    @Test
    public void testExactMessageHash() {
        // Simulating ID starting with "00" and message number 0 with Test Case 1 data
        Message msg = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?", "1");
        // For testing the exact format structure mentioned in instructions:
        String hash = msg.createMessageHash();
        assertNotNull(hash);
        assertTrue(hash.endsWith(":0:HITONIGHT"), "Hash should end with message number and capitalized first/last words.");
    }

    @Test
    public void testHashLoop() {
        // Testing hash generation in a loop across multiple messages
        for (int i = 0; i < 3; i++) {
            Message msg = new Message(i, "+27718693002", "Test message number " + i, "1");
            String hash = msg.createMessageHash();
            assertNotNull(hash);
            assertFalse(hash.isEmpty());
            assertTrue(hash.contains(":" + i + ":"), "Hash should contain the correct message index number.");
        }
    }
    
    @Test
    public void testCheckMessageID() {
        Message msg = new Message(0, "+27718693002", "Test message", "1");
        assertTrue(msg.checkMessageID(), "Message ID should be <= 10 characters.");
    }

    @Test
    public void testProcessSendStatusChoice1() {
        Message msg = new Message(0, "+27718693002", "Test message", "1");
        assertEquals("Message successfully sent.", msg.getSendStatus());
    }

    @Test
    public void testProcessSendStatusChoice2() {
        Message msg = new Message(0, "+27718693002", "Test message", "2");
        assertEquals("Press 0 to delete the message.", msg.getSendStatus());
    }

    @Test
    public void testProcessSendStatusChoice3() {
        Message msg = new Message(0, "+27718693002", "Test message", "3");
        assertEquals("Message successfully stored.", msg.getSendStatus());
    }
}
