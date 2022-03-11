package controller;

import DataHandling.JsonSave;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        // 1. load data
        JsonSave.laodDataSubscriber();
        JsonSave.laodDataSubscriptions();
        JsonSave.laodDataTerminals();

        // 2. start menu
        UserInteraction.menu_main();

        // 3. save data to be sure
        JsonSave.saveDataSubscribers();
        JsonSave.saveDataSubscriptions();
        JsonSave.saveDataTerminals();

    }

}
