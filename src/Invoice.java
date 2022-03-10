public class Invoice {
    Subscriber subscriber;
    int totalVolumeOfUsedData;
    int totalVoiceMinutes;
    double appliedCharges;

    public Invoice(){}

    public Invoice(Subscriber subscriber, int totalVolumeOfUsedData, int totalVoiceMinutes, double appliedCharges) {
        this.subscriber = subscriber;
        this.totalVolumeOfUsedData = totalVolumeOfUsedData;
        this.totalVoiceMinutes = totalVoiceMinutes;
        this.appliedCharges = appliedCharges;
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

    public int getTotalVolumeOfUsedData() {
        return totalVolumeOfUsedData;
    }

    public void setTotalVolumeOfUsedData(int totalVolumeOfUsedData) {
        this.totalVolumeOfUsedData = totalVolumeOfUsedData;
    }

    public int getTotalVoiceMinutes() {
        return totalVoiceMinutes;
    }

    public void setTotalVoiceMinutes(int totalVoiceMinutes) {
        this.totalVoiceMinutes = totalVoiceMinutes;
    }

    public double getAppliedCharges() {
        return appliedCharges;
    }

    public void setAppliedCharges(double appliedCharges) {
        this.appliedCharges = appliedCharges;
    }
}
