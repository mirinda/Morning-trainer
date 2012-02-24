package kz.mirinda.trainer.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Results - model of data.
 * @author mirinda
 */
public class Results implements Serializable{
    private List<Workout> workouts= new ArrayList<Workout>();
    private String author;

    public  Results(String author){
        this.author = author;
    }

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
    public  void add(Workout workout){
        workouts.add(workout);
    }

    @Override
    public String toString(){
        String workoutString="";
        Iterator<Workout> iterator= workouts.iterator();
        for(;iterator.hasNext();){
            workoutString+="\t"+iterator.next().toString()+"\n";
        }
        return author+"\n"+workoutString;
    }
    public  static void main(String[] args){
        Workout workout = new Workout();
        workout.add("push_up",100);
        workout.add("pull_up",100);
        workout.add("press",200);
        Workout workout1 = new Workout();
        workout1.add("push_up",100);
        workout1.add("pull_up",100);
        workout1.add("press",200);

    }
}
