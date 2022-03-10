package DataHandling;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JsonSave {

    private static String filename="DataSafe";
    private static String filepath="src/main/java/DataHandling/Data";

   private static ObjectMapper mapper = new ObjectMapper(); // create once, reuse


    private JsonSave () {

    }

    private static void checkDir() {
        new File(filepath).mkdirs();
    }

    public static void saveDataSubscribers() {
        checkDir();

        try {
           mapper.writeValue(new File(getCompleteFilename()), SubscriberHandler.subscribers);
        } catch (Exception e) {
            System.out.println("Problem mit Schreiben/Serialisieren der Datei");
            System.out.println(e);
        }
    }

    public static void laodDataSubscriber() {
        checkDir();

        try {
            SubscriberHandler.subscribers = mapper.readValue(new File(getCompleteFilename()), new TypeReference<List<Subscriber>>(){});
        } catch (Exception e) {
            System.out.println("Problem mit Lesen/Deserialisieren der Datei");
            System.out.println(e);
        }
    }

    private static String getCompleteFilename() {
        return JsonSave.filepath + "/" + JsonSave.filename;
    }

    public static void main(String args[]) {
        System.out.println("Hello World");


    }
}
