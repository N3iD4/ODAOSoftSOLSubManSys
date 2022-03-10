package models;


public class Terminal {

    String name;
    String supportedRANTechnologies;

    public Terminal(){

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
}

