package service;

import dao.Dao;
import exception.CenterNotAvailableException;
import exception.SlotNotAvailableException;
import exception.UserNotFoundException;
import exception.WorkoutNotAvailableException;
import model.Slot;
import model.Workout;


import java.util.Arrays;
import java.util.List;

public class CenterService {

    private static CenterService centerService=null;
    private Dao centerDao = Dao.getInstance();

    private CenterService() {
    }

    public static CenterService getInstance(){
        if (centerService == null){
            centerService = new CenterService();
        }
        return centerService;
    }

    public void addCenter(String name){
        centerDao.addCenter(name);
        System.out.println("Center: "+name+" added!");
    }

    public void addCenterTimings(String name, List<Slot> slots) throws CenterNotAvailableException {
        centerDao.addCenterTimings(name,slots);
    }

    public void addCenterActivities(String name, String activities) throws CenterNotAvailableException {
        List<String> allActivities = Arrays.asList(activities.split(","));
        centerDao.addCenterActivities(name,allActivities);
    }

    public void addWorkout(String centerName, String workoutName, Integer startTime, Integer endTime, Integer availableSeats) throws CenterNotAvailableException, WorkoutNotAvailableException, SlotNotAvailableException {
        centerDao.addWorkout(centerName,workoutName,startTime,endTime,availableSeats);
    }

}
