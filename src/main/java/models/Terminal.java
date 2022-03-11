package models;


public class Terminal {

    private int id;
    private String name;
    //private String supportedRANTechnologies;
    private boolean supports4G;
    private boolean isActive;

    public Terminal() {

    }

    public Terminal(int id, String name, boolean supports4G, boolean isActive) {
        this.id = id;
        this.name = name;
        this.supports4G = supports4G;
        this.isActive = isActive;
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


    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        this.isActive = active;
    }


    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name +
                " | 4G support: " + supports4G +
                " | is active: " + isActive;
    }
}

