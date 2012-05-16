package kz.mirinda.trainer;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
public class WorkoutDrilslAdapter extends ArrayAdapter<String> implements View.OnClickListener,CheckBox.OnCheckedChangeListener
{

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
	private class MyTextWatcher implements TextWatcher{
		private ViewHolder holder;
		public MyTextWatcher(ViewHolder viewHolder){
			holder = viewHolder;
		}
		@Override
		public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

		@Override
		public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

		@Override
		public void afterTextChanged(Editable editable) {
			Integer.getInteger(holder.editText.getText().toString());

		}
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
	}
	
	public WorkoutDrilslAdapter(Context context, List<String> objects, WorkoutModel workoutModel) {
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
			holder.checkBox.setOnCheckedChangeListener(this);
			holder.button= (Button) rowView.findViewById(R.id.dbutton);
			holder.button.setOnClickListener(this);
			holder.editText= (EditText) rowView.findViewById(R.id.dedit);
			holder.editText.addTextChangedListener(new TextWatcher() {
				@Override
				public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

				@Override
				public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

				@Override
				public void afterTextChanged(Editable editable) {
					 //Integer(holder.editText.getText());
					Log.i("mirinda1","i am changed : "+ editable);
				}
			});
			holder.textView= (TextView) rowView.findViewById(R.id.dlabel);
			rowView.setTag(holder);

		}else{
			holder =(ViewHolder) rowView.getTag();
		}

		RowContainer rowContainer =rowContainerList.get(position);

		holder.textView.setText(rowContainer.label);
		holder.textView.setTag(position);
		holder.editText.setText(rowContainer.count+"");
		holder.checkBox.setTag(position);
		holder.checkBox.setChecked(rowContainer.checked);
		holder.button.setText(rowContainer.label);
		//Object[] objects = {position,holder};
		holder.button.setTag(position);//(objects);
		//if(rowContainer.submitted)holder.button.(R.color.orange);

		return rowView;
	}

	@Override
	public void onClick(View view) {
		/*Object[] objects = (Object[]) view.getTag();
		int position =(Integer) objects[0];
		ViewHolder holder = (ViewHolder) objects[1];*/
		int position = (Integer)view.getTag();
		RowContainer rowContainer = rowContainerList.get(position);
		Log.i("mirinda1", "" + ((Button) view).getText() + " : " + position);
		if(rowContainer.checked){
			for (RowContainer container : rowContainerList) {
				if (container.checked) {
					container.count = rowContainer.count;
					container.submitted = true;
					container.checked = false;
				}
			}
		}else{
			rowContainer.count=position;
			rowContainer.submitted = true;
		}
		notifyDataSetChanged();
	}
	@Override
	public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
		int position = (Integer) compoundButton.getTag();
		rowContainerList.get(position).checked=b;
	}
}
