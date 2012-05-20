package kz.mirinda.trainer;

import android.app.Activity;
import android.os.Bundle;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LineGraphView;

/**
 * new class
 *
 * @author mirinda
 */
public class ResultsActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GraphView.GraphViewSeries exampleSeries = new GraphView.GraphViewSeries(new GraphView.GraphViewData[] {
				new GraphView.GraphViewData(1, 2.0d)
				, new GraphView.GraphViewData(2, 1.5d)
				, new GraphView.GraphViewData(3, 2.5d)
				, new GraphView.GraphViewData(4, 1.0d)
		});

		GraphView graphView = new LineGraphView(this,getString(R.string.graph_title));
		graphView.addSeries(exampleSeries);
		graphView.setViewPort(0,1);
		graphView.setScrollable(true);
		graphView.setScalable(true);
		graphView.setVerticalLabels(new String[]{"10","20"});
	    //GraphView.GraphViewData graphViewData= new GraphView.GraphViewData();
		//graphView.
		//DrawView drawView = new DrawView(this);
		//drawView.setBackgroundColor(Color.BLACK);
		setContentView(graphView);
	}
}