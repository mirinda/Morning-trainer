package kz.mirinda.trainer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import kz.mirinda.trainer.impl.WorkoutModel;

import java.util.ArrayList;
import java.util.List;

/**
 * new class
 *
 * @author mirinda
 */
public class AllDrillsAdapter extends ArrayAdapter<String> implements CheckBox.OnCheckedChangeListener {

	Context  context;
	List<BooleanContainer> checked ;
	private List<String> objects;
	private WorkoutModel workoutModel;

	private class BooleanContainer{
		BooleanContainer(boolean b){ this.b=b;}
		boolean b;
	}
	private class ViewHolder{
		CheckBox checkBox;
		TextView  textView;
	}

	public AllDrillsAdapter(Context context, int resource, int textViewResourceId, List<String> objects,WorkoutModel workoutModel) {
		super(context, resource, textViewResourceId, objects);
		this.context = context;
		this.objects = objects;
		this.workoutModel = workoutModel;
		//init checked (checkbox model)
		checked=new ArrayList<BooleanContainer>(objects.size());
		for (String object : objects) {
			checked.add(new BooleanContainer(false));
		}
	}

	public WorkoutModel getOneTimeWorkout(){
		int i=0;
		WorkoutModel workoutModel1 = new WorkoutModel();
		 for(BooleanContainer container:checked){
			if(container.b){
				workoutModel1.addDrillModel(workoutModel.getDrillModels().get(i));
			}
			i++;
		 }
		return workoutModel1;
	}
	public void addObject(String s){
		objects.add(s);
		checked.add(new BooleanContainer(false));
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		ViewHolder viewHolder;
		if (rowView == null) {
			viewHolder = new ViewHolder();
			LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = layoutInflater.inflate(R.layout.all_drills_row,parent,false);
			viewHolder.textView = (TextView) rowView.findViewById(R.id.aldr_textview);
			viewHolder.checkBox = (CheckBox) rowView.findViewById(R.id.aldr_checkbox);
			viewHolder.checkBox.setOnCheckedChangeListener(this);
			rowView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) rowView.getTag();
		}

		viewHolder.checkBox.setTag(position);
		viewHolder.checkBox.setChecked(checked.get(position).b);
		viewHolder.textView.setText(objects.get(position));
		return rowView;
	}
	@Override
	public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
	 	int position = (Integer) compoundButton.getTag();
		checked.get(position).b=b;
	}

}
