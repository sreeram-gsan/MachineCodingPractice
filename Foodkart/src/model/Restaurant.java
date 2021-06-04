package model;

import constant.Pincode;

import java.util.List;

public class Restaurant {
    String name;
    List<Pincode> servicableAreas;
    String foodName;
    Integer foodPrice;
    Integer availableQuantity;
    Integer rating;
    Integer numberOfRatings;

    public Restaurant(String name, List<Pincode> servicableAreas, String foodName, Integer foodPrice, Integer availableQuantity) {
        this.name = name;
        this.servicableAreas = servicableAreas;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.availableQuantity = availableQuantity;
        this.rating=0;
        this.numberOfRatings=0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pincode> getServicableAreas() {
        return servicableAreas;
    }

    public void setServicableAreas(List<Pincode> servicableAreas) {
        this.servicableAreas = servicableAreas;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Integer getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(Integer foodPrice) {
        this.foodPrice = foodPrice;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(Integer numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }
}
