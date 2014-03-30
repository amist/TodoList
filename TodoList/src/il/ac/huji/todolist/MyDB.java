package il.ac.huji.todolist;

import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MyDB {

	private MyDBHelper dbHelper;
	private SQLiteDatabase database;

	public final static String TABLE = "TodoList"; // name of table
	public final static String TITLE = "title"; // id value for employee
	public final static String DATE = "dueDAte"; // name of employee

	/**
	 * 
	 * @param context
	 */
	public MyDB(Context context) {
		dbHelper = new MyDBHelper(context);
		database = dbHelper.getWritableDatabase();
	}

	public long createRecords(String title, String dueDate) {
		ContentValues values = new ContentValues();
		values.put(TITLE, title);
		values.put(DATE, dueDate);
		return database.insert(TABLE, null, values);
	}

	public Cursor selectRecords() {
		String[] cols = new String[] { TITLE, DATE };
		Cursor mCursor = database.query(true, TABLE, cols, null, null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();

		}
		return mCursor; // iterate to get each value.
	}
	
	public long addItem(String title, Date dueDate) {
		ContentValues values = new ContentValues();
		values.put(TITLE, title);
		values.put(DATE, dueDate.toGMTString());
		Log.w("myDebug", "addItem Create of DB. dueDate.toGMTString() = " + dueDate.toGMTString());
		return database.insert(TABLE, null, values);
	}
}