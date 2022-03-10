package models;

import java.math.BigDecimal;

public class GreenMobilL extends SubscriptionOld {



    public GreenMobilL (){
        super.fee = BigDecimal.valueOf(4.0);
        super.minutesIncluded = 3;
        super.pricePerExtraMinute = BigDecimal.valueOf(3);
        super.dataVolumeInMB = 3;
    }

}
