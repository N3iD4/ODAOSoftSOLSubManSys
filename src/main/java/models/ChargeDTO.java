package models;

public class ChargeDTO {
    private int totalVolumeOfUsedData;
    private int totalFreeVoiceMinutes;
    private int totalPaidVoiceMinutes;
    private double appliedCharges;

    public int getTotalVolumeOfUsedData() {
        return totalVolumeOfUsedData;
    }

    public int getTotalFreeVoiceMinutes() {
        return totalFreeVoiceMinutes;
    }

    public void setTotalFreeVoiceMinutes(int totalFreeVoiceMinutes) {
        this.totalFreeVoiceMinutes = totalFreeVoiceMinutes;
    }

    public int getTotalPaidVoiceMinutes() {
        return totalPaidVoiceMinutes;
    }

    public void setTotalPaidVoiceMinutes(int totalPaidVoiceMinutes) {
        this.totalPaidVoiceMinutes = totalPaidVoiceMinutes;
    }

    public void setTotalVolumeOfUsedData(int totalVolumeOfUsedData) {
        this.totalVolumeOfUsedData = totalVolumeOfUsedData;
    }


    public double getAppliedCharges() {
        return appliedCharges;
    }

    public void setAppliedCharges(double appliedCharges) {
        this.appliedCharges = appliedCharges;
    }

    public ChargeDTO(int totalVolumeOfUsedData, int totalFreeVoiceMinutes, int totalPaidVoiceMinutes, double appliedCharges) {
        setTotalVolumeOfUsedData(totalVolumeOfUsedData);
        setTotalFreeVoiceMinutes(totalFreeVoiceMinutes);
        setTotalPaidVoiceMinutes(totalPaidVoiceMinutes);
        setAppliedCharges(appliedCharges);
    }

}
