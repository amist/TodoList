package il.ac.huji.todolist;

import java.util.List;
import java.util.Objects;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
			TextView title = (TextView) v.findViewById(R.id.title);
			title.setText(getItem(position).toString());
			if (position % 2 == 0)
				title.setTextColor(Color.RED);
			else
				title.setTextColor(Color.BLUE);
		}

		v.setOnLongClickListener(new android.view.View.OnLongClickListener() {

			public boolean onLongClick(View view) {
				AlertDialog.Builder builder = new AlertDialog.Builder(getContext()).setNegativeButton("Delete Item", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						remove(getItem(position));
					}
				});
				TextView title = new TextView(getContext());
				title.setText(getItem(position).toString());
				title.setBackgroundColor(Color.DKGRAY);
				title.setPadding(10, 10, 10, 10);
				title.setGravity(Gravity.CENTER);
				title.setTextColor(Color.WHITE);
				title.setTextSize(20);
				builder.setCustomTitle(title);
				AlertDialog delDialog = builder.create();

				delDialog.show();
				return true;
			}
		});
		return v;
	}

}
