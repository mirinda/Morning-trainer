package kz.mirinda.trainer;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;

/**
 * new class
 *
 * @author mirinda
 */
public class TabResultActivity extends TabActivity {
	public void onCreate(Bundle savedInstanceState) {
		Log.i("mirinda1", "TabResultActivity create");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_result);
		TabHost tabHost = getTabHost();

		TabHost.TabSpec drillTab = tabHost.newTabSpec("drill_tab");
		drillTab.setIndicator(getResources().getText(R.string.tab_r_drill_tab));
		drillTab.setContent(new Intent(this,ResultsActivity.class));

		TabHost.TabSpec timeTab = tabHost.newTabSpec("time_tab");
		timeTab.setIndicator(getResources().getText(R.string.tab_r_time_tab));
		timeTab.setContent(new Intent(this,TimeResultsActivity.class));

		tabHost.addTab(drillTab);
		tabHost.addTab(timeTab);
	}
}