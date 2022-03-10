package models;

import java.math.BigDecimal;

public class Subscription {


    String name;
    BigDecimal basicFee;
    int minutesIncluded;
    BigDecimal pricePerExtraMinute;
    int dataVolumeInMB;
    boolean active;

    public Subscription(){};

    public Subscription(String name, BigDecimal basicFee, int minutesIncluded, BigDecimal pricePerExtraMinute, int dataVolumeInMB) {
        this.name = name;
        this.basicFee = basicFee;
        this.minutesIncluded = minutesIncluded;
        this.pricePerExtraMinute = pricePerExtraMinute;
        this.dataVolumeInMB = dataVolumeInMB;
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
