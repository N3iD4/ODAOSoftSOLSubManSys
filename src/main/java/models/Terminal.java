package models;


public class Terminal {

    private int id;
    private String name;
    private String supportedRANTechnologies;
    private boolean active;

    public Terminal() {

    }

    public Terminal(String name, String supportedRANTechnologies) {
        this.name = name;
        this.supportedRANTechnologies = supportedRANTechnologies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSupportedRANTechnologies() {
        return supportedRANTechnologies;
    }

    public void setSupportedRANTechnologies(String supportedRANTechnologies) {
        this.supportedRANTechnologies = supportedRANTechnologies;
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

