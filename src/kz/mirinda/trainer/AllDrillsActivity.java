package kz.mirinda.trainer;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
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
public class AllDrillsActivity extends ListActivity implements Button.OnClickListener {
	private Results results;
	private int type;
	private EditText editText;
	private AllDrillsActivity.MyDialogListener myDialogListener;
	private AllDrillsAdapter allDrillsAdapter;

	private class MyDialogListener implements DialogInterface.OnClickListener{
		private String resultString =  null;
		private View dialogView=null;

		private MyDialogListener(View dialogView) {
			this.dialogView = dialogView;
		}

		@Override
		public void onClick(DialogInterface dialogInterface, int i) {
			if( i == DialogInterface.BUTTON_POSITIVE){
				EditText editText1 = (EditText) dialogView.findViewById(R.id.alert_edit);
				dialogView.setBackgroundColor(R.color.orange);
				resultString = editText1!=null?editText1.getText().toString():null;
				if ("".equals(resultString)) dialogInterface.cancel();
				results.getOneTimeWorkoutModel().addDrillModel(resultString);
				allDrillsAdapter.addObject(resultString);
				allDrillsAdapter.notifyDataSetChanged();
				Log.i("mirinda1", "onclick in dialog: "+ results.toString());
			}

		}

	}
	private List<String> doDrillModelList(WorkoutModel workoutModel) {
		if (workoutModel.getDrillModels() == null) {
			return new ArrayList<String>();
		}
		List<String> stringList = new ArrayList<String>(workoutModel.getDrillModels().size());
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

		Button button1 = (Button) findViewById(R.id.ald_new_drill);
		button1.setOnClickListener(this);

		editText = (EditText) findViewById(R.id.ald_text);
		results = (Results) intent.getSerializableExtra("results");
		type = intent.getIntExtra("type", Main.ONE_TIME_WORKOUT);
		if (type == Main.ONE_TIME_WORKOUT) editText.setVisibility(View.GONE);

		allDrillsAdapter = new AllDrillsAdapter(this, R.layout.all_drills, R.id.aldr_textview, doDrillModelList(results.getOneTimeWorkoutModel()), results.getOneTimeWorkoutModel());
		setListAdapter(allDrillsAdapter);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.ald_button:
				AllDrillsAdapter allDrillsAdapter = (AllDrillsAdapter) getListAdapter();
				Intent intent = new Intent();
				intent.putExtra("results", results);
				intent.putExtra("type", type);
				WorkoutModel workoutModel = allDrillsAdapter.getOneTimeWorkout();
				if (type == Main.NAMES_WORKOUT) {
					workoutModel.setWorkoutName(editText.getText().toString());
					results.getWorkoutModels().add(workoutModel);
					intent.putExtra("workout_model", results.getWorkoutModels().size() - 1);
				}
				intent.putExtra("one_time_workout", workoutModel);
				intent.setClass(this, WorkoutDrillsActivity.class);
				startActivity(intent);
				finish();
				break;
			case R.id.ald_new_drill:
				LayoutInflater layoutInflater = LayoutInflater.from(this);
				View view1 = layoutInflater.inflate(R.layout.alert_new_drill,null);

				AlertDialog.Builder builder= new AlertDialog.Builder(this);
				builder.setTitle(getString(R.string.create_drill));
				builder.setView(view1);

				 myDialogListener = new MyDialogListener(view1);
				builder.setPositiveButton(android.R.string.ok,myDialogListener);
				builder.setNegativeButton(android.R.string.cancel,myDialogListener);

				AlertDialog alertDialog = builder.create();
				alertDialog.show();

				break;
		}
	}

}