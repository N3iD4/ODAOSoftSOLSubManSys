package DataHandling;


import java.util.ArrayList;
import java.util.List;

public class JsonSave {

    public static void main(String args[]) {
        System.out.println("Hello World");
        List<String> ar = new ArrayList<>();
        for (int i =0;i<99;i++) {
            ar.add("Hallo" + i*i);
        }
        JsonSave js = new JsonSave();
        js.saveData(ar);
        List<String> ar2 = new ArrayList<>();
        js.laodData(ar2);
        System.out.println(ar2.size());
    }

    String filename="DataSafe";
    String filepath="src/main/java/DataHandling/Data";

   //private static ObjectMapper mapper = new ObjectMapper(); // create once, reuse


    public JsonSave () {

    }

    public void saveData(Object obj) {

        try {
           //mapper.writeValue(new File(getCompleteFilename()), obj);
        } catch (Exception e) {
            System.out.println("Problem mit Schreiben/Serialisieren der Datei");
            System.out.println(e);
        }
    }

    public void laodData(List<String> obj) {
        try {
            //bookContainer = mapper.readValue(new File(fileName), new TypeReference<List<Book>>() { } ); //bookContainer.getClass());
            //obj = mapper.readValue(new File(getCompleteFilename()), List<String>. ); //bookContainer.getClass());
        } catch (Exception e) {
            System.out.println("Problem mit Lesen/Deserialisieren der Datei");
            System.out.println(e);
        }
    }

    private String getCompleteFilename() {
        return this.filepath + "/" + this.filename;
    }
}
