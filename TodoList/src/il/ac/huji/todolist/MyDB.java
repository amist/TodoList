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

	public final static String TABLE = "todo";
	public final static String ID = "_id";
	public final static String TITLE = "title";
	public final static String DATE = "dueDAte";

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
		String[] cols = new String[] { ID, TITLE, DATE };
		Cursor mCursor = database.query(true, TABLE, cols, null, null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();

		}
		return mCursor;
	}
	
	public long addItem(String title, Date dueDate) {
		ContentValues values = new ContentValues();
		values.put(TITLE, title);
		values.put(DATE, dueDate.toGMTString());
		Log.w("myDebug", "addItem Create of DB. dueDate.toGMTString() = " + dueDate.toGMTString());
		return database.insert(TABLE, null, values);
	}

	public void removeItem(int id) {
		database.delete(TABLE, "_id=?", new String[]{Integer.toString(id)});
	}
}