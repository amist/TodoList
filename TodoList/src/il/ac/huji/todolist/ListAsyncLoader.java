package il.ac.huji.todolist;

import java.util.Calendar;
import java.util.Date;

import android.database.Cursor;
import android.os.AsyncTask;

public class ListAsyncLoader extends AsyncTask<Void, TodoRow, Void> {

	private Cursor cursor;	
	private MyDB sqlite;
	private ToDoAdapter listAdapter;

	public ListAsyncLoader(MyDB _sqlite, ToDoAdapter _adapetr)
	{
		super();

		sqlite = _sqlite;
		listAdapter = _adapetr;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		listAdapter.clear();

	}


	@Override
	protected Void doInBackground(Void... params) {
		cursor = sqlite.getTableCursor();
		cursor.moveToFirst();
		while(!cursor.isAfterLast())
		{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			TodoRow item = new TodoRow(cursor.getString(1), new Date(cursor.getLong(2)), cursor.getInt(0));
			publishProgress(item);
			cursor.moveToNext();
		}
		return null;
	}


	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
	}

	@Override
	protected void onProgressUpdate(TodoRow... values) {
		super.onProgressUpdate(values);

		listAdapter.add(values[0]);
		listAdapter.notifyDataSetChanged();
	}

}
