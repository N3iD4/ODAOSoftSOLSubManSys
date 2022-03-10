import java.util.ArrayList;

public class Subscriber {
    String forename;
    String surname;
    int ISMSI;
    Terminal terminalType;
    Subscription subscriptionType;
    ArrayList<String> charges;

    public Subscriber(){

    }

    public Subscriber(String forename, String surname, int ISMSI, Terminal terminalType, Subscription subscriptionType, ArrayList<String> charges) {
        this.forename = forename;
        this.surname = surname;
        this.ISMSI = ISMSI;
        this.terminalType = terminalType;
        this.subscriptionType = subscriptionType;
        this.charges = charges;
    }
}
