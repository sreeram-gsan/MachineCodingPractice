package dao;

import exception.CenterNotAvailableException;
import exception.SlotNotAvailableException;
import exception.WorkoutNotAvailableException;
import model.*;
import utils.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class Dao {

    private static Dao dao = null;
    private List<User> usersList = new ArrayList<>();
    private List<Center> centerList = new ArrayList<>();
    private List<Booking> bookingList = new ArrayList<>();

    private Dao() {
    }

    public static Dao getInstance(){
        if (dao == null){
            dao = new Dao();
        }
        return dao;
    }

    //User functions
    public void addUser(String name){
        usersList.add(new User(IdGenerator.getId(),name));
    }

    public Boolean isUserPresent(String name){
        for (User user: usersList){
            if (user.getName().equals(name)){
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
    //Center functions
    public void addCenter(String name){
        centerList.add(new Center(IdGenerator.getId(),name));
    }

    public void addCenterTimings(String name, List<Slot> timings) throws CenterNotAvailableException {
        Boolean set=Boolean.FALSE;
        for (Center center: centerList){
            if (center.getName().equals(name)){
                center.setAvailableSlots(timings);
                set=Boolean.TRUE;
            }
        }
        if (set.equals(Boolean.FALSE)){
            throw  new CenterNotAvailableException();
        }
    }

    public void addCenterActivities(String name, List<String> activities) throws CenterNotAvailableException {
        Boolean set=Boolean.FALSE;
        for (Center center: centerList){
            if (center.getName().equals(name)){
                center.setActivities(activities);
                set=Boolean.TRUE;
            }
        }
        if (set.equals(Boolean.FALSE)){
            throw  new CenterNotAvailableException();
        }
    }

    public void addWorkout(String centerName, String workoutName, Integer startTime, Integer endTime, Integer availableSeats) throws CenterNotAvailableException, WorkoutNotAvailableException, SlotNotAvailableException {
        Boolean set=Boolean.FALSE;
        Boolean isSlotAvailable = Boolean.FALSE;

        for (Center center: centerList){
            if (center.getName().equals(centerName)){
                //Validation
                if (center.getActivities() == null || !center.getActivities().contains(workoutName)){
                    throw new WorkoutNotAvailableException();
                }
                for (Slot slot : center.getAvailableSlots()){
                    if (startTime >= slot.getStartTime() && endTime<= slot.getEndTime()){
                        isSlotAvailable=Boolean.TRUE;
                    }
                }
                if (isSlotAvailable.equals(Boolean.FALSE)){
                    throw new SlotNotAvailableException();
                }
                //Add workout
                if (center.getAvailableWorkouts()==null){
                    center.setAvailableWorkouts(new ArrayList<>());
                }
                center.getAvailableWorkouts().add(new Workout(workoutName,startTime,endTime,availableSeats,center.getName()));
                System.out.println("Workout: "+workoutName+" added for center: "+centerName);
                System.out.println(center.getAvailableWorkouts().toArray().toString());
                set=Boolean.TRUE;
            }
        }
        if (set.equals(Boolean.FALSE)){
            throw  new CenterNotAvailableException();
        }
    }

    public List<Workout> viewAvailableWorkouts(String workoutName){
        List<Workout> availableWorkouts = new ArrayList<>();
        for (Center center: centerList){
            if (center.getAvailableWorkouts()!=null){
                for (Workout workout: center.getAvailableWorkouts()){

                    if (workout.getName().equals(workoutName)){
                        availableWorkouts.add(workout);
                    }
                }
            }

        }
        return availableWorkouts;
    }

    public void bookASession(String userName,String centerName,String workoutName, Integer startTime, Integer endTime) throws WorkoutNotAvailableException, SlotNotAvailableException {
        Boolean isSlotAvailable = Boolean.FALSE;
        Boolean isWorkoutAvailable = Boolean.FALSE;

        for (Center center: centerList){
            if (center.getName().equals(centerName)){
                //Validation
                if (center.getActivities() == null || !center.getActivities().contains(workoutName)){
                    throw new WorkoutNotAvailableException();
                }
                for (Slot slot : center.getAvailableSlots()){
                    if (startTime >= slot.getStartTime() && endTime<= slot.getEndTime()){
                        isSlotAvailable=Boolean.TRUE;
                    }
                }


                for (Workout workout: center.getAvailableWorkouts()){
                    if (workout.getName().equals(workoutName)){
                        workout.setAvailableSeats(workout.getAvailableSeats()-1);
                        isWorkoutAvailable = Boolean.TRUE;
                    }
                }
            }
        }
        if (isSlotAvailable.equals(Boolean.FALSE)){
            throw new SlotNotAvailableException();
        }
        if (isWorkoutAvailable == Boolean.TRUE){
            bookingList.add(new Booking(userName,centerName,workoutName,startTime,endTime));
        }
        System.out.println("Session Booked");
    }

    public Workout returnEarliestWorkout(String workoutName){
        Boolean isEarliestWorkoutAvailable = Boolean.FALSE;
        Workout earliestWorkout = null;
        Integer startTime=24;
        Integer endTime=24;

        for (Center center: centerList){
            if (center.getAvailableWorkouts()!=null)
            {
                for (Workout workout: center.getAvailableWorkouts()){
                    if (workout.getName().equals(workoutName)){
                        if (workout.getAvailableSeats()>0 && workout.getStartTime()<startTime && workout.getEndTime()<endTime){
                            earliestWorkout = workout;
                            isEarliestWorkoutAvailable = Boolean.TRUE;
                        }
                    }
                }
            }


        }
        return (isEarliestWorkoutAvailable.equals(Boolean.TRUE)) ? earliestWorkout : null;
    }
}
