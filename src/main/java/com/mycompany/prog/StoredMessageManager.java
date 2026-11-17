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
}
