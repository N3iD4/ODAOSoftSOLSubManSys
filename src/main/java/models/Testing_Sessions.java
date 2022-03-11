package models;

import DataHandling.SubscriberHandler;
import DataHandling.SubscriptionHandler;
import DataHandling.TerminalHandler;

public class Testing_Sessions {

    public static void main (String[] args){


        Test_addTwoUsersWithSameImsi();


    }


    public static void Test_addTwoUsersWithSameImsi() {
        SubscriptionHandler.init();
        TerminalHandler.init();
        Subscriber newSubscriber1 = new Subscriber(1, "Hans", "Peters", "1111111111", 0, 0);
        Subscriber newSubscriber2 = new Subscriber(2, "Hans", "Peters", "1111111111", 0, 0);
        SubscriberHandler.addSubscriber(newSubscriber1);
        SubscriberHandler.addSubscriber(newSubscriber2);
    }








}
