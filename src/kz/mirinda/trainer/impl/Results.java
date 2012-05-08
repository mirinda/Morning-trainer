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

	public Results(){
		this.author="";
	}
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
		Results results = new Results("mirinda");
		results.add(workout);
		results.add(workout1);
		System.out.println(results.toString());

		Workout workout2 = results.getLastWorkout();
		System.out.println("  :"+workout2);
		Drill drill= workout2.getDrill("push_up");
		System.out.println("  :"+drill);
		System.out.println("  :"+drill.getMaxInt());
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
