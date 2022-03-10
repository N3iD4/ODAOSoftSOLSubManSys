package models;

import java.math.BigDecimal;

public class Subscription {
    static int[] keys;
    static Subscription[] subscriptions;

    String name;
    BigDecimal basicFee;
    int minutesIncluded;
    BigDecimal pricePerExtraMinute;
    int dataVolumeInMB;

    private Subscription(){};

    private Subscription(String name, BigDecimal basicFee, int minutesIncluded, BigDecimal pricePerExtraMinute, int dataVolumeInMB) {
        this.name = name;
        this.basicFee = basicFee;
        this.minutesIncluded = minutesIncluded;
        this.pricePerExtraMinute = pricePerExtraMinute;
        this.dataVolumeInMB = dataVolumeInMB;
    }



    static int[] getSubscriptionKeys(){
        int[] ret= {};
        return ret;
    }

    static Subscription getSubscriptionByKey(int key){
        Subscription ret = new Subscription();
        return ret;
    }

    private void initializeSubscriptions(){

    }


















    public static int[] getKeys() {
        return keys;
    }

    public static void setKeys(int[] keys) {
        Subscription.keys = keys;
    }

    public static Subscription[] getSubscriptions() {
        return subscriptions;
    }

    public static void setSubscriptions(Subscription[] subscriptions) {
        Subscription.subscriptions = subscriptions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBasicFee() {
        return basicFee;
    }

    public void setBasicFee(BigDecimal basicFee) {
        this.basicFee = basicFee;
    }

    public int getMinutesIncluded() {
        return minutesIncluded;
    }

    public void setMinutesIncluded(int minutesIncluded) {
        this.minutesIncluded = minutesIncluded;
    }

    public BigDecimal getPricePerExtraMinute() {
        return pricePerExtraMinute;
    }

    public void setPricePerExtraMinute(BigDecimal pricePerExtraMinute) {
        this.pricePerExtraMinute = pricePerExtraMinute;
    }

    public int getDataVolumeInMB() {
        return dataVolumeInMB;
    }

    public void setDataVolumeInMB(int dataVolumeInMB) {
        this.dataVolumeInMB = dataVolumeInMB;
    }
}
