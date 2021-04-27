package com.example.tourismapp;

public class TourismVertical {
    private int id, image;
    private String name, location, evaluate;

    public TourismVertical(int id, int image, String name, String location, String evaluate) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.location = location;
        this.evaluate = evaluate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }
}
