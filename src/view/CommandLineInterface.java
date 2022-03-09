package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.StandardSocketOptions;
import java.security.InvalidParameterException;

public class CommandLineInterface {


    // main function for debugging of this module only
    public static void main(String[] args) throws IOException {
        start();
    }


    public static void start() throws IOException {
        menu_main();
    }


    private static void menu_main() throws IOException {
        boolean keepRunning = true;
        while (keepRunning) {
            clearScreen();

            int response = letUserChooseMenuItem(
                    "Hello, please let me know how I can help you:\n",
                    new String[] { "manage subscribers", "create session", "create invoice", "list subscribers", "list charges", "exit" });

            switch (response) {
                case 0:
                    menu_manageSubscribers();
                    break;
                case 1:
                    process_createSession();
                    break;
                case 2:
                    process_createInvoice();
                    break;
                case 3:
                    process_listSubscribers();
                    break;
                case 4:
                    process_listCharges();
                    break;
                case 5:
                    keepRunning = false;
                    System.out.println("Goodbye");
                    break;
            }
        }
    }

    private static void menu_manageSubscribers() throws IOException {
        clearScreen();
        int response = letUserChooseMenuItem(
                "You want to manage subscribers:\n",
                new String[] { "add subscriber", "remove subscriber", "back to main menu" });

        switch (response) {
            case 0:
                process_addSubscriber();
                break;
            case 1:
                process_removeSubscriber();
                break;
            case 2:
                // do nothing so that it gets back to the higher level immediately
                break;
        }
    }


    private static void process_addSubscriber() throws IOException {
        // declare variables for required data
        String forename;
        String surname;
        int imsi;
        int subscriptionKey;
        // ...

        // fill variables with input (of the right data type, validation will happen in subscriber-constructor)
        forename = askAndGetString("Forename: ");
        surname = askAndGetString("Surname: ");
        imsi = askAndGetInt("IMSI: ");

        // let controller try to create new subscriber-object
        //debug:
        try {

            letUserChooseMenuItem("The following user has been added: " + forename + " " + surname + " " + imsi, new String[] {"Continue"});
        } catch (IllegalArgumentException e) {

            letUserChooseMenuItem("The values provided by you weren't valid for the creation of a new subscriber. Nothing has been changed, you will be transfered back to the main menu now.", new String[] {"Continue"});
        }

    }


    private static void process_removeSubscriber() throws IOException {

    }



    private static void process_createSession() throws IOException {

    }


    private static void process_createInvoice() throws IOException {

    }

    private static void process_listSubscribers() {

    }

    private static void process_listCharges() throws IOException {

    }






    public static int letUserChooseMenuItem(String[] menuItemTexts) throws IOException {
        return letUserChooseMenuItem("", menuItemTexts);
    }
    public static int letUserChooseMenuItem(String textBeforeItems, String[] menuItemTexts) throws IOException {
        return letUserChooseMenuItem(textBeforeItems, menuItemTexts, "");
    }

    public static int letUserChooseMenuItem(String textBeforeItems, String[] menuItemTexts, String textAfterItems) throws IOException {
        if (menuItemTexts.length == 0) {
            throw new IllegalArgumentException("there must be at least 1 menu item");
        }

        System.out.print(textBeforeItems);
        boolean validInputEntered = false;

        int userInputInt = -1;
        while (!validInputEntered) {
            validInputEntered = true;

            for (int i = 0; i < menuItemTexts.length; i++) {
                System.out.printf(" - " + menuItemTexts[i] + " (%d)\n", i);
            }
            System.out.print(textAfterItems);

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String userInputString = br.readLine();

            try {
                userInputInt = Integer.parseInt(userInputString);
            } catch (Exception e) {
                validInputEntered = false;
            }
            if (userInputInt < 0 || userInputInt >= menuItemTexts.length) {
                validInputEntered = false;
            }

            if (!validInputEntered) {
                System.out.println("Sorry, your response wasn't valid apparently, please try again");
            }

        }
        return userInputInt;
    }



    private static void clearScreen() {
        // Print empty lines for output where char sequence does not work
        for (int i = 0; i < 50; ++i) System.out.println();
        // clear screne via char sequence
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


    private static String askAndGetString(String message) throws IOException {
        System.out.print(message);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String userInputString = br.readLine();

        return userInputString;
    }

    private static int askAndGetInt(String message) throws IOException {
        int userInputInt = -1;
        boolean validInputEntered = false;
        while (!validInputEntered) {
            validInputEntered = true;

            System.out.print(message);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String userInputString = br.readLine();

            try {
                userInputInt = Integer.parseInt(userInputString);
            } catch (Exception e) {
                validInputEntered = false;
            }

            if (!validInputEntered) {
                System.out.println("Sorry, you should enter a number, please try again");
            }
        }
        return userInputInt;

    }



}
