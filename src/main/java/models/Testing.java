package models;

import DataHandling.JsonSave;
import DataHandling.SubscriberHandler;

public class Testing {

    public static void main (String[] args){

        TestSubscriberHandler();

        /*Subscriber subscriber = new Subscriber();
        //subscriber.setForename("kgaz43o78"); // no
        //Grenzen
        subscriber.setForename("AZ");
        subscriber.setForename("az");
        //subscriber.setForename("@@");
        //subscriber.setForename("[[");
        //subscriber.setForename("``");
        //subscriber.setForename("{{");
        subscriber.setForename("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"); //yes
        //subscriber.setForename("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"); //no >30

        //subscriber.setTerminalType(new Terminal()); //yes
        //subscriber.setTerminalType(new Subscriber()); //no

        subscriber.setIMSI("1234567890"); //yes
        //subscriber.setIMSI("123456789"); //no
        //subscriber.setIMSI("12345678901"); //no
        //subscriber.setIMSI("123456789s"); //no
        //subscriber.setIMSI("123ahsjdhf"); //no
        */

    }


    public static void TestSubscriberHandler() {
        Subscriber sub1 = new Subscriber(0,"Hans","Mueller","2929292929",1,1);
        Subscriber sub2 = new Subscriber(0,"franz","meier","2929292429",1,1);
        Subscriber sub3 = new Subscriber(0,"hubert","schuller","2629292929",1,1);

        SubscriberHandler.addSubscriber(sub1);
        SubscriberHandler.addSubscriber(sub2);
        SubscriberHandler.addSubscriber(sub3);

        JsonSave.saveDataSubscribers();
        JsonSave.laodDataSubscriber();
        System.out.println(SubscriberHandler.ToString() + "\n" + SubscriberHandler.subscribers.size());

    }
}
