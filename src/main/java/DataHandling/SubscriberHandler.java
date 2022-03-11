package DataHandling;

import models.*;

import java.util.ArrayList;
import java.util.List;

public class SubscriberHandler {

    public static List<Subscriber> subscribers = new ArrayList<Subscriber>();

    private SubscriberHandler() {
    }

    public static void addSubscriber(Subscriber sub) throws IllegalArgumentException{
        try {
            getIndexWithIMSI(sub);
            throw new IllegalArgumentException("Subscriber with IMSI=" + sub.getIMSI() + " is already stored");
        } catch (IllegalArgumentException e) {}
        if(subscribers.size()!=0)
            sub.setId(SubscriberHandler.subscribers.get(subscribers.size()-1).getId()+1);
        else
            sub.setId(0);
        subscribers.add(sub);
    }

    public static void deleteSub (Subscriber sub) throws IllegalArgumentException {
        int index = -1;
        try {
            index = getIndexWithIMSI(sub);
        } catch (IllegalArgumentException e) {
             throw e;
        }
        if(index!=-1)
            SubscriberHandler.subscribers.remove(index);
    }

    // removes and adds Subscriber related to IMSI
    public static void editSub(Subscriber sub) {
        try {
            deleteSub(sub);
            addSubscriber(sub);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    // gives Subscriber Obj realted to IMSI
    public static Subscriber getSubscriberByIMSI(String IMSI) throws IllegalArgumentException{
        return SubscriberHandler.subscribers.get(getIndexWithIMSI(IMSI));
    }

    //gives List of all stored Subscribers
    public static List<Subscriber> getSubscribers() {
        return SubscriberHandler.subscribers;
    }

    public static String ToString() {
        return "SubscriberHandler{" +
                "subscribers=" + subscribers +
                '}';
    }

    //gives index of given sub related to IMSI, otherwise -1
    public static int getIndexWithIMSI(Subscriber sub) throws IllegalArgumentException{
        try {
            return getIndexWithIMSI(sub.getIMSI());
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    public static int getIndexWithIMSI(String IMSI) throws IllegalArgumentException{
        Object[] tempar =  subscribers.toArray();
        for ( int i =0; i<tempar.length;i++) {
            if(((Subscriber)tempar[i]).getIMSI().equals(IMSI))
                return i;
        }
        throw new IllegalArgumentException("User not Found related to IMSI=" + IMSI);
    }

    public static Subscriber getSubscriberById(int id) throws IllegalArgumentException {
      if(checkID(id))
          return subscribers.get(id);
      throw new IllegalArgumentException("Subscriber don't found");
    }

    public static boolean checkID(int id) {
        Object[] tempar =  subscribers.toArray();
        for ( int i =0; i<tempar.length;i++) {
            if(((Subscriber)tempar[i]).getId() == id)
                return true;
        }
        return false;
    }

    public static void save() {
        JsonSave.saveDataSubscribers();
    }

    public static boolean hasAnySubscriberSubscriptionWithId(int subscriptionId) {
        return subscribers.stream().anyMatch( el -> el.getSubscriptionId() == subscriptionId );
    }


    public static boolean hasAnySubscriberTerminalWithId(int terminalId) {
        return subscribers.stream().anyMatch( el -> el.getTerminalId() == terminalId );
    }


    public static void load() {
        JsonSave.laodDataSubscriber();

    }

}
