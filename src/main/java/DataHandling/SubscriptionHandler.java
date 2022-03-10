package DataHandling;

import models.Subscription;
import models.Subscription;

import java.util.ArrayList;
import java.util.List;

public class SubscriptionHandler {

    public static List<Subscription> subscribers = new ArrayList<Subscription>();

    private SubscriptionHandler() {
    }

    public static void addSubscription(Subscription sub) throws IllegalArgumentException{
        try {
            getIndexWithIMSI(sub);
            throw new IllegalArgumentException("Subscription with IMSI=" + sub.getIMSI() + " is already stored");
        } catch (IllegalArgumentException e) {}
        if(subscribers.size()!=0)
            sub.setId(SubscriptionHandler.subscribers.get(subscribers.size()-1).getId()+1);
        else
            sub.setId(0);
        subscribers.add(sub);
    }

    public static void deleteSub (Subscription sub) throws IllegalArgumentException {
        int index = -1;
        try {
            index = getIndexWithIMSI(sub);
        } catch (IllegalArgumentException e) {
             throw e;
        }
        if(index!=-1)
            SubscriptionHandler.subscribers.remove(index);
    }

    // removes and adds Subscription related to IMSI
    public static void editSub(Subscription sub) {
        try {
            deleteSub(sub);
            addSubscription(sub);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    // gives Subscription Obj realted to IMSI
    public static Subscription getSubscriptionByIMSI(String IMSI) throws IllegalArgumentException{
        return SubscriptionHandler.subscribers.get(getIndexWithIMSI(IMSI));
    }

    //gives List of all stored Subscriptions
    public static List<Subscription> getSubscriptions() {
        return SubscriptionHandler.subscribers;
    }

    public static String ToString() {
        return "SubscriptionHandler{" +
                "subscribers=" + subscribers +
                '}';
    }

    //gives index of given sub related to IMSI, otherwise -1
    public static int getIndexWithIMSI(Subscription sub) throws IllegalArgumentException{
        try {
            return getIndexWithIMSI(sub.getIMSI());
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    public static int getIndexWithIMSI(String IMSI) throws IllegalArgumentException{
        Object[] tempar =  subscribers.toArray();
        for ( int i =0; i<tempar.length;i++) {
            if(((Subscription)tempar[i]).getIMSI().equals(IMSI))
                return i;
        }
        throw new IllegalArgumentException("User not Found related to IMSI=" + IMSI);
    }

    public static Subscription getSubscriptionById(int id) throws IllegalArgumentException {
      if(checkID(id))
          return subscribers.get(id);
      throw new IllegalArgumentException("Subscription don't found");
    }

    public static boolean checkID(int id) {
        Object[] tempar =  subscribers.toArray();
        for ( int i =0; i<tempar.length;i++) {
            if(((Subscription)tempar[i]).getId() == id)
                return true;
        }
        return false;
    }



    public static void main(String args[]) {

        //Subscription sub1 = new Subscription(0,"hans","mueller","4554554559","455","45","4554554559",new PearAphone4s(),new SubscriptionOld(), new ArrayList<ChargeDTO>());
        // Subscription sub2 = new Subscription("franz","krtoffel","HK104",new Terminal(),new SubscriptionOld(), new ArrayList<ChargeDTO>());
        // Subscription sub3 = new Subscription("franziska","karotte","HK105",new Terminal(),new SubscriptionOld(), new ArrayList<ChargeDTO>());

        SubscriptionHandler sh = new SubscriptionHandler();
        //sh.addSubscription(sub1);
        //sh.addSubscription(sub2);
        //sh.addSubscription(sub3);

        System.out.println(sh.toString());
    }

}
