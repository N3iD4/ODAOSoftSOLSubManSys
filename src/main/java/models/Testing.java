package models;

import DataHandling.JsonSave;
import DataHandling.SubscriberHandler;
import DataHandling.SubscriptionHandler;
import DataHandling.TerminalHandler;

import java.math.BigDecimal;

public class Testing {

    public static void main (String[] args){

        TestSubscriberHandler();
        TestSubscriptionsHandler();
        TestTerminalHandler();

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
        System.out.println("=============================SUBSCRIBERS============================");

        Subscriber sub1 = new Subscriber(0,"Hans","Mueller","2929292929",1,1);
        Subscriber sub2 = new Subscriber(0,"franz","meier","2929292429",1,1);
        Subscriber sub3 = new Subscriber(0,"hubert","schuller","2629292929",1,1);

        String tmp ="SubscriberHandler{subscribers=[ID: 0 | Forename: Hans | Surname: Mueller | ISMI: 2929292929 | Terminaltype: null | Subscription: null, ID: 1 | Forename: franz | Surname: meier | ISMI: 2929292429 | Terminaltype: null | Subscription: null, ID: 2 | Forename: hubert | Surname: schuller | ISMI: 2629292929 | Terminaltype: null | Subscription: null]}";

        SubscriberHandler.addSubscriber(sub1);
        SubscriberHandler.addSubscriber(sub2);
        SubscriberHandler.addSubscriber(sub3);

        SubscriberHandler.save();
        SubscriberHandler.load();
        System.out.println(SubscriberHandler.ToString());
        System.out.println("true==" + SubscriberHandler.ToString().equals(tmp));
        System.out.println("3==" + SubscriberHandler.subscribers.size());

        SubscriberHandler.deleteSub(sub2);
        System.out.println("2==" + SubscriberHandler.subscribers.size());
        SubscriberHandler.addSubscriber(sub2);
        System.out.println("0==" + SubscriberHandler.getSubscriberById(SubscriberHandler.getIndexWithIMSI(sub1)).getId());
        System.out.println("2==" + SubscriberHandler.getSubscriberById(SubscriberHandler.getIndexWithIMSI(sub2)).getId());

        sub2.setSurname("FranzPetergeaendert");
        SubscriberHandler.editSub(sub2);
        System.out.println("false==" + SubscriberHandler.getSubscriberById(sub2.getId()).getSurname().equals("meier"));
        System.out.println(SubscriberHandler.getSubscriberById(sub2.getId()));

        System.out.println("=========================================================================");
    }

    public static void TestSubscriptionsHandler() {
        System.out.println("=============================SUBSCRIPTIONS============================");

        Subscription sub1 = new Subscription("freeTarif",new BigDecimal("10.0"),3,new BigDecimal("83.0"),2, true);
        Subscription sub2 = new Subscription("mainTarif",new BigDecimal("10.0"),3,new BigDecimal("887.0"),2, true);
        Subscription sub3 = new Subscription("extend",new BigDecimal("11.0"),3,new BigDecimal("84.0"),2, true);

        String tmp ="SubscriptionHandler{subscriptions=[id: 0| name: freeTarif| basicFee: 10.0| minutesIncluded: 3| pricePerExtraMinute: 83.0| dataVolumeInMB: 2| isActive: false, id: 1| name: mainTarif| basicFee: 10.0| minutesIncluded: 3| pricePerExtraMinute: 887.0| dataVolumeInMB: 2| isActive: false, id: 2| name: extend| basicFee: 11.0| minutesIncluded: 3| pricePerExtraMinute: 84.0| dataVolumeInMB: 2| isActive: false]}";

        SubscriptionHandler.addSubscription(sub1);
        SubscriptionHandler.addSubscription(sub2);
        SubscriptionHandler.addSubscription(sub3);

        SubscriptionHandler.save();
        SubscriptionHandler.load();

        System.out.println(SubscriptionHandler.ToString());
        System.out.println("true==" + SubscriptionHandler.ToString().equals(tmp));
        System.out.println("3==" + SubscriptionHandler.subscriptions.size());

        SubscriptionHandler.deleteSub(sub2);
        System.out.println("2==" + SubscriptionHandler.subscriptions.size());
        SubscriptionHandler.addSubscription(sub2);
        System.out.println("0==" + SubscriptionHandler.getSubscriptionById(SubscriptionHandler.getIndexWithID(sub1)).getId());
        System.out.println("2==" + SubscriptionHandler.getSubscriptionById(SubscriptionHandler.getIndexWithID(sub2)).getId());

        sub2.setName("FranzPetergeaendert");
        SubscriptionHandler.editSub(sub2);
        System.out.println("false==" + SubscriptionHandler.getSubscriptionById(sub2.getId()).getName().equals("mainTarif"));
        System.out.println(SubscriptionHandler.getSubscriptionById(sub2.getId()));

        System.out.println("=========================================================================");
    }

    public static void TestTerminalHandler() {
        System.out.println("=============================Terminals============================");

        Terminal term1 = new Terminal("Funkmast",true);
        Terminal term2 = new Terminal("Antenne",false);
        Terminal term3 = new Terminal("Dosentelefon",true);

        String tmp ="TerminalHandler{terminals=[id: 0| name: Funkmast| supports4G: true| isActive: false, id: 1| name: Antenne| supports4G: false| isActive: false, id: 2| name: Dosentelefon| supports4G: true| isActive: false]}";

        TerminalHandler.addTerminal(term1);
        TerminalHandler.addTerminal(term2);
        TerminalHandler.addTerminal(term3);

        TerminalHandler.save();
        TerminalHandler.load();

        System.out.println(TerminalHandler.ToString());
        System.out.println("true==" + TerminalHandler.ToString().equals(tmp));
        System.out.println("3==" + TerminalHandler.terminals.size());

        TerminalHandler.deleteTerminal(term2);
        System.out.println("2==" + TerminalHandler.terminals.size());
        TerminalHandler.addTerminal(term2);
        System.out.println("0==" + TerminalHandler.getTerminalById(TerminalHandler.getIndexWithID(term1)).getId());
        System.out.println("2==" + TerminalHandler.getTerminalById(TerminalHandler.getIndexWithID(term2)).getId());

        term2.setName("FranzPetergeaendert");
        TerminalHandler.editTerminal(term2);
        System.out.println("false==" + TerminalHandler.getTerminalById(term2.getId()).getName().equals("mainTarif"));
        System.out.println(TerminalHandler.getTerminalById(term2.getId()));

        System.out.println("=========================================================================");
    }
}
