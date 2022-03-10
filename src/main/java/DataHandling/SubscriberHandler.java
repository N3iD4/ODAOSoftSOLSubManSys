package DataHandling;

import models.*;

import java.util.ArrayList;
import java.util.List;

public class SubscriberHandler {

    private List<Subscriber> subscribers;

    public SubscriberHandler() {
        this.subscribers = new ArrayList<Subscriber>();
    }

    public void addSubscriber(Subscriber sub) throws IllegalArgumentException{
        try {
            getIndexWithIMSI(sub);
            throw new IllegalArgumentException("Subscriber with IMSI=" + sub.getIMSI() + " is already stored");
        } catch (IllegalArgumentException e) {}
        if(subscribers.size()!=0)
            sub.setId(this.subscribers.get(subscribers.size()-1).getId()+1);
        else
            sub.setId(0);
        subscribers.add(sub);
    }

    public void deleteSub (Subscriber sub) throws IllegalArgumentException {
        int index = -1;
        try {
            index = getIndexWithIMSI(sub);
        } catch (IllegalArgumentException e) {
             throw e;
        }
        if(index!=-1)
            this.subscribers.remove(index);
    }

    // removes and adds Subscriber related to IMSI
    public void editSub(Subscriber sub) {
        try {
            deleteSub(sub);
            addSubscriber(sub);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    // gives Subscriber Obj realted to IMSI
    public Subscriber getSubscriberByIMSI(String IMSI) throws IllegalArgumentException{
        return this.subscribers.get(getIndexWithIMSI(IMSI));
    }

    //gives List of all stored Subscribers
    public List<Subscriber> getSubscribers() {
        return this.subscribers;
    }

    @Override
    public String toString() {
        return "SubscriberHandler{" +
                "subscribers=" + subscribers +
                '}';
    }

    //gives index of given sub related to IMSI, otherwise -1
    private int getIndexWithIMSI(Subscriber sub) throws IllegalArgumentException{
        try {
            return getIndexWithIMSI(sub.getIMSI());
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    private int getIndexWithIMSI(String IMSI) throws IllegalArgumentException{
        Subscriber[] tempar = (Subscriber[]) subscribers.toArray();
        for ( int i =0; i<tempar.length;i++) {
            if(tempar[i].getIMSI().equals(IMSI))
                return i;
        }
        throw new IllegalArgumentException("User not Found related to IMSI=" + IMSI);
    }

    public static void main(String args[]) {

        Subscriber sub1 = new Subscriber(0,"hans","mueller","4554554559","455","45","4554554559",new PearAphone4s(),new SubscriptionOld(), new ArrayList<ChargeDTO>());
        // Subscriber sub2 = new Subscriber("franz","krtoffel","HK104",new Terminal(),new SubscriptionOld(), new ArrayList<ChargeDTO>());
        // Subscriber sub3 = new Subscriber("franziska","karotte","HK105",new Terminal(),new SubscriptionOld(), new ArrayList<ChargeDTO>());

        SubscriberHandler sh = new SubscriberHandler();
        sh.addSubscriber(sub1);
        //sh.addSubscriber(sub2);
        //sh.addSubscriber(sub3);

        System.out.println(sh.toString());
    }

}
