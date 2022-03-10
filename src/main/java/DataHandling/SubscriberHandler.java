package DataHandling;

import models.ChargeDTO;
import models.Subscriber;
import models.SubscriptionOld;
import models.Terminal;

import java.util.ArrayList;
import java.util.List;

public class SubscriberHandler {

    private List<Subscriber> subscribers;

    public SubscriberHandler() {
        this.subscribers = new ArrayList<Subscriber>();
    }

    public void addSubscriber(String forename, String surname, String IMSI, Terminal terminalType, SubscriptionOld subscriptionOldType, ArrayList<ChargeDTO> charges) {
        int id=1;
        if(subscribers.size()!=0)
            id = this.subscribers.get(subscribers.size()-1).getId()+1;
        subscribers.add(new Subscriber(forename, surname, IMSI, terminalType, subscriptionOldType,null));// TODO add id
    }

    public void addSubscriber(Subscriber sub) {
        if(subscribers.size()!=0)
            sub.setId(this.subscribers.get(subscribers.size()-1).getId()+1);
        else
            sub.setId(0);
        subscribers.add(sub);// TODO add id
    }

    public boolean deleteSub(Subscriber sub) {

        int index = getIndexSubWithIMSI(sub);
        if(index!=-1) {
            this.subscribers.remove(index);
            return true;
        } else
            return false;
    }

    public boolean editSub(Subscriber sub) {
        if(!deleteSub(sub))
            return false;
        addSubscriber(sub);
        return true;

    }

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
    private int getIndexSubWithIMSI(Subscriber sub) {
        Subscriber[] tempar = (Subscriber[]) subscribers.toArray();
        for ( int i =0; i<tempar.length;i++) {
            if(tempar[i].getMSIN() == sub.getMSIN()) //TODO change to get IMSI
               return i;
        }

        return -1;
    }

}
