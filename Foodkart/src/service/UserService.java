package service;

import constant.Gender;
import constant.Pincode;
import dao.UserDao;
import exception.UserNotFoundException;
import model.Restaurant;
import model.User;

import java.util.List;

public class UserService {

    private static UserService userService = null;
    public static UserService getInstance(){
        if (userService == null){
            return new UserService();
        }
        return userService;
    }

    private UserDao userDao = UserDao.getInstance();

    public User registerUser(String phoneNumber, String name, Gender gender, Pincode pincode){
        return userDao.createUser(phoneNumber,name,gender,pincode);
    }

    public void login (String phoneNumber) throws UserNotFoundException {
        userDao.loginUser(phoneNumber);
    }

    public List<Restaurant>  showRestaurents (RestaurantService restaurantService, String filter){
        return restaurantService.getServicableRestaurantsForPincode(userDao.getCurrentUser().getPincode(),filter);
    }
}
