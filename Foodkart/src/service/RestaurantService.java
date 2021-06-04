package service;

import constant.Pincode;
import dao.RestaurantDao;
import exception.QuantityNotAvailableException;
import exception.RestaurantNotFoundException;
import model.Restaurant;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RestaurantService {
    class SortByPrice implements Comparator<Restaurant>
    {
        public int compare(Restaurant a, Restaurant b)
        {
            return b.getFoodPrice()-a.getFoodPrice();
        }
    }

    class SortByRating implements Comparator<Restaurant>
    {
        public int compare(Restaurant a, Restaurant b)
        {
            return b.getRating()-a.getRating();
        }
    }
    private static RestaurantService restaurantService = null;
    private RestaurantDao restaurantDao = RestaurantDao.getInstance();
    public static  RestaurantService getInstance(){
        if(restaurantService==null){
            return new RestaurantService();
        }
        return restaurantService;
    }

    public Restaurant registerRestaurant(String name, String pincode, String foodName, Integer foodPrice, Integer quantity){
        return restaurantDao.createRestaurant(name,pincode,foodName,foodPrice,quantity);
    }

    public void updateQuantity (String restaurantName, Integer newQuentity){
        restaurantDao.updateQuantity(restaurantName,newQuentity);
    }

    public List<Restaurant> getServicableRestaurantsForPincode(Pincode pincode, String filter){
        List<Restaurant> servicableRestaurants = restaurantDao.getServicableRestaurantsForPincode(pincode);
        switch (filter){
            case "rating":
                Collections.sort(servicableRestaurants,new SortByRating());
                break;
            case "price":
                Collections.sort(servicableRestaurants, new SortByPrice());
                break;
        }
        return servicableRestaurants;
    }

    public void addRating(String restaurantName, Integer rating, String comments) {
        restaurantDao.addRating(restaurantName,rating,comments);
    }

    public void placeOrder (String restaurantName, Integer quantity) throws QuantityNotAvailableException, RestaurantNotFoundException {
        restaurantDao.placeOrder(restaurantName,quantity);
    }
}
