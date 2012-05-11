package kz.mirinda.trainer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import kz.mirinda.trainer.impl.Results;

import java.util.List;

/**
 * new class
 *
 * @author mirinda
 */
public class MyWorkoutAdapter extends ArrayAdapter{
	private Results results;
	private Context context;
	private List objects;
	public MyWorkoutAdapter(Context context, int resource, int textViewResourceId, List objects,Results results) {
		super(context, resource, textViewResourceId, objects);
		this.results=results;
		this.context=context;
		this.objects=objects;
	}

	class WHolder{
		TextView textView;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		WHolder holder;
		if(rowView == null){
			LayoutInflater layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = layoutInflater.inflate(R.layout.workoutrow,parent,false);
			holder = new WHolder();
			holder.textView =(TextView) rowView.findViewById(R.id.wlabel);
			rowView.setTag(holder);
		}else{
			holder = (WHolder) rowView.getTag();
		}
		holder.textView.setText((String)objects.get(position));
		return rowView;
	}
}