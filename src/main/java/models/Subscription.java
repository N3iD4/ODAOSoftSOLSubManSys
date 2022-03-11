package models;

import java.math.BigDecimal;

public class Subscription {

    int id;
    String name;
    BigDecimal basicFee;
    int minutesIncluded;
    BigDecimal pricePerExtraMinute;
    int dataVolumeInMB;
    boolean isActive;


    public Subscription(){}


    public Subscription(int id, String name, BigDecimal basicFee, int minutesIncluded, BigDecimal pricePerExtraMinute, int dataVolumeInMB, boolean isActive) {
        this.id = id;
        this.name = name;
        this.basicFee = basicFee;
        this.minutesIncluded = minutesIncluded;
        this.pricePerExtraMinute = pricePerExtraMinute;
        this.dataVolumeInMB = dataVolumeInMB;
        this.isActive = isActive;
    }

    public Subscription(String name, BigDecimal basicFee, int minutesIncluded, BigDecimal pricePerExtraMinute, int dataVolumeInMB, boolean isActive) {
        this.name = name;
        this.basicFee = basicFee;
        this.minutesIncluded = minutesIncluded;
        this.pricePerExtraMinute = pricePerExtraMinute;
        this.dataVolumeInMB = dataVolumeInMB;
        this.isActive = isActive;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public boolean getIsActive() { return isActive; }
    public void setIsActive(boolean active) { this.isActive = active; }

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
    public void setPricePerExtraMinute(BigDecimal pricePerExtraMinute) { this.pricePerExtraMinute = pricePerExtraMinute; }

    public int getDataVolumeInMB() {
        return dataVolumeInMB;
    }
    public void setDataVolumeInMB(int dataVolumeInMB) {
        this.dataVolumeInMB = dataVolumeInMB;
    }


    @Override
    public String toString() {
        return  "ID: " + id +
                " | name: " + name +
                " | basic fee: " + basicFee +
                " | minutes included: " + minutesIncluded +
                " | price per extra minute: " + pricePerExtraMinute +
                " | data volume in MB: " + dataVolumeInMB +
                " | is active: " + isActive;
    }
}
