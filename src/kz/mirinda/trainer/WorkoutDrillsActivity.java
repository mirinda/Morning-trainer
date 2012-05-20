package kz.mirinda.trainer;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import kz.mirinda.trainer.impl.DrillModel;
import kz.mirinda.trainer.impl.Results;
import kz.mirinda.trainer.impl.WorkoutModel;

import java.util.ArrayList;
import java.util.List;

/**
 * new class
 *
 * @author mirinda
 */
public class WorkoutDrillsActivity extends ListActivity {

	private WorkoutModel workoutModel;

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
		Results results = (Results) intent.getSerializableExtra("results");
		if(intent.getIntExtra("type",Main.NAMES_WORKOUT)==Main.NAMES_WORKOUT){
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
		workoutModel.add(drillsAdapter.getWorkout());
		//Log.i("mirinda1", "" +workoutModel.toString()); //TODO save all results here.
		super.onBackPressed();
	}





}