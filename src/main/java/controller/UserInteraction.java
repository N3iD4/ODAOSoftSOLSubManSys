package controller;

import DataHandling.SubscriberHandler;
import DataHandling.SubscriptionHandler;
import DataHandling.TerminalHandler;
import models.Subscriber;
import models.Subscription;
import models.Terminal;
import view.CommandLineInterface;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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
        // exit immediately if there are no active subscriptions or no active terminals
        if (  SubscriptionHandler.getSubscriptions().stream().filter(  e -> e.getIsActive()  ).count() <= 0 || TerminalHandler.getTerminals().stream().filter(  e -> e.getIsActive()  ).count() <= 0 ) {
            CommandLineInterface.waitForUserToContinue("There are either no active subscriptions or no active terminals. You need to specify both in the subscriber creation process. Because of this, you cannot create a new subscriber now. Nothing has changed and you will be brought back to the main menu.");
            return;
        }

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
            return;
        }

        // ask user for subscirber-id
        int userIdToRemove = CommandLineInterface.askAndGetInt("Please specify the user id to be removed:\n");

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
        Subscription newSubscription = getSubscriptionObjectFromUser();

        // let controller try to create new subscriber-object
        try {
            SubscriptionHandler.addSubscription(newSubscription);
            SubscriberHandler.save();

            CommandLineInterface.waitForUserToContinue("The new subscription has been added.");
        } catch (IllegalArgumentException e) {
            CommandLineInterface.waitForUserToContinue("The values provided by you weren't valid for the creation of a new subscription. Nothing has been changed, you will be transfered back to the main menu now.");
        }

    }

    private static void process_removeSubscription() {
        // exit immediately if there are no subscribers
        if (  SubscriptionHandler.getSubscriptions().size() == 0  ) {
            CommandLineInterface.waitForUserToContinue("There are no saved subscriptions at the moment. You will be brought back to the main menu.");
            return;
        }

        // ask user for subscription-id
        int subscriptionIdToRemove = CommandLineInterface.askAndGetInt("Please specify the subscription id to be removed:\n");

        // Try to remove subscriber with entered id
        boolean success = true;
        try {
            Subscription subscription = SubscriptionHandler.getSubscriptionById(subscriptionIdToRemove);
            SubscriptionHandler.deleteSub(subscription);
        } catch (Exception e) {
            success = false;
        }

        // display whether subscriber was removed
        if (success) {
            CommandLineInterface.waitForUserToContinue("The subscription was removed.");
        } else {
            CommandLineInterface.waitForUserToContinue("A susbcription with the id specified by you could not be removed"); // IMPORTANT: No loop here, because if there are no subscribers yet the end-user will be stuck here because he can never seleect a valid user ! [Alternative one day: enter -1 for back to main menu]
        }

    }


    private static void process_editSubscription() {
        if (  SubscriptionHandler.getSubscriptions().size() == 0  ) {
            CommandLineInterface.waitForUserToContinue("There are no saved subscriptions at the moment. You will be brought back to the main menu.");
            return;
        }

        // ask user for subscription-id
        int subscriptionIdToEdit = CommandLineInterface.askAndGetInt("Please specify the id of the subscription that you would like to edit:\n");
        Subscription oldSubscription = SubscriptionHandler.getSubscriptionById(subscriptionIdToEdit);

        System.out.println("Please enter the new subscription attributes - the validity of your change will be tested at the end:\n");
        Subscription newSubscription = getSubscriptionObjectFromUser();

        // is subscription in use?
        boolean isInUse = SubscriberHandler.hasAnySubscriberSubscriptionWithId(subscriptionIdToEdit);

        // test that there is no other subscription with the new name
        if ( !oldSubscription.getName().equals( newSubscription.getName() )  &&  SubscriptionHandler.getSubscriptions().stream().anyMatch(  e -> e.getName().equals( newSubscription.getName() ) )  ) {
            CommandLineInterface.waitForUserToContinue("Subscription couldn't be changed, because another subscription already has the same name (and for clarity reasons every subscription should have a different name).");
            return;
        }

        if (isInUse) {
            boolean isBetterOrEqual = true;

            if ( oldSubscription.getBasicFee().compareTo( newSubscription.getBasicFee() ) < 0 ) {
                isBetterOrEqual = false;
            }

            if ( oldSubscription.getMinutesIncluded() > newSubscription.getMinutesIncluded()  ) {
                isBetterOrEqual = false;
            }

            if ( oldSubscription.getPricePerExtraMinute().compareTo( newSubscription.getPricePerExtraMinute() ) < 0 ) {
                isBetterOrEqual = false;
            }

            if ( oldSubscription.getDataVolumeInMB() > newSubscription.getDataVolumeInMB() ) {
                isBetterOrEqual = false;
            }

            if ( !isBetterOrEqual ) {
                CommandLineInterface.waitForUserToContinue("Subscription couldn't be changed, because users are currently subscribed to it and the new conditions are worse than before");
                return;
            }

            SubscriptionHandler.editSub(newSubscription);
            CommandLineInterface.waitForUserToContinue("The changes were valid and have been saved.");

        }


    }


    private static Subscription getSubscriptionObjectFromUser() {

        Subscription resSubscription = new Subscription();

        // name
        boolean validResponse = false;
        while (!validResponse) {
            String userResponse = CommandLineInterface.askAndGetString("Subscription name: ");
            validResponse = !SubscriptionHandler.getSubscriptions().stream().anyMatch( e -> e.getName().equals(userResponse) );
            if (!validResponse) {
                System.out.println("A subscription with this name already exists. Please choose another name for clarity.");
            } else {
                resSubscription.setName(userResponse);
            }
        }

        resSubscription.setBasicFee( CommandLineInterface.askAndGetPositiveCurrencyValue("Basic fee: ") );

        resSubscription.setMinutesIncluded( CommandLineInterface.askAndGetNonNegativeInt("Minutes included: ") );

        resSubscription.setPricePerExtraMinute( CommandLineInterface.askAndGetPositiveCurrencyValue("Price per extra minute: ") );

        resSubscription.setDataVolumeInMB( CommandLineInterface.askAndGetNonNegativeInt("Data volume in Mb: ") );

        int userAnswer = CommandLineInterface.letUserChooseMenuItem(new String[] {"make subscription active", "make subscription inactive"} );
        resSubscription.setIsActive( userAnswer == 0 ? true : false );

        return resSubscription;
    }





    private static void process_addTerminal() {

    }

    private static void process_removeTerminal() {

    }

    private static void process_editTerminal() {

    }













    private static void process_createInvoice() {
        // exit immediately if there are no subscribers
        if (  SubscriberHandler.getSubscribers().size() == 0  ) {
            CommandLineInterface.waitForUserToContinue("There are no saved subscribers at the moment. You will be brought back to the main menu.");
            return;
        }

        int subscriberId = askSubscriberForValidUserOrMinus1("Please enter the id the subscriber for whom to create an invoice, or -1 to go back to the main menu:\n");
        if (subscriberId == -1) {
            return;
        } else {
            String invoiceMsg = SubscriberHandler.getSubscriberById(subscriberId).createInvoice();
            System.out.print( invoiceMsg );
            CommandLineInterface.waitForUserToContinue("");
        }

    }

    private static void process_listSubscribers() {
        // exit immediately if there are no subscribers
        if (  SubscriberHandler.getSubscribers().size() == 0  ) {
            CommandLineInterface.waitForUserToContinue("There are no saved subscribers at the moment. You will be brought back to the main menu.");
            return;
        }

        CommandLineInterface.waitForUserToContinue( SubscriberHandler.getSubscribers().toString() );
    }

    private static void process_listSubscriptions() {
        // exit immediately if there are no subscribers
        if (  SubscriptionHandler.getSubscriptions().size() == 0  ) {
            CommandLineInterface.waitForUserToContinue("There are no saved subscriptions at the moment. You will be brought back to the main menu.");
            return;
        }

        CommandLineInterface.waitForUserToContinue( SubscriptionHandler.getSubscriptions().stream().map(  el -> el.toString()  ).collect( Collectors.joining("\n")  ) );
    }

    private static void process_listTerminals() {
        // exit immediately if there are no subscribers
        if (  TerminalHandler.getTerminals().size() == 0  ) {
            CommandLineInterface.waitForUserToContinue("There are no saved terminals at the moment. You will be brought back to the main menu.");
            return;
        }

        CommandLineInterface.waitForUserToContinue( TerminalHandler.getTerminals().toString() );
    }

    private static void process_listCharges() {
        // exit immediately if there are no subscribers
        if (  SubscriberHandler.getSubscribers().size() == 0  ) {
            CommandLineInterface.waitForUserToContinue("There are no saved subscribers at the moment. You will be brought back to the main menu.");
            return;
        }

        int subscriberId = askSubscriberForValidUserOrMinus1("Please enter the id the subscriber for whom to list the charges of the current invoicing period, or -1 to go back to the main menu:\n");
        if (subscriberId == -1) {
            return;
        }

        CommandLineInterface.waitForUserToContinue( SubscriberHandler.getSubscriberById(subscriberId).getCharges().toString() );

    }



    private static int askSubscriberForValidUserOrMinus1(String msg) {
        while (true) {
            int response = CommandLineInterface.askAndGetInt(msg);
            if ( response == -1) {
                return -1;
            }
            if ( SubscriberHandler.checkID(response) ) {
                return response;
            }
            System.out.println("Your response was neither a valid user id nor -1. Please try again.");
        }

    }




}
