package DataHandling;

import models.Subscriber;
import models.Subscription;

import java.util.ArrayList;
import java.util.List;

public class SubscriptionHandler {

    public static List<Subscription> subscriptions = new ArrayList<Subscription>();

    private SubscriptionHandler() {
    }

    public static void addSubscription(Subscription sub) throws IllegalArgumentException{
        try {
            getIndexWithID(sub.getId());
            throw new IllegalArgumentException("Subscription with Id=" + sub.getId() + " is already stored");
        } catch (IllegalArgumentException e) {}
        if(subscriptions.size()!=0)
            sub.setId(SubscriptionHandler.subscriptions.get(subscriptions.size()-1).getId()+1);
        else
            sub.setId(0);
        subscriptions.add(sub);
    }

    public static void deleteSub (Subscription sub) throws IllegalArgumentException {
        int index = -1;
        try {
            index = getIndexWithID(sub.getId());
        } catch (IllegalArgumentException e) {
             throw e;
        }
        if(index!=-1)
            SubscriptionHandler.subscriptions.remove(index);
    }

    // removes and adds Subscription related to ID
    public static void editSub(Subscription sub) {
        try {
            deleteSub(sub);
            addSubscription(sub);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    //gives List of all stored Subscriptions
    public static List<Subscription> getSubscriptions() {
        return SubscriptionHandler.subscriptions;
    }

    public static String ToString() {
        return "SubscriptionHandler{" +
                "subscriptions=" + subscriptions +
                '}';
    }
    public static int getIndexWithID(Subscription sub) throws IllegalArgumentException{
        try {
            return getIndexWithID(sub.getId());
        } catch(IllegalArgumentException e) {
            throw e;
        }

    }
    public static int getIndexWithID(int id) throws IllegalArgumentException{
        Object[] tempar =  subscriptions.toArray();
        for ( int i =0; i<tempar.length;i++) {
            if(((Subscription)tempar[i]).getId() == id)
                return i;
        }
        throw new IllegalArgumentException("User not Found related to id=" + id);
    }

    public static Subscription getSubscriptionById(int id) throws IllegalArgumentException {
      if(checkID(id)) {
          Object[] tempar = subscriptions.toArray();
          for (int i = 0; i < tempar.length; i++) {
              if (((Subscription) tempar[i]).getId() == id)
                  return subscriptions.get(i);
          }
      }
      throw new IllegalArgumentException("Subscription don't found");
    }

    public static boolean checkID(int id) {
        Object[] tempar =  subscriptions.toArray();
        for ( int i =0; i<tempar.length;i++) {
            if(((Subscription)tempar[i]).getId() == id)
                return true;
        }
        return false;
    }

    public static void save() {
        JsonSave.saveDataSubscriptions();
    }

    public static void load() {
        JsonSave.laodDataSubscriptions();
    }

}
