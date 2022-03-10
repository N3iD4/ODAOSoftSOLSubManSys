package DataHandling;

import models.Terminal;

import java.util.ArrayList;
import java.util.List;

public class TerminalHandler {

    public static List<Terminal> subscribers = new ArrayList<Terminal>();

    private TerminalHandler() {
    }

    public static void addTerminal(Terminal sub) throws IllegalArgumentException{
        try {
            getIndexWithIMSI(sub);
            throw new IllegalArgumentException("Terminal with IMSI=" + sub.getIMSI() + " is already stored");
        } catch (IllegalArgumentException e) {}
        if(subscribers.size()!=0)
            sub.setId(TerminalHandler.subscribers.get(subscribers.size()-1).getId()+1);
        else
            sub.setId(0);
        subscribers.add(sub);
    }

    public static void deleteSub (Terminal sub) throws IllegalArgumentException {
        int index = -1;
        try {
            index = getIndexWithIMSI(sub);
        } catch (IllegalArgumentException e) {
             throw e;
        }
        if(index!=-1)
            TerminalHandler.subscribers.remove(index);
    }

    // removes and adds Terminal related to IMSI
    public static void editSub(Terminal sub) {
        try {
            deleteSub(sub);
            addTerminal(sub);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    // gives Terminal Obj realted to IMSI
    public static Terminal getTerminalByIMSI(String IMSI) throws IllegalArgumentException{
        return TerminalHandler.subscribers.get(getIndexWithIMSI(IMSI));
    }

    //gives List of all stored Terminals
    public static List<Terminal> getTerminals() {
        return TerminalHandler.subscribers;
    }

    public static String ToString() {
        return "TerminalHandler{" +
                "subscribers=" + subscribers +
                '}';
    }

    //gives index of given sub related to IMSI, otherwise -1
    public static int getIndexWithIMSI(Terminal sub) throws IllegalArgumentException{
        try {
            return getIndexWithIMSI(sub.getIMSI());
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    public static int getIndexWithIMSI(String IMSI) throws IllegalArgumentException{
        Object[] tempar =  subscribers.toArray();
        for ( int i =0; i<tempar.length;i++) {
            if(((Terminal)tempar[i]).getIMSI().equals(IMSI))
                return i;
        }
        throw new IllegalArgumentException("User not Found related to IMSI=" + IMSI);
    }

    public static Terminal getTerminalById(int id) throws IllegalArgumentException {
      if(checkID(id))
          return subscribers.get(id);
      throw new IllegalArgumentException("Terminal don't found");
    }

    public static boolean checkID(int id) {
        Object[] tempar =  subscribers.toArray();
        for ( int i =0; i<tempar.length;i++) {
            if(((Terminal)tempar[i]).getId() == id)
                return true;
        }
        return false;
    }



    public static void main(String args[]) {

        //Terminal sub1 = new Terminal(0,"hans","mueller","4554554559","455","45","4554554559",new PearAphone4s(),new SubscriptionOld(), new ArrayList<ChargeDTO>());
        // Terminal sub2 = new Terminal("franz","krtoffel","HK104",new Terminal(),new SubscriptionOld(), new ArrayList<ChargeDTO>());
        // Terminal sub3 = new Terminal("franziska","karotte","HK105",new Terminal(),new SubscriptionOld(), new ArrayList<ChargeDTO>());

        TerminalHandler sh = new TerminalHandler();
        //sh.addTerminal(sub1);
        //sh.addTerminal(sub2);
        //sh.addTerminal(sub3);

        System.out.println(sh.toString());
    }

}
