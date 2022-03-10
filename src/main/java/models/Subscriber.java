package models;

import java.util.ArrayList;

public class Subscriber {

    int id;
    String forename;
    String surname;
    String IMSI;
    String MCC;
    String MNC;
    String MSIN;
    Terminal terminalType;
    SubscriptionOld subscriptionOldType;
    ArrayList<ChargeDTO> charges;


    public Subscriber(){

    }

    public Subscriber(int id, String forename, String surname, String IMSI, Terminal terminalType, SubscriptionOld subscriptionOldType, ArrayList<ChargeDTO> charges) {
        setId(id);
        setForename(forename);
        setSurname(surname);
        //setIMSI(IMSI);
        setTerminalType(terminalType);
        setSubscriptionType(subscriptionOldType);
        setCharges(charges);
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

   /* public String getIMSI() {
        return IMSI;
    }

    public void setIMSI(String IMSI) {
        char[] arr = IMSI.toCharArray();
        //ÜBERPRÜFEN WIE OBEN OB NUR ZAHLEN
        if(arr.length != 9){
            throw new IllegalArgumentException("Number too long!");
        }
        this.IMSI = IMSI;
    }
*/



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
            throw new IllegalArgumentException("Not 3 digits long!");
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
            throw new IllegalArgumentException("Not 3 digits long!");
        }
        for(int i=0; i<arr.length; i++){
            if(arr[i] < 47 || arr[i] > 57){
                throw new IllegalArgumentException("Not a Number!");
            }
        }
        this.MSIN = MSIN;
    }



    public Terminal getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(Terminal terminalType) {
        if(!(terminalType.getClass() != Terminal.class)){
            throw new RuntimeException("Not a Terminal!");
        }
        this.terminalType = terminalType;
    }

    public SubscriptionOld getSubscriptionType() {
        return subscriptionOldType;
    }

    public void setSubscriptionType(SubscriptionOld subscriptionOldType) {
        if(!(subscriptionOldType.getClass() != SubscriptionOld.class)){
            throw new RuntimeException("Not a Terminal!");
        }
        this.subscriptionOldType = subscriptionOldType;
    }

    public ArrayList<ChargeDTO> getCharges() {
        return charges;
    }

   public void setCharges(ArrayList<ChargeDTO> charges) {
        for(int i=0; i<charges.size(); i++){
            if(!(charges.get(i).getClass() != ChargeDTO.class)){
                throw new RuntimeException("Not a Terminal!");
            }
        }
        this.charges = charges;
    }

    public static void main(String[] args){
        Subscriber subscriber = new Subscriber();
        subscriber.setForename("");
        PhairPhone l = new PhairPhone();
        subscriber.setTerminalType(l);
        System.out.println(subscriber.getForename());
        System.out.println(subscriber.getTerminalType());
    }
}
