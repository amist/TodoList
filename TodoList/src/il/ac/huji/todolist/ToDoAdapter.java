package il.ac.huji.todolist;

import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ToDoAdapter<E> extends ArrayAdapter<E> {
	public ToDoAdapter(Context context, int textViewResourceId, E[] objects) {
		super(context, textViewResourceId, objects);

	}

	public ToDoAdapter(Context context, int textViewResourceId, List<E> objects) {
		super(context, textViewResourceId, objects);
	}

	public View getView(final int position, final View convertView, final ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater inflater = LayoutInflater.from(getContext());
			v = inflater.inflate(R.layout.row, null);

		}
		if (v != null) {
			TodoRow row = (TodoRow) getItem(position);
			TextView title = (TextView) v.findViewById(R.id.txtTodoTitle);
			title.setText(row.getText());
			TextView date = (TextView) v.findViewById(R.id.txtTodoDueDate);
			date.setText(row.getDateString());
			
			Calendar c = Calendar.getInstance();
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);
			
			if (c.getTimeInMillis() > row.getDate().getTimeInMillis()) {
				title.setTextColor(Color.RED);
				date.setTextColor(Color.RED);
			} else {
				title.setTextColor(Color.BLACK);
				date.setTextColor(Color.BLACK);
			}
		}

		v.setOnLongClickListener(new android.view.View.OnLongClickListener() {

			public boolean onLongClick(View view) {
				Intent intent = new Intent(getContext(), ItemMenuActivity.class);
				TodoRow row = (TodoRow) getItem(position);
				intent.putExtra("title", row.getText());
				intent.putExtra("dueDate", row.getDateString());
				intent.putExtra("position", String.valueOf(position));
				intent.putExtra("id", row.getId());
				((Activity) getContext()).startActivityForResult(intent, 2);
				return true;
			}
		});
		return v;
	}

}
