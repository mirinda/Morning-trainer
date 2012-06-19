package kz.mirinda.trainer;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import kz.mirinda.trainer.impl.DrillModel;
import kz.mirinda.trainer.impl.Results;
import kz.mirinda.trainer.impl.Workout;
import kz.mirinda.trainer.impl.WorkoutModel;
import kz.mirinda.trainer.saver.Saver;

import java.util.ArrayList;
import java.util.List;

/**
 * new class
 *
 * @author mirinda
 */
public class WorkoutDrillsActivity extends ListActivity {

	private WorkoutModel workoutModel;
	private Results results;
	private int type;

	private List<String> doDrillModelList(WorkoutModel workoutModel){
		if (workoutModel.getDrillModels() == null) {
			return new ArrayList<String>();
		}
		List<String> stringList =new ArrayList<String>(workoutModel.getDrillModels().size());
		for (DrillModel drillModel : workoutModel.getDrillModels()) {
			stringList.add(drillModel.getDrillName());
		}
		return stringList;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enter_drill);
		Intent intent =getIntent();
		results = TrainerApplication.getResults();
		type = intent.getIntExtra("type",Main.NAMES_WORKOUT);
		if(type==Main.NAMES_WORKOUT){
			int i = intent.getIntExtra("workout_model",0);
			workoutModel= results.getWorkoutModels().get(i);
		}else{
			workoutModel= (WorkoutModel) intent.getSerializableExtra("one_time_workout");
		}
		WorkoutDrillsAdapter drillAdapter =new WorkoutDrillsAdapter(this,doDrillModelList(workoutModel));
		setListAdapter(drillAdapter);
	}

	@Override
	public void onBackPressed() {
		WorkoutDrillsAdapter drillsAdapter =(WorkoutDrillsAdapter) getListAdapter();
		Workout workout = drillsAdapter.getWorkout();
		if(type==Main.NAMES_WORKOUT)workoutModel.add(workout);
		results.getOneTimeWorkoutModel().add(workout);
		Saver.saveResults(results);
		Log.i("mirinda1", "" + results.toString());
		Log.i("mirinda1","export results from WorkoutDrills");
		super.onBackPressed();
	}





}