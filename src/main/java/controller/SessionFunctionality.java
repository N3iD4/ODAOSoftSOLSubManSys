package controller;

import models.*;
import view.CommandLineInterface;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class SessionFunctionality {



    private static String[] serviceNames = new String[] { "Voice Call", "Browsing and social networking", "App download", "Adaptive HD video" };
    private static String[] serviceTypes = new String[] { "voice", "data", "data", "data" };
    private static int[] serviceRateInMbPerS = new int[] { 0, 2, 10, 100 };


    protected static void process_createSession() {
        // Needed: user, service, time

        // Ask for userId
        int userId = CommandLineInterface.askAndGetInt("Which user should open a session?");
        // Check if valid userId
        boolean isValidId = true; // SubscriberHandler.  = Persistence.UserManagement.isValidId(userId);
        if (!isValidId) {
            CommandLineInterface.waitForUserToContinue("The id you entered didn't match a user account. No session has been created and you will be brought back to the main menu.");
            return;
        }
        Subscriber subscriber = new Subscriber( 0, "Vorname", "Nachname", "0", "0", "0","0", 0, 0, new ArrayList<>()); // = DataHandling.getUser();


        // Ask for service type
        int serviceTypeIndex = CommandLineInterface.letUserChooseMenuItem("Which service should be started?", serviceNames);

        // Ask for time
        int timeInS = CommandLineInterface.askAndGetInt("How long should the session be?");

        if ( serviceTypes[serviceTypeIndex].equals("voice") ) {
            doVoiceSession(subscriber, serviceTypeIndex, timeInS);
        } else {
            doDataSession(subscriber, serviceTypeIndex, timeInS);
        }

    }



    protected static void doVoiceSession(Subscriber subscriber, int serviceTypeIndex, int timeInS) {
        BigDecimal costs = BigDecimal.valueOf(0);

        // Calculate nr of free minutes after call and costs for minutes bigger than the free minutes
        int freeMinutesBeforeCall = subscriber.getFreeMinutesLeft();
        int freeMinutesAfterCall = freeMinutesBeforeCall - (int) Math.ceil(timeInS / 60);
        int usedPaidMinutes = 0;
        if (freeMinutesAfterCall < 0) {
            costs = costs.add(  BigDecimal.valueOf(  -freeMinutesAfterCall  ).multiply(  subscriber.getSubscription().getPricePerExtraMinute()  )   );
            usedPaidMinutes = -freeMinutesAfterCall;
            freeMinutesAfterCall = 0;
        }
        int usedFreeMinutes = freeMinutesBeforeCall - freeMinutesAfterCall;

        // Create and add charge to subcsriber
        ChargeDTO newCharge = new ChargeDTO(0, usedFreeMinutes, usedPaidMinutes, costs);
        subscriber.addCharge(newCharge);

        subscriber.setFreeMinutesLeft(freeMinutesAfterCall);

        System.out.println( "The following charge was created" );
        CommandLineInterface.waitForUserToContinue( newCharge.toString() );

    }


    private static String[] throughPutNames = { "good", "medium", "low", "n/a" };
    private static BigDecimal[] throughPutPercent = { BigDecimal.valueOf(0.5), BigDecimal.valueOf(0.25), BigDecimal.valueOf(0.1), BigDecimal.valueOf(0) };
    protected static void doDataSession(Subscriber subscriber, int serviceTypeIndex, int timeInS) {
        // calculate random signal strength
        int signalStrengthIndex = (int) Math.floor(Math.random() * 4);

        // Calculate usedDataRate (achievable or required by service, whatever is lower)
        BigDecimal maxThroughPut = subscriber.getMaxThroughput();
        BigDecimal achievableDataRate = maxThroughPut.multiply( throughPutPercent[signalStrengthIndex] );
        BigDecimal usedDataRateInMbitPerS = maxThroughPut.min(achievableDataRate);

        // Calculate required volume
        int requiredVolume = floor(usedDataRateInMbitPerS.multiply( BigDecimal.valueOf(timeInS) ));

        //
        int oldUserVolume = subscriber.getDataVolumeLeft();
        int newUserVolume = (int) Math.floor(oldUserVolume - requiredVolume);

        if ( newUserVolume >= 0 ) {
            subscriber.setDataVolumeLeft( newUserVolume );
            // addcharge
            ChargeDTO newCharge = new ChargeDTO(newUserVolume - oldUserVolume,0, 0, BigDecimal.valueOf(0) );
            subscriber.addCharge( newCharge );
            // TODO: save?
            CommandLineInterface.waitForUserToContinue( String.format("The user used %s for %d seconds with %d network quality. This was still covered by his data volume and lead to %dMb used.\nThe user's data volume changed from %dMb to %dMb.", serviceNames[serviceTypeIndex], timeInS, throughPutNames[signalStrengthIndex], requiredVolume, oldUserVolume, newUserVolume));
        } else {
            subscriber.setDataVolumeLeft( 0 );
            // addcharge
            ChargeDTO newCharge = new ChargeDTO( oldUserVolume,0, 0, BigDecimal.valueOf(0) ); // nur für restliche Daten, also für oldUserVolume, berechnen !
            subscriber.addCharge( newCharge );
            // TODO: save?
            CommandLineInterface.waitForUserToContinue( String.format("The user wanted to use %s for %d seconds with %d network quality. This was not covered by their data volume. Instead, %dMb were used.\nThe user's data volume changed from %dMb to %dMb.", serviceNames[serviceTypeIndex], timeInS, throughPutNames[signalStrengthIndex], oldUserVolume, oldUserVolume, 0));
        }

    }

    public static int floor(BigDecimal x) {
        return x.setScale(0, RoundingMode.FLOOR).unscaledValue().intValue();
    }


    // Subscriber braucht getmaxthroughput, weil er phone UND subscription direkt kennt


    // check, wenn nciht



}




















































