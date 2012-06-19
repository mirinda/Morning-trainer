package kz.mirinda.trainer;

import android.app.Application;
import android.util.Log;
import kz.mirinda.trainer.impl.Results;
import kz.mirinda.trainer.saver.Saver;

/**
 * new class
 *
 * @author mirinda
 */
public class TrainerApplication extends Application {
	private static Results results=null;

	public static Results getResults() {
		return results;
	}

	public static void setResults(Results results) {
		TrainerApplication.results = results;
	}

	@Override
	public void onTerminate() {
		Saver.saveResults(results);
		super.onTerminate();    //To change body of overridden methods use File | Settings | File Templates.
	}

	@Override
	public void onCreate() {
		Saver.setUser(Main.user);
		if((results =Saver.initResults()) != null) Log.i("mirinda1", "import file from main");
		else Log.e("mirinda1", "cannot import");
		super.onCreate();    //To change body of overridden methods use File | Settings | File Templates.

	}
}
