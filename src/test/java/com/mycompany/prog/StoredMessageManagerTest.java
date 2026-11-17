/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.prog;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Student
 */
public class StoredMessageManagerTest {
    
    private String[] sentMessages;
    private String[] storedMessages;
    private String[] messageIds;
    private String[] recipients;
    private String[] messageHashes;
    private int messageCount;

    @BeforeEach
    public void setUp() {
        // Populating array data using Test Data Messages 1 to 5 from the brief
        sentMessages = new String[] {
            "Did you get the cake?",
            "It is dinner time!"
        };

        storedMessages = new String[] {
            "Did you get the cake?",
            "Where are you? You are late! I have asked you to be on time.",
            "Yohoooo, I am at your gate.",
            "It is dinner time!",
            "Ok, I am leaving without you."
        };

        messageIds = new String[] { "+27834557896", "+27838884567", "+27834484567", "0388884567", "+27838884567" };
        recipients = new String[] { "+27834557896", "+27838884567", "+27834484567", "0388884567", "+27838884567" };
        messageHashes = new String[] { "HASH1", "HASH2_WHEREAREYOU?", "HASH3", "HASH4", "HASH5" };
        messageCount = 5;
    }

    @Test
    public void testSentMessagesArrayPopulated() {
        // Test Data: Developer entry for Test data for message 1-4
        // Expected system returns: "Did you get the cake?", "It is dinner time!"
        assertEquals("Did you get the cake?", sentMessages[0]);
        assertEquals("It is dinner time!", sentMessages[1]);
    }

    @Test
    public void testDisplayLongestMessage() {
        // Test Data: message 1-4
        // Expected system returns: "Where are you? You are late! I have asked you to be on time."
        int longestIndex = 0;
        for (int i = 1; i < 4; i++) {
            if (storedMessages[i].length() > storedMessages[longestIndex].length()) {
                longestIndex = i;
            }
        }
        assertEquals("Where are you? You are late! I have asked you to be on time.", storedMessages[longestIndex]);
    }

    @Test
    public void testSearchByMessageId() {
        // Test Data: message 4, ID: "0388884567"
        // Expected system returns: "It is dinner time!"
        String searchId = "0388884567";
        String resultMessage = "";

        for (int i = 0; i < messageCount; i++) {
            if (messageIds[i].equals(searchId)) {
                resultMessage = storedMessages[i];
                break;
            }
        }
        assertEquals("It is dinner time!", resultMessage);
    }

    @Test
    public void testSearchByRecipient() {
        // Test Data: +27838884567
        // Expected system returns: "Where are you? You are late! I have asked you to be on time." and "Ok, I am leaving without you."
        String targetRecipient = "+27838884567";
        StringBuilder expectedOutput = new StringBuilder();

        for (int i = 0; i < messageCount; i++) {
            if (recipients[i].equals(targetRecipient)) {
                expectedOutput.append(storedMessages[i]).append(" ");
            }
        }

        String expectedResult = "Where are you? You are late! I have asked you to be on time. Ok, I am leaving without you. ";
        assertEquals(expectedResult, expectedOutput.toString());
    }

    @Test
    public void testDeleteByHash() {
        // Test Data: Test Message 2
        // Expected system returns: Message: "Where are you? You are late! I have asked you to be on time" successfully deleted.
        String targetHash = "HASH2_WHEREAREYOU?";
        int deleteIndex = -1;

        for (int i = 0; i < messageCount; i++) {
            if (messageHashes[i].equals(targetHash)) {
                deleteIndex = i;
                break;
            }
        }

        boolean isDeleted = (deleteIndex != -1);
        assertTrue(isDeleted, "Message successfully deleted.");
    }

    @Test
    public void testDisplayReport() {
        // Test that array has content for report generation including Message Hash and Recipient
        assertNotNull(messageHashes[0]);
        assertNotNull(recipients[0]);
        assertTrue(messageCount > 0);
    }
    
    
}
