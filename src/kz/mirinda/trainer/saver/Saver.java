package kz.mirinda.trainer.saver;

import android.os.Environment;
import android.util.Log;
import kz.mirinda.trainer.impl.Results;

import java.io.*;
import java.util.Arrays;

/**
 * new class
 *
 * @author mirinda
 */
public class Saver {
	private static final  String SERIALIZED="serialized";
	private static String userPath;
	private static String appPath;

	public static String getUser() {
		return user;
	}

	public static void setUser(String user) {
		Saver.user = user;
	}

	private static String user="mirinda";

	public static void saveResults(Results results){
		try{
			File file= new File(userPath,SERIALIZED);
			if(file.exists()) Log.i("mirinda1", file.delete() ? "deleted" : "do not " + "delete:" + file.getPath());
			FileOutputStream fos= new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(results);
			oos.flush();
			oos.close();
		} catch (FileNotFoundException e) {
			Log.e("mirinda1","file not found "+e.getMessage());
		} catch (IOException e) {
			Log.e("mirinda1", "io exception " + e.getMessage());

		}
	}
	public static Results initResults(){

		Results results;
		String state = Environment.getExternalStorageState();

		if(state.equals(Environment.MEDIA_MOUNTED)){   //TODO: MEDIA_READONLY
			// is exists folders
			File file = new File(Environment.getExternalStorageDirectory(),"morning-trainer");

			if(!file.isDirectory())Log.e("mirinda1", file.delete() ? "delete" : "cannot delete file :" + file.getPath());//TODO and what? if can't delete
			if(file.mkdir()) Log.i("mirinda1","create directory: "+ file.getName());
			appPath = file.getPath();

			file = new File(appPath,user);
			if(!file.isDirectory())Log.e("mirinda1",file.delete()?"delete":"cannot delete file :"+file.getPath());
			if(file.mkdir()) Log.i("mirinda1","create directory: "+ file.getName());
			userPath = file.getPath();

			String[] lst =  file.list();
			Log.i("mirinda1", Arrays.toString(lst));
			//load data
			try {
				file = new File(userPath,SERIALIZED);
				if(!file.exists()) throw new IOException("not exist");
				if(!file.isFile()) throw new IOException("not a file");//TODO my exceptions

				FileInputStream inputStream= new FileInputStream(file);
				ObjectInputStream objectInputStream = new  ObjectInputStream(inputStream);
				results  = (Results) objectInputStream.readObject();
				Log.i("mirinda1", results.toString());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				results=null;
			} catch (OptionalDataException e) {
				e.printStackTrace();
				results=null;
			} catch (FileNotFoundException e) {
				Log.e("mirinda1","File not found");
				results=null;
			} catch (StreamCorruptedException e) {
				e.printStackTrace();
				results=null;
			} catch (IOException e) {
				Log.e("mirinda1",""+e.getMessage());
				results = new Results(user);
			}
		}else{
			Log.e("mirinda1","SD card didn't mounted");
			results=null;
		}
		return  results;
	}
}
