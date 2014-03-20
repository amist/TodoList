package il.ac.huji.todolist;

import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
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
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH) + 1;
			int day = c.get(Calendar.DAY_OF_MONTH);
			String dateString = day + "/" + month + "/" + year;
			Log.e("myDebug", "in ToDoAdapter getView. now date = " + dateString);
			Log.e("myDebug", "in ToDoAdapter getView. row date = " + row.getDate());
			
			if (c.getTimeInMillis() > row.getDate().getTimeInMillis()) {
//			if (position % 2 == 0) {
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
				intent.putExtra("date", row.getDate());
				intent.putExtra("position", position);
				((Activity) getContext()).startActivityForResult(intent, 2);
//				AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//				builder.setNegativeButton("Delete Item", new DialogInterface.OnClickListener() {
//					public void onClick(DialogInterface dialog, int which) {
//						remove(getItem(position));
//					}
//				});
//				if (isCallItem(getItem(position))) {
//					TodoRow row = (TodoRow) getItem(position);
//					String text = row.getText();
//					builder.setPositiveButton(text, new DialogInterface.OnClickListener() {
//						public void onClick(DialogInterface dialog, int which) {
//							Log.e("myDebug", "clicked on call button of item " + getItem(position).toString());
//							TodoRow row = (TodoRow) getItem(position);
//							String number = row.getText().substring(5);
//							Log.e("myDebug", "number to call: |" + number + "|");
//							Intent dial = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
//							getContext().startActivity(dial);
//						}
//					});
//				}
//				TextView title = new TextView(getContext());
//				title.setText(getItem(position).toString());
//				title.setBackgroundColor(Color.DKGRAY);
//				title.setPadding(10, 10, 10, 10);
//				title.setGravity(Gravity.CENTER);
//				title.setTextColor(Color.WHITE);
//				title.setTextSize(20);
//				builder.setCustomTitle(title);
//				AlertDialog delDialog = builder.create();
//
//				delDialog.show();
				return true;
			}
		});
		return v;
	}
	
	public boolean isCallItem(E item) {
		String text = item.toString();
		if (text.startsWith("call"))
			return true;
		return false;
	}

}
