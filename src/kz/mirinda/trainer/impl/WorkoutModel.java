package kz.mirinda.trainer.impl;

import java.io.Serializable;
import java.util.*;

/**
 * new class
 *
 * @author mirinda
 */
public class WorkoutModel implements Serializable {
	private List<Workout> workouts = new ArrayList<Workout>();
	private List<DrillModel> drillModels = new ArrayList<DrillModel>();
	private String workoutName;


	public class DrillContainer implements Comparable<DrillContainer>{
		public long date;
		public int count;

		@Override
		public String toString() {
			return "Date:"+date+"; Count:"+count;
		}

		@Override
		public int compareTo(DrillContainer drillContainer) {
			return date>drillContainer.date?1:date==drillContainer.date?0:-1;
		}
	}
	public class AllInfDrillContainer extends DrillContainer{
		//public String workoutModelName;
		public String drillName;
	}
	public WorkoutModel(){}
	public WorkoutModel(String name){
		workoutName =name;
	}


	public List<Workout> getWorkouts() {
		return workouts;
	}
	public void setDrillModels(List<DrillModel> drillModels) {
		this.drillModels = drillModels;
	}
	public List<DrillModel> getDrillModels() {
		return drillModels;
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
	public void addDrillModel(DrillModel drillModel){
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
				"workouts=" + workouts +
				", drillModels=" + drillModels +
				", workoutName='" + workoutName + '\'' +
				'}';
	}

	public List<String> doDrillModelList(){
		if (getDrillModels() == null) {
			return new ArrayList<String>();
		}
		List<String> stringList =new ArrayList<String>(getDrillModels().size());
		for (DrillModel drillModel : getDrillModels()) {
			stringList.add(drillModel.getDrillName());
		}
		return stringList;
	}

	public List<DrillContainer> getInfOfDrill(int position) {
		List<DrillContainer> drillContainers = new LinkedList<DrillContainer>();
		DrillModel drillModel = drillModels.isEmpty()?null:drillModels.get(position);
		DrillContainer drillContainer = new DrillContainer();
		for(Workout workout:workouts){
			int t = workout.findDrill(drillModel);
			if(t == 0) continue;
		   	drillContainer.count = t;
			drillContainer.date = workout.getDate().getTime();
			drillContainers.add(drillContainer);
			drillContainer = new DrillContainer();
		}
		return drillContainers;
	}
	public List<Date> getAllDays(){
	 	List<Date> days = new LinkedList<Date>();

		for (Workout workout : workouts) {
			Date date = workout.getDate();
			boolean flag=true;
			for (Date day : days) {
				if(isDatesInDay(day,date)){
					flag=false;
					break;
				}
			}
			if(flag){
				days.add(date);
			}
		}
		return  days;
	}
	boolean isDatesInDay(Date date1,Date date2){
		//noinspection RedundantIfStatement
		if(date1.getYear()==date2.getYear()&&date1.getMonth()==date2.getMonth()&&date1.getDay()==date2.getDay()){
			return true;
		}
		return false;
	}
	public List<AllInfDrillContainer> getDayDrills(Date day){
		List<AllInfDrillContainer> drillContainers =new LinkedList<AllInfDrillContainer>();
		for (Workout workout : workouts) {
			if(isDatesInDay(day,workout.getDate())){
				for (Drill  drill: workout.getDrills()) {
					AllInfDrillContainer container = new AllInfDrillContainer();
					container.drillName = drill.getDrillName();
					container.date = workout.getDate().getTime();
					container.count= drill.getNumbers().get(0);
					drillContainers.add(container);
				}
			}
		}
		return drillContainers;
	}
}
