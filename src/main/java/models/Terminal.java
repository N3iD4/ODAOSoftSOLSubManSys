package models;


public class Terminal {

    private int id;
    private String name;
    //private String supportedRANTechnologies;
    private boolean supports4G;
    private boolean active;

    public Terminal() {

    }

    public Terminal(String name, boolean supports4G) {
        this.name = name;
        this.supports4G = supports4G;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getSupports4G() {
        return supports4G;
    }

    public void setSupports4G(boolean supports4G) {
        this.supports4G = supports4G;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


}

