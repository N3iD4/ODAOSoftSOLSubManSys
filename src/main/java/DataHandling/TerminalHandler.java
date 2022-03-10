package DataHandling;

import models.Terminal;

import java.util.ArrayList;
import java.util.List;

public class TerminalHandler {

    public static List<Terminal> terminals = new ArrayList<Terminal>();

    private TerminalHandler() {
    }

    public static void addTerminal(Terminal terminal) throws IllegalArgumentException{
        try {
            getIndexWithID(terminal);
            throw new IllegalArgumentException("Terminal with IMSI=" + terminal.getId() + " is already stored");
        } catch (IllegalArgumentException e) {}
        if(terminals.size()!=0)
            terminal.setId(TerminalHandler.terminals.get(terminals.size()-1).getId()+1);
        else
            terminal.setId(0);
        terminals.add(terminal);
    }

    public static void deleteSub (Terminal terminal) throws IllegalArgumentException {
        int index = -1;
        try {
            index = getIndexWithID(terminal);
        } catch (IllegalArgumentException e) {
             throw e;
        }
        if(index!=-1)
            TerminalHandler.terminals.remove(index);
    }

    // removes and adds Terminal related to IMSI
    public static void editSub(Terminal terminal) {
        try {
            deleteSub(terminal);
            addTerminal(terminal);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    // gives Terminal Obj realted to IMSI
    public static Terminal getTerminalByIMSI(int id) throws IllegalArgumentException{
        return TerminalHandler.terminals.get(getIndexWithID(id));
    }

    //gives List of all stored Terminals
    public static List<Terminal> getTerminals() {
        return TerminalHandler.terminals;
    }

    public static String ToString() {
        return "TerminalHandler{" +
                "terminals=" + terminals +
                '}';
    }

    //gives index of given terminal related to ID, otherwise -1
    public static int getIndexWithID(Terminal terminal) throws IllegalArgumentException{
        try {
            return getIndexWithID(terminal.getId());
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    public static int getIndexWithID(int id) throws IllegalArgumentException{
        Object[] tempar =  terminals.toArray();
        for ( int i =0; i<tempar.length;i++) {
            if(((Terminal)tempar[i]).getId() == id)
                return i;
        }
        throw new IllegalArgumentException("User not Found related to ID=" + id);
    }

    public static Terminal getTerminalById(int id) throws IllegalArgumentException {
      if(checkID(id))
          return terminals.get(id);
      throw new IllegalArgumentException("Terminal don't found");
    }

    public static boolean checkID(int id) {
        Object[] tempar =  terminals.toArray();
        for ( int i =0; i<tempar.length;i++) {
            if(((Terminal)tempar[i]).getId() == id)
                return true;
        }
        return false;
    }

}
