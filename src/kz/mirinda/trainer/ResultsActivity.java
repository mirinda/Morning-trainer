package kz.mirinda.trainer;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LineGraphView;
import kz.mirinda.trainer.impl.Results;
import kz.mirinda.trainer.impl.WorkoutModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * new class
 *
 * @author mirinda
 */
public class ResultsActivity extends ListActivity implements View.OnClickListener, AdapterView.OnItemLongClickListener {

	public static final String TIME_FORMAT_HOUR = "dd.MM kk:mm";
	public static final String TIME_FORMAT_YEAR = "dd.MM.yy";
	public static final String TIME_FORMAT_ALL = "dd.MM.yy kk:mm:ss";
	private final long millSecOfDay = 24*60*60*1000;

	private MyGraphView graphView;
	private LinearLayout linearLayout;
	private Results results;
	private ListView listView;
	private long firstDate;//from mapping graph
	private GraphView.GraphViewSeries lastGraphViewSeries=null; //graphView didn't know if he has a Series.

	private class MyGraphView extends LineGraphView{

		public MyGraphView(Context context, String title) {
			super(context, title);
		}

		@Override
		protected String formatLabel(double value, boolean isValueX) {
			if(isValueX){
				DateFormat dateFormat = new SimpleDateFormat(TIME_FORMAT_YEAR);
				Date curDate = new Date(firstDate + (long) (value*millSecOfDay));
				return dateFormat.format(curDate);
			}
			return ""+value;
		}

	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		results = TrainerApplication.getResults();
		setMyContextView();
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, results.getOneTimeWorkoutModel().doDrillModelList());
		setListAdapter(arrayAdapter);
	}
	private void setMyContextView(){
		linearLayout = new LinearLayout(this);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		listView = new ListView(this);
		listView.setId(android.R.id.list);
		listView.setOnItemLongClickListener(this);
		linearLayout.addView(listView,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT,10));
		initGraphView();
		linearLayout.addView(graphView,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT,11));
		setContentView(linearLayout);

	}

	private void initGraphView() {

		graphView = new MyGraphView(this,getString(R.string.graph_title));
		graphView.setViewPort(0,1);
		graphView.setScrollable(true);
		graphView.setScalable(true);
		initGraphViewFromPosition(0);
	}

	private void initGraphViewFromPosition(int position) {
		//get all  pair date and count from drill on position
		WorkoutModel allWorkoutModel = results.getOneTimeWorkoutModel();
		List<WorkoutModel.DrillContainer> drillContainers = allWorkoutModel.getInfOfDrill(position);
		Collections.sort(drillContainers);



		//init datas from drill
		GraphView.GraphViewData[] graphViewDatas = new GraphView.GraphViewData[drillContainers.size()];
		int i=0;
		if(drillContainers.isEmpty()){
			graphViewDatas=new GraphView.GraphViewData[]{new GraphView.GraphViewData(0,0)};
			firstDate=0;
		}else{
			firstDate = drillContainers.get(0).date;
		}
		for (WorkoutModel.DrillContainer next : drillContainers) {
			//Log.i("mirinda1",next.toString()+"time:"+((next.date-first)*1.0)/millSecOfDay+"/n");
			graphViewDatas[i] = new GraphView.GraphViewData(((next.date-firstDate)*1.0)/millSecOfDay, next.count);
			i++;
		}

		//init new graphViewSeries
		if (lastGraphViewSeries!=null){
			graphView.removeSeries(lastGraphViewSeries);
		}

		lastGraphViewSeries = new GraphView.GraphViewSeries(graphViewDatas);
		graphView.addSeries(lastGraphViewSeries);
		graphView.invalidate();//refresh view
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		initGraphViewFromPosition(position);
	}


	@Override
	public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
		Intent intent= new Intent();
		intent.setClass(this,AllDrillsOfDrillmodelActivity.class);
		intent.putExtra("results",results);
		TextView textView = (TextView) view.findViewById(android.R.id.text1);
		intent.putExtra("drillName",textView.getText().toString());
		intent.putExtra("position",i);
		startActivity(intent);
		return false;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void onClick(View view) {
		listView.setVisibility(View.GONE);
		Log.i("mirinda1","click");
	}

}