package il.ac.huji.todolist;

import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class MyDB {

	private MyDBHelper dbHelper;
	private SQLiteDatabase database;

	public final static String TABLE = "todo";
	public final static String ID = "_id";
	public final static String TITLE = "title";
	public final static String DATE = "due";

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
//		values.put(DATE, dueDate.toGMTString());
		values.put(DATE, dueDate.parse(dueDate.toGMTString()));
		Log.w("myDebug", "addItem Create of DB. dueDate.toGMTString() = " + dueDate.toGMTString());
		long id = database.insert(TABLE, null, values);

		ParseObject testObject = new ParseObject(TABLE);
		testObject.put(TITLE, title);
//		testObject.put(DATE, dueDate.toGMTString());
		testObject.put(DATE, dueDate.parse(dueDate.toGMTString()));
		testObject.put("SQLiteId", id);
		testObject.saveInBackground();

		return id;
	}

	public void removeItem(int id) {
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(TABLE);
		query.whereEqualTo("SQLiteId", id);
		query.findInBackground(new FindCallback<ParseObject>() {
			public void done(List<ParseObject> testList, ParseException e) {
				if (e == null) {
					Log.d("myDebug", "Retrieved " + testList.size() + " objects from parse.com");
					for (int i = 0; i < testList.size(); i++) {
						ParseObject tempTest = testList.get(i);
						tempTest.deleteInBackground();
					}
				} else {
					Log.d("myDebug", "Error: " + e.getMessage());
				}
			}
		});

		// ParseObject = toDelete = ParseObject.
//		ParseObject.createWithoutData(TABLE, String.valueOf(id)).deleteEventually();
		database.delete(TABLE, "_id=?", new String[] { Integer.toString(id) });
	}
}