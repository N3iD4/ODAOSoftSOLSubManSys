package models;

import DataHandling.SubscriberHandler;
import DataHandling.SubscriptionHandler;
import DataHandling.TerminalHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.function.ToDoubleBiFunction;

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

    public Subscriber() {}

    public Subscriber(Subscriber subscriber) {
        setId(subscriber.id);
        setForename(subscriber.forename);
        setSurname(subscriber.surname);
        setIMSI(subscriber.IMSI);
        setTerminalId(subscriber.terminalId);
        setSubscriptionId(subscriber.subscriptionId);
        setCharges(subscriber.charges);
        setFreeMinutesLeft(subscriber.freeMinutesLeft);
        setDataVolumeLeft(dataVolumeLeft);
    }

    public Subscriber(int id, String forename, String surname, String IMSI, int terminalId, int subscriptionId) {
        setId(id);
        setForename(forename);
        setSurname(surname);
        setIMSI(IMSI);
        setTerminalId(terminalId);
        setSubscriptionId(subscriptionId);
        this.charges = new ArrayList<>();
    }

    @JsonIgnore
    public Subscription getSubscription() {
        return SubscriptionHandler.getSubscriptionById(this.subscriptionId);
    }

    @JsonIgnore
    public Terminal getTerminal() {
        return TerminalHandler.getTerminalById(this.terminalId);
    }

    @JsonIgnore
    public BigDecimal getMaxThroughput(){
        return getTerminal().getSupports4G() ? BigDecimal.valueOf(300) : BigDecimal.valueOf(20);
    }


    public String toString(){
        return "ID: " + this.id + " | Forename: " + this.forename + " | Surname: " + this.surname + " | IMSI: " + this.IMSI + " | Terminal: " + this.getTerminal().getName() + " | Subscription: " + this.getSubscription().getName() + " | free minutes left: " + freeMinutesLeft + "min | data volume left: " + dataVolumeLeft + "Mb";
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
        if(arr.length < 1 || arr.length > 30){
            throw new IllegalArgumentException("Name has too many(>30) or little(<1) letters!");
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
        if(arr.length < 1 || arr.length > 30){
            throw new IllegalArgumentException("Name has too many(>30) or little(<1) letters!");
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

    // returns string for invoice and then resets minutesIncluded, dataVolumeLeft and charges
    public String createInvoice() {
        BigDecimal totalCharges = this.getSubscription().basicFee; // important: also add basicFee to the charges
        int totalDataUsed = 0;
        int totalFreeMinutesUsed = 0;
        int totalPaidMinutesUsed = 0;
        String resString = "======== ======== ======== ========\n=== New invoice for " + forename + ", " + surname + "\n";
        resString += "=== Charges:\n";
        for (ChargeDTO el : charges) {
            resString += " - data used: " + el.getTotalVolumeOfUsedData() + "Mb, free minutes used: " + el.getTotalFreeVoiceMinutes() + "m, paid minutes used: " + el.getTotalPaidVoiceMinutes() + ", charged: " + el.getAppliedCharges() + "€\n";
            totalCharges = totalCharges.add(el.getAppliedCharges());
            totalDataUsed += el.getTotalVolumeOfUsedData();
            totalFreeMinutesUsed += el.getTotalFreeVoiceMinutes();
            totalPaidMinutesUsed += el.getTotalPaidVoiceMinutes();
        }
        resString += "=== Total:\n";
        resString += " - data used: " + totalDataUsed + "Mb, free minutes used: " + totalFreeMinutesUsed + "m, paid minutes used: " + totalPaidMinutesUsed + ", charged: " + totalCharges + "€ (includes basic fee of " + this.getSubscription().getBasicFee() + "€)\n";
        resString += "======== ======== ======== ========\n";

        resetFreeMinutesAndDataVolume();
        this.charges = new ArrayList<>();

        SubscriberHandler.save();

        return resString;
    }


    public void resetFreeMinutesAndDataVolume() {
        this.freeMinutesLeft = this.getSubscription().minutesIncluded;
        this.dataVolumeLeft = this.getSubscription().dataVolumeInMB;
    }
    
}
