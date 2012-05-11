package kz.mirinda.trainer;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
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
public class MyWorkoutListActivity extends ListActivity {
	 Results results;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		results =(Results) getIntent().getSerializableExtra("results");
		List<String> objects= doWorkoutModelList(results);
		MyWorkoutAdapter workoutAdapter = new MyWorkoutAdapter(this,R.layout.workoutrow,R.id.wlabel, objects,results);
		setListAdapter(workoutAdapter);
		
		ListView listView =getListView();
		listView.getId();
	}
	private List<String> doWorkoutModelList(Results results){
		List<String> strings = new ArrayList<String>();
		Iterator<WorkoutModel> iterator =results.getWorkoutModels().iterator();
		while (iterator.hasNext()) {
			WorkoutModel next =  iterator.next();
			strings.add(next.getWorkoutName());
		}
		return strings;
	}


	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent intent = new Intent();
		intent.setClass(this,MyDrillListActivity.class);
		intent.putExtra("results", results);
		intent.putExtra("workout_model",position);
		startActivity(intent);
		finish();
	}
}
