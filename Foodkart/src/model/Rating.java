package model;

public class Rating {
    String restaurantName;
    Integer rating;
    String comment;

    public Rating(String restaurantName, Integer rating, String comment) {
        this.restaurantName = restaurantName;
        this.rating = rating;
        this.comment = comment;
    }

    public Rating(String restaurantName, Integer rating) {
        this.restaurantName = restaurantName;
        this.rating = rating;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
