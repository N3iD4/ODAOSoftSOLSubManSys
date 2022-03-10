package controller;

import java.io.IOException;

import view.CommandLineInterface;


public class UserInteraction {


    public static void main(String[] args) throws IOException {
        menu_main();
    }




    private static void menu_main() throws IOException {
        boolean keepRunning = true;
        while (keepRunning) {
            CommandLineInterface.clearScreen();

            int response = CommandLineInterface.letUserChooseMenuItem(
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
        CommandLineInterface.clearScreen();
        int response = CommandLineInterface.letUserChooseMenuItem(
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
        forename = CommandLineInterface.askAndGetString("Forename: ");
        surname = CommandLineInterface.askAndGetString("Surname: ");
        imsi = CommandLineInterface.askAndGetInt("IMSI: ");

        // let controller try to create new subscriber-object
        //debug:
        try {

            CommandLineInterface.letUserChooseMenuItem("The following user has been added: " + forename + " " + surname + " " + imsi, new String[] {"Continue"});
        } catch (IllegalArgumentException e) {

            CommandLineInterface.letUserChooseMenuItem("The values provided by you weren't valid for the creation of a new subscriber. Nothing has been changed, you will be transfered back to the main menu now.", new String[] {"Continue"});
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




}
