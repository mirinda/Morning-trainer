package kz.mirinda.trainer;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import kz.mirinda.trainer.impl.Results;
import kz.mirinda.trainer.impl.WorkoutModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Displays all the workouts provides an opportunity to create a new workout
 * Отображает все тренировки, даёт возможность создать новую тренировку
 *
 * @author mirinda
 */
public class AllWorkoutsActivity extends ListActivity implements Button.OnClickListener{
	 Results results;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.all_workouts);
		Button  button = (Button) findViewById(R.id.aw_button);
		button.setOnClickListener(this);
		results =(Results) getIntent().getSerializableExtra("results");
		List<String> objects= doWorkoutModelList(results);
		AllWorkoutsAdapter workoutAdapter = new AllWorkoutsAdapter(this,R.layout.all_workouts_row,R.id.wlabel, objects);
		setListAdapter(workoutAdapter);
	}
	private List<String> doWorkoutModelList(Results results){
		List<String> strings = new ArrayList<String>(results.getWorkoutModels().size());
		for (WorkoutModel next : results.getWorkoutModels()) {
			strings.add(next.getWorkoutName());
		}
		return strings;
	}


	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent intent = new Intent();
		intent.setClass(this,WorkoutDrillsActivity.class);
		intent.putExtra("workout_model",position);
		startActivity(intent);
		finish();
	}

	@Override
	public void onClick(View view) {
		Intent intent= new Intent();
		intent.putExtra("results",results);
		intent.putExtra("type",Main.NAMES_WORKOUT);
		intent.setClass(this,AllDrillsActivity.class);
		startActivity(intent);
		finish();
	}
}
