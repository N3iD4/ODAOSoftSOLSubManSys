import DataHandling.JsonSave;

import java.util.ArrayList;
import java.util.List;

public class MainMenu {
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
}
