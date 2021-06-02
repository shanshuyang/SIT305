package com.example.foodrescueapp.model;

public class Food {
    private String foodTitle, foodQuantity, foodLocation, foodDescription, foodDate, foodTime;
    private int foodId;
    private byte[] foodImage;

    public Food(byte[] foodImage, String foodTitle, String foodDescription, String foodDate, String foodTime,String foodQuantity, String foodLocation) {
        this.foodTitle = foodTitle;
        this.foodQuantity = foodQuantity;
        this.foodLocation = foodLocation;
        this.foodDescription = foodDescription;
        this.foodDate = foodDate;
        this.foodTime = foodTime;
        this.foodImage = foodImage;
    }

    public Food(){

    }

    public Food(String foodTitle){
        this.foodTitle = foodTitle;
    }

    public String getFoodTitle() {
        return foodTitle;
    }

    public void setFoodTitle(String foodTitle) {
        this.foodTitle = foodTitle;
    }

    public String getFoodQuantity() {
        return foodQuantity;
    }

    public void setFoodQuantity(String foodQuantity) {
        this.foodQuantity = foodQuantity;
    }

    public String getFoodLocation() {
        return foodLocation;
    }

    public void setFoodLocation(String foodLocation) {
        this.foodLocation = foodLocation;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    public String getFoodDate() {
        return foodDate;
    }

    public void setFoodDate(String foodDate) {
        this.foodDate = foodDate;
    }

    public String getFoodTime() {
        return foodTime;
    }

    public void setFoodTime(String foodTime) {
        this.foodTime = foodTime;
    }

    public byte[] getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(byte[] foodImage) {
        this.foodImage = foodImage;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }
}
