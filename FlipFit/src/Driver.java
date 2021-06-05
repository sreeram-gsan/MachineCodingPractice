import exception.CenterNotAvailableException;
import exception.SlotNotAvailableException;
import exception.UserNotFoundException;
import exception.WorkoutNotAvailableException;
import model.Slot;
import model.Workout;
import service.CenterService;
import service.UserService;

import java.util.ArrayList;
import java.util.List;

public class Driver {
    public static void main (String args[]){
        UserService userService = UserService.getInstance();
        CenterService centerService = CenterService.getInstance();

        //Adding center
        centerService.addCenter("Koramangala");
        centerService.addCenter("Bellandur");

        //Adding Center timings
        List<Slot> slots = new ArrayList<>();
        slots.add(new Slot(6,9));
        slots.add(new Slot(18,21));
        try{
            centerService.addCenterTimings("Koramangala",slots);
        }catch (CenterNotAvailableException E){
            System.out.println("Center not found!");
        }

        //Add activities
        try{
            centerService.addCenterActivities("Koramangala","Weights,Cardio");
        }catch (CenterNotAvailableException E){
            System.out.println("Center not found!");
        }

        //add workouts
        try{
            centerService.addWorkout("Koramangala","Weights",6,7,100);
//            centerService.addWorkout("Koramangala","Weights",7,8,100);
        }catch (CenterNotAvailableException E){
            System.out.println("Center not found!");
        } catch (WorkoutNotAvailableException e) {
            System.out.println("Requested Workout is not available for this center");
        } catch (SlotNotAvailableException e) {
            System.out.println("Requested slot is not available for this center");
        }

        //add user
        userService.addUser("User1");

        //get available workouts
        List<Workout> workouts = userService.viewAvailableWorkouts("Weights");
        for (Workout workout: workouts){
            System.out.println(workout.getCenterName()+" "+workout.getName()+" "+workout.getStartTime()+" "+workout.getEndTime()+" "+workout.getAvailableSeats());
        }

        try{
            userService.bookASession("User1","Koramangala","Weights",7,8);
        } catch (WorkoutNotAvailableException e) {
            System.out.println("Requested Workout is not available");
        } catch (SlotNotAvailableException e) {
            System.out.println("Requested slot is not available");
        } catch (UserNotFoundException e) {
            System.out.println("User not found!");
        }

        //get Earliest available workout
        Workout workout = userService.returnEarliestSlot("Weights");
        if (workout==null){
            System.out.println("Not available");
        }else{
            System.out.println(workout.getCenterName()+" "+workout.getName()+" "+workout.getStartTime()+" "+workout.getEndTime()+" "+workout.getAvailableSeats());
        }

    }
    }
