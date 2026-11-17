/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prog;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
/**
 *
 * @author Student
 */
public class StoredMessageManager {
    private static String[] storedMessages = new String[100];
    private static String[] messageHashes = new String[100];
    private static String[] messageIds = new String[100];
    private static String[] senders = new String[100];
    private static String[] recipients = new String[100];
    private static int messageCount = 0;
    
    public static void loadStoredMessages() {
        messageCount = 0; // Reset count before loading
        try (BufferedReader br = new BufferedReader(new FileReader("messages.json"))) {
            String line;
            String currentId = "";
            String currentSender = "";
            String currentRecipient = "";
            String currentText = "";
            String currentHash = "";

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("\"id\":")) {
                    currentId = extractValue(line);
                } else if (line.startsWith("\"sender\":")) {
                    currentSender = extractValue(line);
                } else if (line.startsWith("\"recipient\":")) {
                    currentRecipient = extractValue(line);
                } else if (line.startsWith("\"message\":")) {
                    currentText = extractValue(line);
                } else if (line.startsWith("\"hash\":")) {
                    currentHash = extractValue(line);
                    
                    // Once we hit the hash, we have a complete record
                    if (messageCount < storedMessages.length) {
                        messageIds[messageCount] = currentId;
                        senders[messageCount] = currentSender;
                        recipients[messageCount] = currentRecipient;
                        storedMessages[messageCount] = currentText;
                        messageHashes[messageCount] = currentHash;
                        messageCount++;
                    }
                }
            }
            System.out.println("Successfully loaded " + messageCount + " messages from storage.");
        } catch (IOException e) {
            System.out.println("Could not read messages.json: " + e.getMessage());
        }
    }
    // Helper method to extract values from JSON string lines
    private static String extractValue(String line) {
        int colonIdx = line.indexOf(':');
        if (colonIdx != -1) {
            String val = line.substring(colonIdx + 1).trim();
            // Remove trailing commas and quotes
            if (val.endsWith(",")) {
                val = val.substring(0, val.length() - 1);
            }
            if (val.startsWith("\"") && val.endsWith("\"")) {
                val = val.substring(1, val.length() - 1);
            }
            return val;
        }
        return "";
    }
    /**
     * Option 2: Main Sub-menu for Stored Messages
     */
    public static void displayStoredMessagesMenu(Scanner scanner) {
        loadStoredMessages(); // Load data fresh from JSON each time menu opens

        boolean running = true;
        while (running) {
            System.out.println("\n--- Stored Messages Menu ---");
            System.out.println("a. Display sender and recipient of all stored messages");
            System.out.println("b. Display the longest stored message");
            System.out.println("c. Search for a message ID (display recipient & message)");
            System.out.println("d. Search messages for a particular recipient");
            System.out.println("e. Delete a message using the message hash");
            System.out.println("f. Display full details report of all stored messages");
            System.out.println("g. Return to Main Menu");
            System.out.print("Choose an option (a-g): ");

            String choice = scanner.nextLine().trim().toLowerCase();

            switch (choice) {
                case "a":
                    displaySenderAndRecipient();
                    break;
                case "b":
                    displayLongestMessage();
                    break;
                case "c":
                    searchByMessageId(scanner);
                    break;
                case "d":
                    searchByRecipient(scanner);
                    break;
                case "e":
                    deleteByHash(scanner);
                    break;
                case "f":
                    displayFullReport();
                    break;
                case "g":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose between a and g.");
            }
        }
    }
    // (a) Display sender and recipient of all stored messages
    public static void displaySenderAndRecipient() {
        System.out.println("\n--- Sender and Recipient List ---");
        if (messageCount == 0) {
            System.out.println("No stored messages found.");
            return;
        }
        for (int i = 0; i < messageCount; i++) {
            System.out.println("Sender: " + senders[i] + " | Recipient: " + recipients[i]);
        }
    }

    // (b) Display the longest stored message
    public static void displayLongestMessage() {
        if (messageCount == 0) {
            System.out.println("No stored messages found.");
            return;
        }
        int longestIndex = 0;
        for (int i = 1; i < messageCount; i++) {
            if (storedMessages[i].length() > storedMessages[longestIndex].length()) {
                longestIndex = i;
            }
        }
        System.out.println("\n--- Longest Stored Message ---");
        System.out.println("Message: " + storedMessages[longestIndex]);
        System.out.println("Length: " + storedMessages[longestIndex].length() + " characters");
        System.out.println("Sender: " + senders[longestIndex] + " | Recipient: " + recipients[longestIndex]);
    }

    // (c) Search for a message ID and display corresponding recipient and message
    public static void searchByMessageId(Scanner scanner) {
        System.out.print("Enter Message ID to search: ");
        String searchId = scanner.nextLine().trim();
        boolean found = false;

        for (int i = 0; i < messageCount; i++) {
            if (messageIds[i].equalsIgnoreCase(searchId)) {
                System.out.println("\n--- Message Found ---");
                System.out.println("Recipient: " + recipients[i]);
                System.out.println("Message: " + storedMessages[i]);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No message found with ID: " + searchId);
        }
    }

    // (d) Search for all messages stored for a particular recipient
    public static void searchByRecipient(Scanner scanner) {
        System.out.print("Enter Recipient name: ");
        String targetRecipient = scanner.nextLine().trim();
        boolean found = false;

        System.out.println("\n--- Messages for Recipient: " + targetRecipient + " ---");
        for (int i = 0; i < messageCount; i++) {
            if (recipients[i].equalsIgnoreCase(targetRecipient)) {
                System.out.println("ID: " + messageIds[i] + " | Message: " + storedMessages[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No messages found for recipient: " + targetRecipient);
        }
    }

    // (e) Delete a message using the message hash
    public static void deleteByHash(Scanner scanner) {
        System.out.print("Enter Message Hash of the message to delete: ");
        String targetHash = scanner.nextLine().trim();
        int deleteIndex = -1;

        for (int i = 0; i < messageCount; i++) {
            if (messageHashes[i].equalsIgnoreCase(targetHash)) {
                deleteIndex = i;
                break;
            }
        }

        if (deleteIndex != -1) {
            // Shift elements down in all arrays
            for (int i = deleteIndex; i < messageCount - 1; i++) {
                messageIds[i] = messageIds[i + 1];
                senders[i] = senders[i + 1];
                recipients[i] = recipients[i + 1];
                storedMessages[i] = storedMessages[i + 1];
                messageHashes[i] = messageHashes[i + 1];
            }
            messageCount--;
            System.out.println("Message with hash '" + targetHash + "' successfully deleted from memory.");
            // Note: To persist deletion, you can also call your save method to overwrite messages.json here if required.
        } else {
            System.out.println("No message found with hash: " + targetHash);
        }
    }

    // (f) Display a report that lists the full details of all the stored messages
    public static void displayFullReport() {
        System.out.println("\n================ FULL STORED MESSAGES REPORT ================");
        if (messageCount == 0) {
            System.out.println("No stored messages to report.");
            return;
        }
        for (int i = 0; i < messageCount; i++) {
            System.out.println("------------------------------------------------------------");
            System.out.println("Index       : " + (i + 1));
            System.out.println("Message ID  : " + messageIds[i]);
            System.out.println("Sender      : " + senders[i]);
            System.out.println("Recipient   : " + recipients[i]);
            System.out.println("Message     : " + storedMessages[i]);
            System.out.println("Hash        : " + messageHashes[i]);
        }
        System.out.println("=============================================================");
    } 
}
