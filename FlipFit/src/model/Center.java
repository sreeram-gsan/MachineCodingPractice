package model;

import java.util.List;

public class Center {
    Integer id;
    String name;
    List<Slot> availableSlots;
    List<String> activities;
    List<Workout> availableWorkouts;

    public Center(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Slot> getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(List<Slot> availableSlots) {
        this.availableSlots = availableSlots;
    }

    public List<String> getActivities() {
        return activities;
    }

    public void setActivities(List<String> activities) {
        this.activities = activities;
    }

    public List<Workout> getAvailableWorkouts() {
        return availableWorkouts;
    }

    public void setAvailableWorkouts(List<Workout> availableWorkouts) {
        this.availableWorkouts = availableWorkouts;
    }
}
