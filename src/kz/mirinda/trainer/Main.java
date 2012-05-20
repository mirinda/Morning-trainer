package kz.mirinda.trainer;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import kz.mirinda.trainer.impl.Results;
import kz.mirinda.trainer.impl.Workout;
import kz.mirinda.trainer.impl.WorkoutModel;

/**
 * new class
 *
 * @author mirinda
 */
public class Main extends Activity implements OnClickListener{
	private Button btn;
	private String MYTAG="mirinda1";
	private String state="";
	private String appPath="" ;
	private String userPath="" ;
	private String user = "mirinda";
	private Results results = new Results(user);
	private Workout workout= new Workout();
	private Button trainButton;
	private Button resultButton;
	private Button nowButton;
	static final int ONE_TIME_WORKOUT=0;
	static final int NAMES_WORKOUT=1;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main1);
		trainButton = (Button) findViewById(R.id.train);
		resultButton= (Button) findViewById(R.id.result);
		nowButton= (Button) findViewById(R.id.now);
		nowButton.setOnClickListener(this);
		//resultButton.setOnClickListener();
		trainButton.setOnClickListener(this);
		WorkoutModel workoutModel = new WorkoutModel("Morning trainer");
		workoutModel.addDrillModel("push_up");
		workoutModel.addDrillModel("squat");
		workoutModel.addDrillModel("press");
		workoutModel.addDrillModel("push_up1");
		workoutModel.addDrillModel("squat1");
		workoutModel.addDrillModel("press1");
		results.addDrillModel("push_up");
		results.addDrillModel("squat");
		results.addDrillModel("press");
		results.addDrillModel("press1");
		results.addDrillModel("push_up1");
		results.addDrillModel("squat1");


		WorkoutModel workoutModel1 = new WorkoutModel("Home trainer");
		workoutModel1.addDrillModel("push_up");
		workoutModel1.addDrillModel("pull_up");
		workoutModel1.addDrillModel("press");
		Workout workout1 = new Workout();
		workout1.add("push_up",100);
		workout1.add("pull_up",100);
		workout1.add("press",200);
		results.add(workoutModel);
		results.add(workoutModel1);
	}

	@Override
	public void onClick(View view) {
		if(view.getId()==R.id.train){
			Intent intent= new Intent();
			intent.setClass(this,AllWorkoutsActivity.class);
			intent.putExtra("results", results);
			startActivity(intent);
			finish();
		}else if(view.getId()==R.id.now){
			Intent intent= new Intent();
			intent.setClass(this,AllDrillsActivity.class);
			//intent.putExtra("results", results);
			intent.putExtra("type",ONE_TIME_WORKOUT);
			intent.putExtra("results", results);
			startActivity(intent);
			finish();
		}

	}
}