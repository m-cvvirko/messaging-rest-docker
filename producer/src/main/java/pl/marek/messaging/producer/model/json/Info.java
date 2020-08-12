package pl.marek.messaging.producer.model.json;

import org.json.simple.JSONObject;

public class Info {
    String name;
    String surname;
    String country;

    private Info() { }

    public Info(String name, String surname, String country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }

    public static Info build(JSONObject jinfo) {
        String name = (String) jinfo.get("name");
        String surname = (String) jinfo.get("surname");
        String country = (jinfo.get("country") != null ) ? (String)jinfo.get("country") : "";
        return new Info(name, surname, country);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
