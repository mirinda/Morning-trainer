package kz.mirinda.trainer;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import kz.mirinda.trainer.impl.Results;

/**
 * new class
 *
 * @author mirinda
 */
public class Main extends Activity implements OnClickListener{

	public static String user = "mirinda";
	private Results results;
	private Button trainButton;
	private Button resultButton;
	private Button nowButton;
	static final int ONE_TIME_WORKOUT=0;
	static final int NAMES_WORKOUT=1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.i("mirinda1","Main.onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main1);
		trainButton = (Button) findViewById(R.id.train);
		resultButton= (Button) findViewById(R.id.result);
		nowButton= (Button) findViewById(R.id.now);
		nowButton.setOnClickListener(this);
		resultButton.setOnClickListener(this);
		trainButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		if(view.getId()==R.id.train){
			Intent intent= new Intent();
			intent.setClass(this,AllWorkoutsActivity.class);
			startActivity(intent);
			//finish();
		}else if(view.getId()==R.id.now){
			Intent intent= new Intent();
			intent.setClass(this,AllDrillsActivity.class);
			intent.putExtra("type",ONE_TIME_WORKOUT);
			startActivity(intent);
			//finish();
		} else if(view.getId()==R.id.result){
			Intent intent = new Intent();
			intent.setClass(this,TabResultActivity.class);
			startActivity(intent);
			//finish();

		}

	}
	@Override
	protected void onStop() {
		Log.i("mirinda1","Main.onStop");
		super.onStop();
		//Saver.saveResults(results);
		Log.i("mirinda1","export file from main");
	}

	@Override
	protected void onStart() {
		Log.i("mirinda1","Main.onStart");
		super.onStart();
		if((results =TrainerApplication.getResults()) != null)Log.i("mirinda1","import file from main");


	}
}