package kz.mirinda.trainer;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListAdapter;
import android.widget.SimpleExpandableListAdapter;
import kz.mirinda.trainer.impl.Results;
import kz.mirinda.trainer.impl.WorkoutModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * new class
 *
 * @author mirinda
 */
public class TimeResultsActivity extends ExpandableListActivity {
	private final String TIME="time";
	private final String DRILLNAME="drillname";
	private final String COUNT="count";
	private Results results;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.time_results);
		results = TrainerApplication.getResults();
		List<Date> days = results.getOneTimeWorkoutModel().getAllDays();
		Log.i("mirinda1", "" + doGroupList(days));
		ExpandableListAdapter listAdapter= new SimpleExpandableListAdapter(this,doGroupList(days),R.layout.time_r_group_row,
				new String[]{TIME},new int[]{android.R.id.text1},doChildList(days),R.layout.time_r_child_row,
				new String[]{TIME,DRILLNAME,COUNT},new int[]{R.id.text, android.R.id.text1,android.R.id.text2});
		setListAdapter(listAdapter);
	}

	List<List<Map<String,?>>> doChildList(List<Date> days){
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		//dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		List<List<Map<String,?>>> childList = new ArrayList<List<Map<String, ?>>>(days.size());
		for(Date day:days){
			List<WorkoutModel.AllInfDrillContainer> drillContainers = results.getOneTimeWorkoutModel().getDayDrills(day);
			List<Map<String,?>> maps = new ArrayList<Map<String, ?>>(drillContainers.size());
			for (WorkoutModel.AllInfDrillContainer  drillContainer : drillContainers) {
				HashMap<String,String> hashMap = new HashMap<String, String>();
				hashMap.put(TIME,dateFormat.format(new Date(drillContainer.date)));
				hashMap.put(DRILLNAME,drillContainer.drillName);
				hashMap.put(COUNT, String.valueOf(drillContainer.count));
				maps.add(hashMap);
			}
			childList.add(maps);
		}
		return childList;
	}

	List<Map<String, ?>> doGroupList(List<Date> days){
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		List<Map<String, ?>> maps = new ArrayList<Map<String, ?>>(days.size());
		for (Date  day : days) {
			HashMap<String,String> hashMap =  new HashMap<String, String>();
			hashMap.put(TIME,dateFormat.format(day));
			maps.add(hashMap);
		}
		 return  maps;
	}
}