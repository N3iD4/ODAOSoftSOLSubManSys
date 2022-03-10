package DataHandling;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JsonSave {

    String filename="DataSafe";
    String filepath="src/main/java/DataHandling";

   private static ObjectMapper mapper = new ObjectMapper(); // create once, reuse


    public JsonSave () {

    }

    public void saveData(Object obj) {

        try {
           mapper.writeValue(new File(getCompleteFilename()), obj);
        } catch (Exception e) {
            System.out.println("Problem mit Schreiben/Serialisieren der Datei");
            System.out.println(e);
        }
    }

    public void laodData(List<String> obj) {
        try {
            //bookContainer = mapper.readValue(new File(fileName), new TypeReference<List<Book>>() { } ); //bookContainer.getClass());
            obj = mapper.readValue(new File(getCompleteFilename()), List<String>. ); //bookContainer.getClass());
        } catch (Exception e) {
            System.out.println("Problem mit Lesen/Deserialisieren der Datei");
            System.out.println(e);
        }
    }

    private String getCompleteFilename() {
        return this.filepath + "/" + this.filename;
    }
}
