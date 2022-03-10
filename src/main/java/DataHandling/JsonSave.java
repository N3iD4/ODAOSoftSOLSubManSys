package DataHandling;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JsonSave {

    private static String filepath="src/main/java/DataHandling/Data";

   private static ObjectMapper mapper = new ObjectMapper(); // create once, reuse

    private static String subscribersFile = "subscribers";
    private static String subscriptionsFile = "subscriptions";
    private static String terminalsFile = "terminals";


    private JsonSave () {

    }

    private static void checkDir() {
        new File(filepath).mkdirs();
    }

    //Subscribers

    public static void saveDataSubscribers() {
        checkDir();

        try {
            mapper.writeValue(new File(getCompleteFilename(subscribersFile)), SubscriberHandler.subscribers);
        } catch (Exception e) {
            System.out.println("Problem mit Schreiben/Serialisieren der Datei Subscribers");
            System.out.println(e);
        }
    }

    public static void laodDataSubscriber() {
        checkDir();

        try {
            SubscriberHandler.subscribers = mapper.readValue(new File(getCompleteFilename(subscribersFile)), new TypeReference<List<Subscriber>>(){});
        } catch (Exception e) {
            System.out.println("Problem mit Lesen/Deserialisieren der Datei Subscribers");
            System.out.println(e);
        }
    }

    //Subscriptions

    public static void saveDataSubscriptions() {
        checkDir();

        try {
            mapper.writeValue(new File(getCompleteFilename(subscriptionsFile)), SubscriptionHandler.subscriptions);
        } catch (Exception e) {
            System.out.println("Problem mit Schreiben/Serialisieren der Datei Subscriptions");
            System.out.println(e);
        }
    }

    public static void laodDataSubscriptions() {
        checkDir();

        try {
            SubscriptionHandler.subscriptions = mapper.readValue(new File(getCompleteFilename(subscriptionsFile)), new TypeReference<List<Subscription>>(){});
        } catch (Exception e) {
            System.out.println("Problem mit Lesen/Deserialisieren der Datei Subscriptions");
            System.out.println(e);
        }
    }

    //Terminals

    public static void saveDataTerminals() {
        checkDir();

        try {
            mapper.writeValue(new File(getCompleteFilename(terminalsFile)), TerminalHandler.terminals);
        } catch (Exception e) {
            System.out.println("Problem mit Schreiben/Serialisieren der Datei Terminals");
            System.out.println(e);
        }
    }

    public static void laodDataTerminals() {
        checkDir();

        try {
            TerminalHandler.terminals = mapper.readValue(new File(getCompleteFilename(terminalsFile)), new TypeReference<List<Terminal>>(){});
        } catch (Exception e) {
            System.out.println("Problem mit Lesen/Deserialisieren der Datei Terminals");
            System.out.println(e);
        }
    }

    private static String getCompleteFilename(String filename) {
        return JsonSave.filepath + "/" + filename;
    }

    public static void main(String args[]) {
        System.out.println("Hello World");


    }
}
