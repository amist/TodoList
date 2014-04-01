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
import com.parse.ParseACL;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class MyDB {

	private MyDBHelper dbHelper;
	private SQLiteDatabase database;

	private final static String TABLE = "todo";
	private final static String ID = "_id";
	private final static String TITLE = "title";
	private final static String DATE = "due";
	private ParseACL defaultACL = new ParseACL();

	/**
	 * 
	 * @param context
	 */
	public MyDB(Context context) {
		dbHelper = new MyDBHelper(context);
		database = dbHelper.getWritableDatabase();
		
		ParseUser.enableAutomaticUser();
//		ParseACL defaultACL = new ParseACL();
		// Optionally enable public read access while disabling public write access.
		// defaultACL.setPublicReadAccess(true);
		ParseACL.setDefaultACL(defaultACL, true);
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
		values.put(DATE, dueDate.parse(dueDate.toGMTString()));
		Log.w("myDebug", "addItem Create of DB. dueDate.toGMTString() = " + dueDate.toGMTString());
		long id = database.insert(TABLE, null, values);

		ParseObject todoObject = new ParseObject(TABLE);
		todoObject.put(TITLE, title);
		todoObject.put(DATE, dueDate.parse(dueDate.toGMTString()));
		todoObject.put("SQLiteId", id);
		todoObject.put("user", ParseUser.getCurrentUser());
		todoObject.setACL(new ParseACL(ParseUser.getCurrentUser()));
		todoObject.saveInBackground();

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

		database.delete(TABLE, "_id=?", new String[] { Integer.toString(id) });
	}
}