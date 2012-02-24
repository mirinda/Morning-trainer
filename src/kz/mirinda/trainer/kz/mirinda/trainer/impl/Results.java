package kz.mirinda.trainer.kz.mirinda.trainer.impl;

import java.util.List;

/**
 * Results - model of data.
 * @author mirinda
 */
public class Results {
    private List<Workout> workouts;
    private String author;

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
