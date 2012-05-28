package kz.mirinda.trainer;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import kz.mirinda.trainer.impl.Results;
import kz.mirinda.trainer.impl.WorkoutModel;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * new class
 *
 * @author mirinda
 */
public class AllDrillsOfDrillmodelActivity extends ListActivity {
	private final String W_MODEL_NAME="workout_model_name";
	private final String DRILL_NAME="drill_name";
	private final String DATE="date";
	private final String COUNT="count";

	private class DateBinder implements SimpleAdapter.ViewBinder{
		private SimpleDateFormat dateFormat;

		private DateBinder(String dateFormat) {
			this.dateFormat = new SimpleDateFormat(dateFormat);
		}

		@Override
		public boolean setViewValue(View view, Object o, String s) {
			if (view.getId()==R.id.addr_date){
				TextView textView = (TextView) view;
				textView.setText(dateFormat.format((Date) o));
				return  true;
			}
			return false;
		}
	}

	private Results results;
	private WorkoutModel allWorkoutModel;
	private String drillName;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.all_drills_of_drillmodel);
		Intent intent = getIntent();
		results = (Results) intent.getSerializableExtra("results");
		drillName = intent.getStringExtra("drillName");
		allWorkoutModel =  results.getOneTimeWorkoutModel();
		int position = intent.getIntExtra("position",0);
		List<WorkoutModel.DrillContainer> drillContainers = allWorkoutModel.getInfOfDrill(position);
		Collections.sort(drillContainers);
		Collections.reverse(drillContainers);

		SimpleAdapter adapter = new SimpleAdapter(this,createDrillsList(drillContainers),
				                                 R.layout.all_drills_of_drillmodel_row,
				                                 new String[]{DATE,COUNT},
				                                 new int[] {R.id.addr_date,R.id.addr_count});
		setListAdapter(adapter);
		DateBinder dateBinder = new DateBinder(ResultsActivity.TIME_FORMAT_ALL);
		adapter.setViewBinder(dateBinder);

		//MaX
		int max=0,sum=0;
		for(WorkoutModel.DrillContainer drillContainer:drillContainers){
			if (drillContainer.count>max) max= drillContainer.count;
			sum+=drillContainer.count;
		}
		TextView textView = (TextView) findViewById(R.id.add_max_count);
		textView.setText(""+max);
		textView = (TextView) findViewById(R.id.add_all_count);
		textView.setText(""+sum);


	}
	private List<Map<String,?>> createDrillsList(List<WorkoutModel.DrillContainer> drillContainers){
		List<Map<String,?>> hashMaps = new ArrayList<Map<String,?>>();

		for(WorkoutModel.DrillContainer drillContainer: drillContainers){
			Map<String, Object> hashMap = new HashMap<String, Object>();
			//hashMap.put(W_MODEL_NAME,"");
			//hashMap.put(DRILL_NAME,drillName);
			Date date =new Date(drillContainer.date);
			hashMap.put(DATE,date);
			hashMap.put(COUNT,drillContainer.count);
			hashMaps.add(hashMap);
		}
		return hashMaps;
	}
}