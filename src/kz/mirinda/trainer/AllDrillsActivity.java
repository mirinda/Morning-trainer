package kz.mirinda.trainer;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
public class AllDrillsActivity extends ListActivity implements Button.OnClickListener{
	private Results results;
	private int type;
	private EditText editText;

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

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.all_drills);

		Intent intent = getIntent();
		Button button = (Button) findViewById(R.id.ald_button);
		button.setOnClickListener(this);
		editText = (EditText) findViewById(R.id.ald_text);
		results =(Results)intent.getSerializableExtra("results");
		type = intent.getIntExtra("type",Main.ONE_TIME_WORKOUT);
		if (type==Main.ONE_TIME_WORKOUT) editText.setVisibility(View.GONE);


		AllDrillsAdapter allDrillsAdapter;
		allDrillsAdapter=new AllDrillsAdapter(this,R.layout.all_drills,R.id.aldr_textview,doDrillModelList(results.getOneTimeWorkoutModel()),results.getOneTimeWorkoutModel());
		setListAdapter(allDrillsAdapter);
	}

	@Override
	public void onClick(View view) {
		AllDrillsAdapter allDrillsAdapter = (AllDrillsAdapter) getListAdapter();
		Intent intent = new Intent();
		intent.putExtra("results",results);
		intent.putExtra("type",type);
		WorkoutModel workoutModel = allDrillsAdapter.getOneTimeWorkout();
		if(type==Main.NAMES_WORKOUT){
			workoutModel.setWorkoutName(editText.getText().toString());
			results.getWorkoutModels().add(workoutModel);
			intent.putExtra("workout_model",results.getWorkoutModels().size()-1);
		}
        intent.putExtra("one_time_workout",workoutModel);
		intent.setClass(this,WorkoutDrillsActivity.class);
		startActivity(intent);
		finish();
	}
}