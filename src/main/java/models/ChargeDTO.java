package models;

import java.math.BigDecimal;

public class ChargeDTO {
    private int totalVolumeOfUsedData;
    private int totalFreeVoiceMinutes;
    private int totalPaidVoiceMinutes;
    private BigDecimal appliedCharges;

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


    public BigDecimal getAppliedCharges() {
        return appliedCharges;
    }

    public void setAppliedCharges(BigDecimal appliedCharges) {
        this.appliedCharges = appliedCharges;
    }

    public ChargeDTO(int totalVolumeOfUsedData, int totalFreeVoiceMinutes, int totalPaidVoiceMinutes, BigDecimal appliedCharges) {
        setTotalVolumeOfUsedData(totalVolumeOfUsedData);
        setTotalFreeVoiceMinutes(totalFreeVoiceMinutes);
        setTotalPaidVoiceMinutes(totalPaidVoiceMinutes);
        setAppliedCharges(appliedCharges);
    }

    @Override
    public String toString() {
        return "ChargeDTO{" +
                "totalVolumeOfUsedData=" + totalVolumeOfUsedData +
                ", totalFreeVoiceMinutes=" + totalFreeVoiceMinutes +
                ", totalPaidVoiceMinutes=" + totalPaidVoiceMinutes +
                ", appliedCharges=" + appliedCharges +
                '}';
    }
}
