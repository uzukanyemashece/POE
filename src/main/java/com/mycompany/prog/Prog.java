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
       userLogin.setFirstName(scanner.nextLine());

        System.out.print("Enter Last Name: ");
       userLogin.setLastName(scanner.nextLine());
       
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

     // --- PART 2: QUICKCHAT MESSAGING SYSTEM ---
        if (loggedIn) {
            System.out.println("\nWelcome to QuickChat.");
            boolean running = true;

            while (running) {
                System.out.println("\nSelect an option from the menu:");
                System.out.println("1) Send Messages");
                System.out.println("2) Show recently sent messages");
              System.out.println("3) Stored Messages Management"); // <-- Fourth main menu option added
                System.out.println("4) Quit");
                System.out.print("Enter choice (1-4): ");
                String choice = scanner.nextLine();

                switch (choice) {
                   case "1":
    System.out.print("How many messages would you like to enter? ");
    int numMessages = 0;
    try {
        numMessages = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
        System.out.println("Please enter a valid numeric value.");
        break;
    }

    // Start message numbers at 0
    for (int i = 0; i < numMessages; i++) {
        System.out.println("\n--- Entering Message " + i + " of " + (numMessages - 1) + " ---");
        
        // 1. Recipient Cell Number Input & Immediate Verification Loop
        String recipient = "";
        boolean validRecipient = false;
        while (!validRecipient) {
            System.out.print("Enter recipient cell number (with international code, e.g., +27...): ");
            recipient = scanner.nextLine();

            Message tempMsg = new Message(i, recipient, "", "1");
            validRecipient = tempMsg.checkRecipientCell();

            if (validRecipient) {
                System.out.println("Recipient cell number successfully verified.");
            } else {
                System.out.println("Cell phone number incorrectly formatted or does not contain international code. Please try again.");
            }
        }

        // 2. Message Text Input & Length Validation Loop
        String messageText = "";
        boolean validMessage = false;
        while (!validMessage) {
            System.out.print("Enter message description (max 250 characters): ");
            messageText = scanner.nextLine();

            Message tempMsg = new Message(i, recipient, messageText, "1");
            validMessage = tempMsg.checkMessageLength();

            if (validMessage) {
                System.out.println("Message length approved.");
            } else {
                System.out.println("Please enter a message of less than 250 characters.");
            }
        }

        // 3. Choose Send Option
        System.out.println("\nChoose send option:");
        System.out.println("1) Send Message (\"Message successfully sent\")");
        System.out.println("2) Disregard Message (\"Press 0 to delete the message\")");
        System.out.println("3) Store Message to send later (\"Message successfully stored\")");
        System.out.println("4) Quit"); // Shifted quit to 4 (or keep quit as 5 if you prefer)
        System.out.print("Enter choice (1-4): ");
        String sendChoice = scanner.nextLine();

        // 4. Create Message Object
        Message msg = new Message(i, recipient, messageText, sendChoice);
        
        // 5. Save the message to messages.json
        msg.saveMessageToFile();

        // 6. Display details only if sent
        if (sendChoice.equals("1")) {
            System.out.println("\n--- Message Details ---");
            System.out.println(msg.printMessages());
        } else if (sendChoice.equals("2")) {
            System.out.println("Message disregarded.");
        } else {
            System.out.println("Message successfully stored.");
        }
    }

    System.out.println("\nTotal messages sent so far: " + Message.returnTotalMessages());
    break;

                    case "2":
                        System.out.println("Coming Soon.");
                        break;

                    case "3":
                        // Opens the Stored Messages Management sub-menu
                        StoredMessageManager.displayStoredMessagesMenu(scanner);
                        break;
                        
                        case "4":
                        System.out.println("Exiting QuickChat. Goodbye!");
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid option. Please choose 1, 2, or 3 or 4 .");
                        break;
                        
                }
            }
                   
    }
    
    }}
