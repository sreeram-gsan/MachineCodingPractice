package model;

public class Booking {
    String userName;
    String centerName;
    String workoutName;
    Integer startTime;
    Integer endTime;

    public Booking(String userName, String centerName, String workoutName, Integer startTime, Integer endTime) {
        this.userName = userName;
        this.centerName = centerName;
        this.workoutName = workoutName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }
}
