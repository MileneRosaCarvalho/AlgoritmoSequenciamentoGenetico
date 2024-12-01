package br.anhembi.geneticsequencing.model.dto;

public class Response {
    private double value;
    private String name;
    private String description;
    private String link;

    public Response(double value) {
        this.value = value;
    }

    public Response (double value, String name, String description, String link){
        this.value = value;
        this.name = name;
        this.description = description;
        this.link = link;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
