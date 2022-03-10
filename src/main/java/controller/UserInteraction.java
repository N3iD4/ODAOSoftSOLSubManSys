package controller;

import DataHandling.SubscriberHandler;
import DataHandling.SubscriptionHandler;
import DataHandling.TerminalHandler;
import models.Subscriber;
import models.Subscription;
import models.Terminal;
import view.CommandLineInterface;

import java.io.IOException;
import java.util.List;

public class UserInteraction {

    protected static void menu_main() throws IOException {
        boolean keepRunning = true;
        while (keepRunning) {
            CommandLineInterface.clearScreen();

            int response = CommandLineInterface.letUserChooseMenuItem(
                    "Hello, please let me know how I can help you:\n",
                    new String[] { "manage subscribers", "manage subscriptions", "manage terminals", "create session", "create invoice", "list subscribers", "list subscriptions", "list terminals", "list charges", "exit" });

            switch (response) {
                case 0:
                    menu_manageSubscribers();
                    break;
                case 1:
                    menu_manageSubscriptions();
                    break;
                case 2:
                    menu_manageTerminals();
                    break;
                case 3:
                    SessionFunctionality.process_createSession();
                    break;
                case 4:
                    process_createInvoice();
                    break;
                case 5:
                    process_listSubscribers();
                    break;
                case 6:
                    process_listSubscriptions();
                    break;
                case 7:
                    process_listTerminals();
                    break;
                case 8:
                    process_listCharges();
                    break;
                case 9:
                    keepRunning = false;
                    System.out.println("Goodbye");
                    break;
            }
        }
    }

    private static void menu_manageSubscribers() {
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


    private static void menu_manageSubscriptions() {
        CommandLineInterface.clearScreen();
        int response = CommandLineInterface.letUserChooseMenuItem(
                "You want to manage subscriptions:\n",
                new String[] { "add subscription", "remove subscription", "edit subscriptions", "back to main menu" });

        switch (response) {
            case 0:
                process_addSubscription();
                break;
            case 1:
                process_removeSubscription();
                break;
            case 2:
                process_editSubscription();
                break;
            case 3:
                // do nothing so that it gets back to the higher level immediately
                break;
        }
    }




    private static void menu_manageTerminals() {
        CommandLineInterface.clearScreen();
        int response = CommandLineInterface.letUserChooseMenuItem(
                "You want to manage terminals:\n",
                new String[] { "add subscription", "remove subscription", "edit subscriptions", "back to main menu" });

        switch (response) {
            case 0:
                process_addTerminal();
                break;
            case 1:
                process_removeTerminal();
                break;
            case 2:
                process_editTerminal();
                break;
            case 3:
                // do nothing so that it gets back to the higher level immediately
                break;
        }
    }



    private static void process_addSubscriber() {
        // declare variables for required data
        String forename;
        String surname;
        String imsi;
        int subscriptionId;
        int terminalId;

        // fill variables with input (of the right data type, validation will happen in subscriber-constructor)
        forename = CommandLineInterface.askAndGetString("Forename: ");
        surname = CommandLineInterface.askAndGetString("Surname: ");
        imsi = CommandLineInterface.askAndGetImsi("IMSI: ");
        // get subscription
        List<Subscription> subscriptions = SubscriptionHandler.getSubscriptions();
        int userAnswer = CommandLineInterface.letUserChooseMenuItem("Subscription", (String[]) subscriptions.stream().filter( el -> el.getIsActive() ).map( el -> el.getName() ).toArray() );
        subscriptionId = subscriptions.get(userAnswer).getId();
        // get terminal
        List<Terminal> terminals = TerminalHandler.getTerminals();
        userAnswer = CommandLineInterface.letUserChooseMenuItem("Subscription", (String[]) terminals.stream().filter( el -> el.getIsActive() ).map( el -> el.getName() ).toArray() );
        terminalId = terminals.get(userAnswer).getId();

        // let controller try to create new subscriber-object
        try {
            //
            Subscriber newSubscriber = new Subscriber( -1, forename, surname, imsi, terminalId, subscriptionId );
            SubscriberHandler.addSubscriber(newSubscriber);
            SubscriberHandler.save();

            CommandLineInterface.letUserChooseMenuItem("The following user has been added: " + forename + " " + surname + " " + imsi, new String[] {"Continue"});
        } catch (IllegalArgumentException e) {
            CommandLineInterface.letUserChooseMenuItem("The values provided by you weren't valid for the creation of a new subscriber. Nothing has been changed, you will be transfered back to the main menu now.", new String[] {"Continue"});
        }

    }


    private static void process_removeSubscriber() {
        // exit immediately if there are no subscribers
        if (  SubscriberHandler.getSubscribers().size() == 0  ) {
            CommandLineInterface.waitForUserToContinue("There are no saved subscribers at the moment. You will be brought back to the main menu.");
        }

        // ask user for subscirber-id
        int userIdToRemove = CommandLineInterface.askAndGetInt("Please specify the user id to remove");

        // Try to remove subscriber with entered id
        boolean success = true;
        try {
            Subscriber subscriber = SubscriberHandler.getSubscriberById(userIdToRemove);
            SubscriberHandler.deleteSub(subscriber);
        } catch (Exception e) {
            success = false;
        }

        // display whether subscriber was removed
        if (success) {
            CommandLineInterface.waitForUserToContinue("The subscriber was removed.");
        } else {
            CommandLineInterface.waitForUserToContinue("A subscriber with the id specified by you could not be removed"); // IMPORTANT: No loop here, because if there are no subscribers yet the end-user will be stuck here because he can never seleect a valid user ! [Alternative one day: enter -1 for back to main menu]
        }
    }








    private static void process_addSubscription() {

    }

    private static void process_removeSubscription() {


    }


    private static void process_editSubscription() {

    }



    private static void process_addTerminal() {

    }

    private static void process_removeTerminal() {

    }

    private static void process_editTerminal() {

    }













    private static void process_createInvoice() {

    }

    private static void process_listSubscribers() {

    }

    private static void process_listSubscriptions() {

    }

    private static void process_listTerminals() {

    }

    private static void process_listCharges() {

    }




}
