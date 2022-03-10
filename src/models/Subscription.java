package models;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Subscription {
    static ArrayList<Subscription> subscriptions;
    String name;
    BigDecimal fee;
    int minutesIncluded;
    BigDecimal pricePerExtraMinute;
    int dataVolumeInMB;

    public Subscription(){}

    public Subscription(String name, BigDecimal fee, int minutesIncluded, BigDecimal pricePerExtraMinute, int dataVolumeInMB) {
        this.name = name;
        this.fee = fee;
        this.minutesIncluded = minutesIncluded;
        this.pricePerExtraMinute = pricePerExtraMinute;
        this.dataVolumeInMB = dataVolumeInMB;
    }


    //static int[] getSubscriptionKeys(){
        //vielleicht mit enum
   // }





    public String getName() {
        return name;
    }


    //getter public setter private
    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
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
