package models;

import java.util.ArrayList;

public class Invoice {
    Subscriber subscriber;
    ArrayList<ChargeDTO> charges = new ArrayList<>();

    public Invoice(){}

    public Invoice(Subscriber subscriber, ArrayList<ChargeDTO> charges) {
        this.subscriber = subscriber;
        this.charges = charges;
    }


    public Invoice sendInvoice(){
        Invoice ret = new Invoice();
        return ret;
    }






    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public ArrayList<ChargeDTO> getCharges() {
        return charges;
    }

    public void setCharges(ArrayList<ChargeDTO> charges) {
        this.charges = charges;
    }
}
