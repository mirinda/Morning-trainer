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
	private List<WorkoutModel> workoutModels = new ArrayList<WorkoutModel>();
	private WorkoutModel oneTimeWorkoutModel= new WorkoutModel();
	private String author;

	public Results(){
		this.author="";
	}
	public  Results(String author){
		this.author = author;
	}

	public WorkoutModel getOneTimeWorkoutModel() {
		return oneTimeWorkoutModel;
	}
	public void setOneTimeWorkoutModel(WorkoutModel workoutModel) {
		this.oneTimeWorkoutModel = workoutModel;
	}
	public List<WorkoutModel> getWorkoutModels() {
		return workoutModels;
	}
	public void setWorkoutModels(List<WorkoutModel> workoutModels) {
		this.workoutModels = workoutModels;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	public void add(WorkoutModel workoutModel){
		workoutModels.add(workoutModel);
	}
	public void addDrillModel(DrillModel drillModel){
		oneTimeWorkoutModel.addDrillModel(drillModel);
	}
	public void addDrillModel(String drillString){
		oneTimeWorkoutModel.addDrillModel(drillString);
	}
	@Override
	public String toString(){
		String workoutString="";
		Iterator<WorkoutModel> iterator= workoutModels.iterator();
		for(;iterator.hasNext();){
			workoutString+="\t"+iterator.next().toString()+"\n";
		}
		return author+"\n"+workoutString;
	}
	public  static void main(String[] args){
		WorkoutModel workoutModel = new WorkoutModel("Morning trainer");
		workoutModel.addDrillModel("push_up");
		workoutModel.addDrillModel("squat");
		workoutModel.addDrillModel("press");

		WorkoutModel workoutModel1 = new WorkoutModel("Home trainer");
		workoutModel1.addDrillModel("push_up");
		workoutModel1.addDrillModel("pull_up");
		workoutModel1.addDrillModel("press");
		Workout workout1 = new Workout();
		workout1.add("push_up",100);
		workout1.add("pull_up",100);
		workout1.add("press",200);
		Results results = new Results("mirinda");
		System.out.println(results.toString());


		/*try{
			String userPath="D:/";
			String SERIALIZED="serialized";
			File file= new File(userPath,SERIALIZED);
			if(file.exists())file.delete();//Log.i("mirinda",file.delete()?"":"do not "+"delete:"+file.getPath());
			FileOutputStream fos= new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(results);
			oos.flush();
			oos.close();
		} catch (FileNotFoundException e) {
			//Log.e("mirinda","file not found "+e.getMessage());
			System.out.println("class not found "+e.getMessage());
		} catch (IOException e) {
			//Log.e("mirinda","io exception "+e.getMessage());
			System.out.println("io exception "+e.getMessage());
		}

		try {
			String userPath="D:/";
			String SERIALIZED="serialized";
			File file= new File(userPath,SERIALIZED);
			file = new File(userPath,SERIALIZED);
			if(!file.isFile()) throw new IOException("not a file");
			if(!file.exists()) throw new IOException("not exist");//TODO my exceptions
			//Log.i("mirinda",file.createNewFile()?"":"do not"+" create new file:"+file.getPath());  //throw IOException

			FileInputStream inputStream= new FileInputStream(file);
			ObjectInputStream objectInputStream = new  ObjectInputStream(inputStream);
			results  = (Results) objectInputStream.readObject();
		} catch (IOException e) {

			System.out.println("io exception "+e.getMessage());
		} catch (ClassNotFoundException e) {

			System.out.println("class not found "+e.getMessage());
		}*/

		//System.out.println(results.toString());
	}
}
