package dao;

import constant.Gender;
import constant.Pincode;
import exception.UserNotFoundException;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private User currentUser = null;
    List<User> userList = new ArrayList<>();
    private static UserDao userDao = null;
    public static UserDao getInstance(){
        if (userDao == null){
            return new UserDao();
        }
        return userDao;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void loginUser(String phoneNumber) throws UserNotFoundException {
        for (User user : userList) {
            if (user.getPhoneNumber().equals(phoneNumber)) {
                this.currentUser = user;
            }
        }
        if (this.currentUser==null || !this.currentUser.getPhoneNumber().equals(phoneNumber)) {
            throw new UserNotFoundException();
        }

    }
    public User createUser(String phoneNumber, String name, Gender gender, Pincode pincode){
        User user = new User(phoneNumber,name,gender,pincode);
        userList.add(user);
        System.out.println("New User: "+user.getName()+" created!");
        return user;
    }

}
