package kz.mirinda.trainer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import kz.mirinda.trainer.impl.WorkoutModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * new class
 *
 * @author mirinda
 */
public class MyDrillAdapter extends ArrayAdapter<String> {
	private WorkoutModel workoutModel;
	private  Context context;
	private List<RowContainer> rowContainerList;
	private class RowContainer{
		public String label;
		public int count;
		public boolean checked;
		public boolean submitted;
	}
	private class ViewHolder{
		public CheckBox checkBox;
		public TextView textView;
		public EditText editText;
		public Button button;

	}

	private void initRowContainerList(List<String> labels){
		if (labels == null) {
			rowContainerList = new ArrayList<RowContainer>();
			return;
		}
		rowContainerList = new ArrayList<RowContainer>(labels.size());
		Iterator<String> iterator = labels.iterator();
		RowContainer rowContainer;
		int i=0;
		while (iterator.hasNext()) {
			String label = iterator.next();
			rowContainer = new RowContainer();
			rowContainer.label = label;
			rowContainer.checked=false;
			rowContainer.count=0;
			rowContainer.submitted=false;
			rowContainerList.add(i++,rowContainer);
		}
		return;
	}
	
	public MyDrillAdapter(Context context, List<String> objects, WorkoutModel workoutModel) {
		super(context, R.layout.enter_drillrow, R.id.wlabel, objects);
		this.context =context;
		this.workoutModel = workoutModel;
		initRowContainerList(objects);

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		ViewHolder holder;

		if (rowView == null) {
			LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = layoutInflater.inflate(R.layout.enter_drillrow,parent,false);

			holder = new ViewHolder();
		   	holder.checkBox =(CheckBox) rowView.findViewById(R.id.dbox);
			holder.button= (Button) rowView.findViewById(R.id.dbutton);
			holder.editText= (EditText) rowView.findViewById(R.id.dedit);
			holder.textView= (TextView) rowView.findViewById(R.id.dlabel);

			rowView.setTag(holder);

		}else{

			holder =(ViewHolder) rowView.getTag();
		}

		RowContainer rowContainer =rowContainerList.get(position);

		Integer integer = new Integer(position);
		holder.textView.setText(rowContainer.label);
		holder.textView.setTag(0,integer);
		holder.editText.setText(rowContainer.count+"");
		holder.checkBox.setTag(0,integer);
		holder.checkBox.setChecked(rowContainer.checked);
		holder.button.setTag(0,integer);
		if(rowContainer.submitted)holder.button.setBackgroundColor(R.color.orange);

		return rowView;
	}
}
