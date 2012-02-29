package kz.mirinda.trainer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import kz.mirinda.trainer.impl.Results;
import kz.mirinda.trainer.impl.Workout;

import java.io.*;
import java.util.Arrays;

public class Start extends Activity implements View.OnClickListener
{
    private Button btn;
    private String MYTAG="mirinda1";
    private String state="";
    private String appPath="" ;
    private String userPath="" ;
    private String user = "mirinda";
    private Results results = new Results(user);
    private Workout workout= new Workout();
    private final String SERIALIZED="serialized";
    private boolean pushUpSubmitted=false;
    private boolean pressSubmitted=false;
    private boolean squatSubmitted=false;

    private class DigitInputFilter implements InputFilter{

        @Override
        public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
            Log.i(MYTAG,"StringFilter:"+charSequence.toString());
            return "";
        }
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        EditText editText;
        btn =(Button) findViewById(R.id.push_up_submit);
        btn.setOnClickListener(this);
        btn =(Button) findViewById(R.id.press_submit);
        btn.setOnClickListener(this);
        btn =(Button) findViewById(R.id.squat_submit);
        btn.setOnClickListener(this);

        editText = (EditText) findViewById(R.id.push_up_edit);
        editText.setFilters(new InputFilter[]{new DigitInputFilter()});
    }
    @Override
    public void onStart(){
        super.onStart();
        //file
        state=Environment.getExternalStorageState();
        if(state.equals(Environment.MEDIA_MOUNTED)){
            // is exists folders
            File file = new File(Environment.getExternalStorageDirectory(),"morning-trainer");

            if(!file.isDirectory())Log.e(MYTAG, file.delete() ? "delete" : "cannot delete file :" + file.getPath());
            if(file.mkdir()) Log.i(MYTAG,"create directory: "+ file.getName());
            appPath = file.getPath();

            file = new File(appPath,user);
            if(!file.isDirectory())Log.e(MYTAG,file.delete()?"delete":"cannot delete file :"+file.getPath());
            if(file.mkdir()) Log.i(MYTAG,"create directory: "+ file.getName());
            userPath = file.getPath();

            String[] lst =  file.list();
            Log.i(MYTAG, Arrays.toString(lst));
            //load data
            try {
                file = new File(userPath,SERIALIZED);
                if(!file.isFile()) throw new IOException("not a file");
                if(!file.exists()) throw new IOException("not exist");//TODO my exceptions

                FileInputStream inputStream= new FileInputStream(file);
                ObjectInputStream objectInputStream = new  ObjectInputStream(inputStream);
                results  = (Results) objectInputStream.readObject();
                Log.i(MYTAG, results.toString());
                //last workout
                Workout lastWorkout;
                if((lastWorkout=results.getLastWorkout())==null) throw new IOException("haven't any workouts");
                Log.i(MYTAG, lastWorkout.toString());
                Log.i(MYTAG, workout.getDrill("press") + "");
                ((EditText)findViewById(R.id.press_edit)).setText(lastWorkout.getDrill("press").getMaxInt()+"");
                ((EditText)findViewById(R.id.push_up_edit)).setText(lastWorkout.getDrill("push_up").getMaxInt()+"");
                ((EditText)findViewById(R.id.squat_edit)).setText(lastWorkout.getDrill("squat").getMaxInt()+"");
            } catch (IOException e) {
                Log.i(MYTAG,"cannot find serialized file "+e.getMessage());
            } catch (ClassNotFoundException e) {
                //e.printStackTrace();
                Log.i(MYTAG,"bad serialized file");
            }



        }else{
            Log.e("mtrainer","External storage unavailable");
        }
    }
    @Override
    public void onClick(View view){
        EditText editText;
        switch (view.getId()){
            case R.id.push_up_submit:
                Log.i(MYTAG,"Push up");
                pushUpSubmitted=true;
                editText = (EditText) findViewById(R.id.push_up_edit);
                editText.setClickable(false);
                workout.add(Workout.PUSH_UP,Integer.parseInt(editText.getText().toString()));      //TODO inputfilter to digits
                break;

            case R.id.press_submit:
                Log.i(MYTAG,"Press");
                pressSubmitted=true;
                editText = (EditText) findViewById(R.id.press_edit);
                editText.setClickable(false);
                workout.add(Workout.PRESS,Integer.parseInt(editText.getText().toString()));
                break;

            case R.id.squat_submit:
                Log.i(MYTAG,"Squat");
                squatSubmitted=true;
                editText = (EditText) findViewById(R.id.press_edit);
                editText.setClickable(false);
                workout.add(Workout.SQUAT,Integer.parseInt(editText.getText().toString()));
                break;
            default:Log.i(MYTAG,"other");
        }

        if(pressSubmitted&&pushUpSubmitted&&squatSubmitted) results.add(workout);
    }
    @Override
    public void onStop(){
        super.onStop();
        SaveResults();
    }


    void SaveResults(){
        try{
            File file= new File(userPath,SERIALIZED);
            if(file.exists())Log.i(MYTAG,file.delete()?"":"do not "+"delete:"+file.getPath());
            FileOutputStream fos= new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(results);
            oos.flush();
            oos.close();
        } catch (FileNotFoundException e) {
            Log.e(MYTAG,"file not found "+e.getMessage());
        } catch (IOException e) {
            Log.e(MYTAG,"io exception "+e.getMessage());
        }
    }
}
