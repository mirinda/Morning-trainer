package kz.mirinda.trainer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import kz.mirinda.trainer.impl.Workout;

import java.io.File;
import java.util.Arrays;

public class Start extends Activity implements View.OnClickListener
{
    private Button btn;
    private String state="";
    private String appPath="" ;
    private String userPath="" ;
    private String user = "mirinda";
    private Workout workout= new Workout();
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        //file
        state=Environment.getExternalStorageState();
        if(state.equals(Environment.MEDIA_MOUNTED)){
            // is exists folders
            File file = new File(Environment.getExternalStorageDirectory(),"morning-trainer");

            if(!file.isDirectory())Log.e("mirinda", file.delete() ? "delete" : "cannot delete file :" + file.getPath());
            if(file.mkdir()) Log.i("mirinda","create directory: "+ file.getName());
            appPath = file.getPath();

            file = new File(appPath,user);
            if(!file.isDirectory())Log.e("mirinda",file.delete()?"delete":"cannot delete file :"+file.getPath());
            if(file.mkdir()) Log.i("mirinda","create directory: "+ file.getName());
            userPath = file.getPath();
            
            String[] lst =  file.list();
            Log.i("mirinda", Arrays.toString(lst));

            // TODO load data

        }else{
            Log.e("mtrainer","External storage unavailable");
        }

        //other
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        btn =(Button) findViewById(R.id.push_up_submit);
        btn.setOnClickListener(this);
        btn =(Button) findViewById(R.id.press_submit);
        btn.setOnClickListener(this);
        btn =(Button) findViewById(R.id.squat_submit);
        btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
           case R.id.push_up_submit:  Log.i("mirinda","Push up");  break;
           case R.id.press_submit:    Log.i("mirinda","Press");    break;
           case R.id.squat_submit:    Log.i("mirinda","Squat");    break;
           default:Log.i("mirinda","other");
        };
    }
}
