package DataHandling;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JsonSave {

    public static void main(String args[]) {
        System.out.println("Hello World");

        //Subscriber sub1 = new Subscriber(0,"hans","mueller","4554554559","455","45","4554554559",new Terminal(),new SubscriptionOld(), new ArrayList<ChargeDTO>());
        //SubscriberHandler.addSubscriber(sub1);

        JsonSave js = new JsonSave();
        js.saveDataSubscribers();
        js.laodDataSubscriber();
        System.out.println(SubscriberHandler.ToString());
    }

    private String filename="DataSafe";
    private String filepath="src/main/java/DataHandling/Data";

   private static ObjectMapper mapper = new ObjectMapper(); // create once, reuse


    public JsonSave () {
        new File(filepath).mkdirs();

    }

    public void saveDataSubscribers() {

        try {
           mapper.writeValue(new File(getCompleteFilename()), SubscriberHandler.subscribers);
        } catch (Exception e) {
            System.out.println("Problem mit Schreiben/Serialisieren der Datei");
            System.out.println(e);
        }
    }

    public void laodDataSubscriber() {
        try {

            //bookContainer = mapper.readValue(new File(fileName), new TypeReference<List<Book>>() { } ); //bookContainer.getClass());
            SubscriberHandler.subscribers = mapper.readValue(new File(getCompleteFilename()), new TypeReference<List<Subscriber>>(){}); //bookContainer.getClass());
        } catch (Exception e) {
            System.out.println("Problem mit Lesen/Deserialisieren der Datei");
            System.out.println(e);
        }
    }

    private String getCompleteFilename() {
        return this.filepath + "/" + this.filename;
    }
}
