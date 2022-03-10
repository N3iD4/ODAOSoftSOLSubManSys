package models;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Subscriber {
    private int id;
    private String forename;
    private String surname;
    private String IMSI;
    private int terminalId;
    private int subscriptionId;
    private ArrayList<ChargeDTO> charges;
    private int freeMinutesLeft;
    private int dataVolumeLeft;

    // Currently not used:
    private String MCC;
    private String MNC;
    private String MSIN;

    public int getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(int terminalId) {
        this.terminalId = terminalId;
    }

    public int getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Subscriber() {

    }

    public Subscriber(Subscriber subscriber) {
        setId(subscriber.id);
        setForename(subscriber.forename);
        setSurname(subscriber.surname);
        setIMSI(subscriber.IMSI);
        setMCC(subscriber.MCC);
        setMNC(subscriber.MNC);
        setMSIN(subscriber.MSIN);
        setTerminalId(subscriber.terminalId);
        setSubscriptionId(subscriber.subscriptionId);
        setCharges(subscriber.charges);
    }



    public Subscriber(int id, String forename, String surname, String IMSI, int terminalId, int subscriptionId) {
        setId(id);
        setForename(forename);
        setSurname(surname);
        setIMSI(IMSI);
        setTerminalId(terminalId);
        setSubscriptionId(subscriptionId);
        setCharges(charges);
    }


    // Constructor with MCC, MNC, MSIN
    public Subscriber(int id, String forename, String surname, String IMSI, String MCC, String MNC, String MSIN, int terminalId, int subscriptionId, ArrayList<ChargeDTO> charges) {
        setId(id);
        setForename(forename);
        setSurname(surname);
        setIMSI(IMSI);
        setMCC(MCC);
        setMNC(MNC);
        setMSIN(MSIN);
        setTerminalId(terminalId);
        setSubscriptionId(subscriptionId);
        setCharges(charges);
    }


    public Subscription getSubscription() {
        return null;
        //return SubscriptionHandler.getSubscriptionById(this.subscriptionId);
    }


    public Subscription getTerminal() {
        return null;
        //return TerminalHandler.getTerminalById(this.terminalId);
    }

    public BigDecimal getMaxThroughput(){
        return      BigDecimal.valueOf(0.0);
    }


    public String toString(){
        String ret;
        ret = "ID: " + this.id + " | Forename: " + this.forename + " | Surname: " + this.surname + " | ISMI: " + this.IMSI + " | Terminaltype: " + this.getTerminal() + " | Subscription: " + this.getSubscription();
        return ret;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //Überprüfen auf richtigkeit
    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        if(forename == null){
            throw new IllegalArgumentException("Value is null!");
        }
        char[] arr = forename.toCharArray();
        if(arr.length <= 1 || arr.length > 30){
            throw new IllegalArgumentException("Name has too many(>30) or little(<2) letters!");
        }
        for(int i=0; i<arr.length; i++) {
            if (arr[i] < 65) {
                throw new IllegalArgumentException("Not a letter!");
            } else if (arr[i] > 90) {
                if (arr[i] < 97 || (arr[i] > 122)) {
                    throw new IllegalArgumentException("Not a letter!");
                }
            }
        }
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if(surname == null){
            throw new IllegalArgumentException("Value is null!");
        }
        char[] arr = surname.toCharArray();
        if(arr.length <= 1 || arr.length > 30){
            throw new IllegalArgumentException("Name has too many(>30) or little(<2) letters!");
        }
        for(int i=0; i<arr.length; i++) {
            if (arr[i] < 65) {
                throw new IllegalArgumentException("Not a letter!");
            } else if (arr[i] > 90) {
                if (arr[i] < 97 || (arr[i] > 122)) {
                    throw new IllegalArgumentException("Not a letter!");
                }
            }
        }
        this.surname = surname;
    }

    public String getIMSI() {
        return IMSI;
    }

    public void setIMSI(String IMSI) {
        char[] arr = IMSI.toCharArray();
        //ÜBERPRÜFEN WIE OBEN OB NUR ZAHLEN
        if(arr.length != 10){
            throw new IllegalArgumentException("Number not 10 digits long!");
        }
        for(int i=0; i<arr.length; i++){
            if(arr[i] < 47 || arr[i] > 57){
                throw new IllegalArgumentException("Not a Number!");
            }
        }
        this.IMSI = IMSI;
    }




    public String getMCC() {
        return MCC;
    }

    public void setMCC(String MCC) {
        if(MCC == null){
            throw new IllegalArgumentException("Value is Null!");
        }
        char[] arr = MCC.toCharArray();
        if(arr.length != 3){
            throw new IllegalArgumentException("Not 3 digits long!");
        }
        for(int i=0; i<arr.length; i++){
            if(arr[i] < 47 || arr[i] > 57){
                throw new IllegalArgumentException("Not a Number!");
            }
        }
        this.MCC = MCC;
    }

    public String getMNC() {
        return MNC;
    }

    public void setMNC(String MNC) {
        if(MNC == null){
            throw new IllegalArgumentException("Value is Null!");
        }
        char[] arr = MNC.toCharArray();
        if(arr.length != 2){
            throw new IllegalArgumentException("Not 2 digits long!");
        }
        for(int i=0; i<arr.length; i++){
            if(arr[i] < 47 || arr[i] > 57){
                throw new IllegalArgumentException("Not a Number!");
            }
        }
        this.MNC = MNC;
    }

    public String getMSIN() {
        return MSIN;
    }

    public void setMSIN(String MSIN) {
        if(MSIN == null){
            throw new IllegalArgumentException("Value is Null!");
        }
        char[] arr = MSIN.toCharArray();
        if(arr.length != 10){
            throw new IllegalArgumentException("Not 10 digits long!");
        }
        for(int i=0; i<arr.length; i++){
            if(arr[i] < 47 || arr[i] > 57){
                throw new IllegalArgumentException("Not a Number!");
            }
        }
        this.MSIN = MSIN;
    }





    public ArrayList<ChargeDTO> getCharges() {
        return charges;
    }

   public void setCharges(ArrayList<ChargeDTO> charges) {
        for(int i=0; i<charges.size(); i++){
            if((charges.get(i).getClass() != ChargeDTO.class)){
                throw new RuntimeException("Not a Terminal!");
            }
        }
        this.charges = charges;
    }

    public void addCharge(ChargeDTO charge){
        this.charges.add(charge);
    }



    public int getFreeMinutesLeft() {
        return freeMinutesLeft;
    }

    public void setFreeMinutesLeft(int freeMinutesLeft) {
        this.freeMinutesLeft = freeMinutesLeft;
    }

    public int getDataVolumeLeft() {
        return dataVolumeLeft;
    }

    public void setDataVolumeLeft(int dataVolumeLeft) {
        this.dataVolumeLeft = dataVolumeLeft;
    }
    
}
