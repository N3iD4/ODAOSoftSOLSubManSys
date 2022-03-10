public class Subscription {
    String name;
    double fee;
    int minutesIncluded;
    double pricePerExtraMinute;
    int dataVolumeInMB;

    public Subscription(){}

    public Subscription(String name, double fee, int minutesIncluded, double pricePerExtraMinute, int dataVolumeInMB) {
        this.name = name;
        this.fee = fee;
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

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public int getMinutesIncluded() {
        return minutesIncluded;
    }

    public void setMinutesIncluded(int minutesIncluded) {
        this.minutesIncluded = minutesIncluded;
    }

    public double getPricePerExtraMinute() {
        return pricePerExtraMinute;
    }

    public void setPricePerExtraMinute(double pricePerExtraMinute) {
        this.pricePerExtraMinute = pricePerExtraMinute;
    }

    public int getDataVolumeInMB() {
        return dataVolumeInMB;
    }

    public void setDataVolumeInMB(int dataVolumeInMB) {
        this.dataVolumeInMB = dataVolumeInMB;
    }
}
