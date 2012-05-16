package kz.mirinda.trainer;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import kz.mirinda.trainer.impl.DrillModel;
import kz.mirinda.trainer.impl.Results;
import kz.mirinda.trainer.impl.WorkoutModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * new class
 *
 * @author mirinda
 */
public class WorkoutDrillsActivity extends ListActivity {
	Results results;
	private List<String> doDrillModelList(WorkoutModel workoutModel){
		if (workoutModel.getDrillModels() == null) {
			return new ArrayList<String>();
		}
		List<String> stringList =new ArrayList<String>(workoutModel.getDrillModels().size());
		Iterator<DrillModel> iterator = workoutModel.getDrillModels().iterator();
		while (iterator.hasNext()) {
			DrillModel drillModel = iterator.next();
			stringList.add(drillModel.getDrillName());
		}
		return stringList;
	}
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enter_drill);
		Intent intent =getIntent();
		results = (Results) intent.getSerializableExtra("results");
		int i = intent.getIntExtra("workout_model",0);
		WorkoutModel workoutModel=results.getWorkoutModels().get(i);
		WorkoutDrilslAdapter drillAdapter =new WorkoutDrilslAdapter(this,doDrillModelList(workoutModel),workoutModel);
		setListAdapter(drillAdapter);
	}


}