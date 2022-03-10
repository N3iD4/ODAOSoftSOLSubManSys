package models;

public class Testing {

    public static void main (String[] args){
        Subscriber subscriber = new Subscriber();
        //subscriber.setForename("kgaz43o78"); // no
        //Grenzen
        subscriber.setForename("AZ");
        subscriber.setForename("az");
        //subscriber.setForename("@@");
        //subscriber.setForename("[[");
        //subscriber.setForename("``");
        //subscriber.setForename("{{");
        subscriber.setForename("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"); //yes
        //subscriber.setForename("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"); //no >30

        //subscriber.setTerminalType(new Terminal()); //yes
        //subscriber.setTerminalType(new Subscriber()); //no

        subscriber.setIMSI("1234567890"); //yes
        //subscriber.setIMSI("123456789"); //no
        //subscriber.setIMSI("12345678901"); //no
        //subscriber.setIMSI("123456789s"); //no
        //subscriber.setIMSI("123ahsjdhf"); //no

    }
}
