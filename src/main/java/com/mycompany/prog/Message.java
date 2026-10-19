package com.mycompany.prog;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author Student
 */
public class Message {
  private static int totalMessagesSent = 0; // Accumulates total sent messages across the application run[cite: 3]
    
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    private String sendStatus;
    
     public Message(int messageNumber, String recipient, String messageText, String sendChoice) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
        processSendStatus(sendChoice);
    }

    // Generates a random 10-digit number for tracking[cite: 2, 4]
    private String generateMessageID() {
        Random rand = new Random();
        long id = 1000000000L + (long)(rand.nextDouble() * 9000000000L);
        return String.valueOf(id);
    }

    // Method to ensure Message ID is not more than 10 characters
    public boolean checkMessageID() {
        return this.messageID != null && this.messageID.length() <= 10;
    }

    // Method to ensure the recipient cell number is no more than 10 characters and starts with an international code (+ prefix)[cite: 2, 4]
    public boolean checkRecipientCell() {
        if (this.recipient == null) return false;
        // Regex ensures it starts with '+' and contains digits, max length check handled
        String cellRegex = "^\\+[0-9]{9,12}$"; 
        return this.recipient.matches(cellRegex) && this.recipient.length() <= 13; // + plus up to 12 digits
    }

    // Method to check if message length is within 250 characters[cite: 2]
    public boolean checkMessageLength() {
        return this.messageText != null && this.messageText.length() <= 250;
    }

    // Creates the Message Hash: first two numbers of the ID, colon, message number, colon, first and last words in all caps[cite: 2, 4]
    public String createMessageHash() {
        if (this.messageID == null || this.messageID.length() < 2) return "";
        String firstTwoId = this.messageID.substring(0, 2);
        
        String firstWord = "";
        String lastWord = "";
        if (this.messageText != null && !this.messageText.trim().isEmpty()) {
            String cleanText = this.messageText.replaceAll("[^a-zA-Z ]", "").trim();
            String[] words = cleanText.split("\\s+");
            if (words.length > 0) {
                firstWord = words[0];
                lastWord = words[words.length - 1];
            }
        }
        return (firstTwoId + ":" + this.messageNumber + ":" + firstWord + lastWord).toUpperCase();
    }

    // Handles message send option choices[cite: 3, 4]
    private void processSendStatus(String choice) {
        switch (choice) {
            case "1":
                this.sendStatus = "Message successfully sent";
                totalMessagesSent++;
                break;
            case "2":
                this.sendStatus = "Press 0 to delete the message";
                break;
            case "3":
                this.sendStatus = "Message successfully stored";
                break;
            default:
                this.sendStatus = "Message successfully stored";
                break;
        }
    }

    // Displays full details in the requested order: Message ID, Message Hash, Recipient, Message[cite: 3]
    public String printMessages() {
        return "Message ID: " + messageID + "\n" +
               "Message Hash: " + messageHash + "\n" +
               "Recipient: " + recipient + "\n" +
               "Message: " + messageText + "\n" +
               "Status: " + sendStatus;
    }

    public static int returnTotalMessages() {
        return totalMessagesSent;
    }

    // Getters
    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
    public String getSendStatus() { return sendStatus; }
}


