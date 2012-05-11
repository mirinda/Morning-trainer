package kz.mirinda.trainer.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * new class
 *
 * @author mirinda
 */
public class WorkoutModel implements Serializable {
	List<Workout> workouts = new ArrayList<Workout>();

	public List<DrillModel> getDrillModels() {
		return drillModels;
	}

	public void setDrillModels(List<DrillModel> drillModels) {
		this.drillModels = drillModels;
	}

	List<DrillModel> drillModels = new ArrayList<DrillModel>();
	private String workoutName;

	public WorkoutModel(){}
	public WorkoutModel(String name){
		workoutName =name;
	}

	public List<Workout> getWorkouts() {
		return workouts;
	}
	public void setWorkouts(List<Workout> workouts) {
		this.workouts = workouts;
	}
	public String getWorkoutName() {
		return workoutName;
	}
	public void setWorkoutName(String workoutName) {
		this.workoutName = workoutName;
	}
	public void addDrillModel(String name){
		DrillModel drillModel =  new DrillModel();
		drillModel.setDrillName(name);
		drillModels.add(drillModel);
	}

	public Workout getLastWorkout(){
		Iterator<Workout> iterator = workouts.iterator();
		Workout lastWorkout = null;
		Workout workout=null;
		for(;iterator.hasNext();){
			if (lastWorkout== null){
				workout=lastWorkout=iterator.next();
			}else{
				workout=iterator.next();
			}
			if(workout.getDate().after(lastWorkout.getDate())){lastWorkout=workout;}
		}
		return workout;
	}
	public  void add(Workout workout){
		workouts.add(workout);
	}

	public static void main(String[] args){
		WorkoutModel workoutModel = new WorkoutModel();
		workoutModel.setWorkoutName("my_bar_workout");
		workoutModel.addDrillModel("pull_up");
		workoutModel.addDrillModel("press");
		workoutModel.addDrillModel("squat");
		WorkoutModel workoutModel1 = new WorkoutModel();
		workoutModel1.setWorkoutName("parallel_bar_workout");
		workoutModel1.addDrillModel("push_up");
		workoutModel1.addDrillModel("parallel_bar");
		workoutModel1.addDrillModel("squat");

	}
	
	@Override
	public String toString() {
		return "WorkoutModel{" +
				"workoutName='" + workoutName + '\'' +
				'}';
	}
}
