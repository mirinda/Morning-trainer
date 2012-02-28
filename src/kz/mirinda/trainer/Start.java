package kz.mirinda.trainer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import kz.mirinda.trainer.impl.Results;
import kz.mirinda.trainer.impl.Workout;

import java.io.*;
import java.util.Arrays;

public class Start extends Activity implements View.OnClickListener
{
    private Button btn;
    private String state="";
    private String appPath="" ;
    private String userPath="" ;
    private String user = "mirinda";
    private Results results = new Results(user);
    private Workout workout= new Workout();
    private final String SERIALIZED="serialized";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState){
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
        }
    }
    @Override
    public void onStart(){
        super.onStart();
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
            //load data
            try {
                file = new File(userPath,SERIALIZED);
                if(!file.isFile()) throw new IOException("not a file");
                if(!file.exists()) throw new IOException("not exist");//TODO my exceptions

                FileInputStream inputStream= new FileInputStream(file);
                ObjectInputStream objectInputStream = new  ObjectInputStream(inputStream);
                results  = (Results) objectInputStream.readObject();
                //last workout
                workout=results.getLastWorkout();
                ((EditText)findViewById(R.id.press_edit)).setText(workout.getDrill("press").getMaxInt()+"");
                ((EditText)findViewById(R.id.push_up_edit)).setText(workout.getDrill("push_up").getMaxInt()+"");
                ((EditText)findViewById(R.id.squat_edit)).setText(workout.getDrill("squat").getMaxInt()+"");
            } catch (IOException e) {
                Log.i("mirinda","cannot find serialized file "+e.getMessage());
            } catch (ClassNotFoundException e) {
                //e.printStackTrace();
                Log.i("mirinda","bad serialized file");
            }



        }else{
            Log.e("mtrainer","External storage unavailable");
        }
    }
    void SaveResults(){
        try{
            File file= new File(userPath,SERIALIZED);
            if(file.exists())Log.i("mirinda",file.delete()?"":"do not "+"delete:"+file.getPath());
            FileOutputStream fos= new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(results);
            oos.flush();
            oos.close();
        } catch (FileNotFoundException e) {
            Log.e("mirinda","file not found "+e.getMessage());
        } catch (IOException e) {
            Log.e("mirinda","io exception "+e.getMessage());
        }
    }
}
