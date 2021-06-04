package dao;


import constant.Pincode;
import exception.QuantityNotAvailableException;
import exception.RestaurantNotFoundException;
import model.Rating;
import model.Restaurant;
import java.util.ArrayList;
import java.util.List;

public class RestaurantDao {

    List<Restaurant> restaurantList = new ArrayList<>();
    private static RestaurantDao restaurantDao = null;
    List<Rating> ratingList = new ArrayList<>();

    public static RestaurantDao getInstance(){
        if (restaurantDao == null){
            return new RestaurantDao();
        }
        return restaurantDao;
    }

    public Restaurant createRestaurant(String name, String pincode, String foodname, Integer foodPrice, Integer quantity){
        String [] pincodes = pincode.split("/");
        List<Pincode> pincodeEnumList = new ArrayList<>();
        for (String s: pincodes){
            pincodeEnumList.add(Pincode.valueOf(s));
        }
        Restaurant restaurant = new Restaurant(name, pincodeEnumList, foodname, foodPrice,quantity);
        restaurantList.add(restaurant);
        System.out.println("New Restaurant: "+restaurant.getName()+" created!");
        return restaurant;
    }

    public void updateQuantity(String restaurantName, Integer newQuantity){
        for (Restaurant r: restaurantList){
            if (r.getName().equals(restaurantName)){
                r.setAvailableQuantity(newQuantity);
            }
        }
    }

    public void addRating(String restaurantName, Integer rating, String comments){
        if (comments==null){
            ratingList.add(new Rating(restaurantName, rating));
        }else{
            ratingList.add(new Rating(restaurantName, rating, comments));
        }
        for (Restaurant r: restaurantList){
            if (r.getName().equals(restaurantName)){
                Integer newRating = ((r.getRating()*r.getNumberOfRatings()) + rating)/(r.getNumberOfRatings()+1);
                r.setRating(newRating);
                r.setNumberOfRatings(r.getNumberOfRatings()+1);
            }
        }
    }

    public List<Restaurant> getServicableRestaurantsForPincode(Pincode pincode){
        List<Restaurant> servicableRestaurants = new ArrayList<>();
        for (Restaurant restaurant: restaurantList){
            if (restaurant.getServicableAreas().contains(pincode)){
                servicableRestaurants.add(restaurant);
            }
        }
        return servicableRestaurants;
}

    public void placeOrder(String restaurantName, Integer quantity) throws QuantityNotAvailableException, RestaurantNotFoundException {
        Boolean restaurantFound = Boolean.FALSE;
        for (Restaurant restaurant: restaurantList){
            if (restaurant.getName().equals(restaurantName)){
                restaurantFound = Boolean.TRUE;
                if (restaurant.getAvailableQuantity()>=quantity){
                    restaurant.setAvailableQuantity(restaurant.getAvailableQuantity()-quantity);
                    System.out.println("Order at " + restaurantName + " for quantity "+ quantity+ " successful!");
                }else{
                    throw new QuantityNotAvailableException();
                }
            }
        }
        if (restaurantFound.equals(Boolean.FALSE))
            throw new RestaurantNotFoundException();
    }

}
