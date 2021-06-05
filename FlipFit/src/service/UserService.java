package service;


import dao.Dao;
import exception.SlotNotAvailableException;
import exception.UserNotFoundException;
import exception.WorkoutNotAvailableException;
import model.Workout;

import java.util.List;


public class UserService {

    private static UserService userService=null;
    private Dao userDao = Dao.getInstance();

    private UserService() {
    }

    public static UserService getInstance(){
        if (userService == null){
            userService= new UserService();
        }
        return userService;
    }

    public void addUser(String name){
        userDao.addUser(name);
        System.out.println("User: "+name+" added!");
    }

    public List<Workout> viewAvailableWorkouts(String workoutName){
        return  userDao.viewAvailableWorkouts(workoutName);
    }

    public void bookASession(String userName,String centerName,String workoutName, Integer startTime, Integer endTime) throws SlotNotAvailableException, WorkoutNotAvailableException, UserNotFoundException {
        if (userDao.isUserPresent(userName).equals(Boolean.FALSE)){
            throw new UserNotFoundException();
        }
        userDao.bookASession(userName,centerName,workoutName,startTime,endTime);
    }

    public Workout returnEarliestSlot(String workoutName){
        return userDao.returnEarliestWorkout(workoutName);
    }
}
