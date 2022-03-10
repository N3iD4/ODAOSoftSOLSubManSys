package controller;

import java.io.IOException;



import view.CommandLineInterface;


public class UserInteraction {







    protected static void menu_main() throws IOException {
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

    private static void process_addSubscriber() {
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
        try {
            //
            // Subscriber newSubscriber = new Subscriber(  ...  );
            // Persistence.addSubscriber(newSubscriber);

            CommandLineInterface.letUserChooseMenuItem("The following user has been added: " + forename + " " + surname + " " + imsi, new String[] {"Continue"});
        } catch (IllegalArgumentException e) {
            CommandLineInterface.letUserChooseMenuItem("The values provided by you weren't valid for the creation of a new subscriber. Nothing has been changed, you will be transfered back to the main menu now.", new String[] {"Continue"});
        }

    }


    private static void process_removeSubscriber() {
        int userIdToRemove = CommandLineInterface.askAndGetInt("Please specify the user id to remove");
        boolean success = true; // = Persistence.RemoveUser(userIdToRemove);
        if (success) {
            CommandLineInterface.waitForUserToContinue("The subscriber was removed.");
        } else {
            CommandLineInterface.waitForUserToContinue("A subscriber with the id specified by you could not be removed"); // IMPORTANT: No loop here, because if there are no subscribers yet the end-user will be stuck here because he can never seleect a valid user ! [Alternative one day: enter -1 for back to main menu]
        }


    }



    private static String[] serviceNames = new String[] { "Voice Call", "Browsing and social networking", "App download", "Adaptive HD video" };
    private static String[] serviceTypes = new String[] { "voice", "data", "data", "data" };
    private static int[] serviceRateInMbPerS = new int[] { 0, 2, 10, 100 };


    private static void process_createSession() {
        // Needed: user, service, time

        // Ask for userId
        int userId = CommandLineInterface.askAndGetInt("Which user should open a session?");
        // Check if valid userId
        boolean isValidId = true; // = Persistence.UserManagement.isValidId(userId);
        if (!isValidId) {
            CommandLineInterface.waitForUserToContinue("The id you entered didn't match a user account. No session has been created and you will be brought back to the main menu.");
            return;
        }

        // Ask for service type
        int serviceType = CommandLineInterface.letUserChooseMenuItem("Which service should be started?", serviceNames);

        // Ask for time
        int timeInS = CommandLineInterface.askAndGetInt("How long should the session be?");






    }


    private static void process_createInvoice() {

    }

    private static void process_listSubscribers() {

    }

    private static void process_listCharges() {

    }




}
