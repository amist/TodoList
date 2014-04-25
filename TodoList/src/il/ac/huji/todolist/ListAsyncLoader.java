package il.ac.huji.todolist;

import java.util.Date;

import android.database.Cursor;
import android.os.AsyncTask;

public class ListAsyncLoader extends AsyncTask<Void, TodoRow, Void> {

	private Cursor cursor;	
	private MyDB db;
	private ToDoAdapter adapter;

	public ListAsyncLoader(MyDB db, ToDoAdapter adapter)
	{
		super();
		this.db = db;
		this.adapter = adapter;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		adapter.clear();
	}

	@Override
	protected Void doInBackground(Void... params) {
		cursor = db.getTableCursor();
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
	protected void onProgressUpdate(TodoRow... items) {
		super.onProgressUpdate(items);
		adapter.add(items[0]);
		adapter.notifyDataSetChanged();
	}
}
