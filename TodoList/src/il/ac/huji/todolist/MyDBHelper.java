package il.ac.huji.todolist;

import java.util.Date;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDBHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "TodoList";

	private static final int DATABASE_VERSION = 3;

	// Database creation sql statement
//	private static final String DATABASE_CREATE = "create table TodoList ( _id integer primary key,name text not null);";
	private static final String DATABASE_CREATE = "create table TodoList (title text ,dueDate text);";

	public MyDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		Log.w("myDebug", "Ctor of DB");
	}

	// Method is called during creation of the database
	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
		Log.w("myDebug", "onCreate of DB");
	}

	// Method is called during an upgrade of the database,
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		Log.w("myDebug", "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
		database.execSQL("DROP TABLE IF EXISTS TodoList");
		onCreate(database);
	}
}