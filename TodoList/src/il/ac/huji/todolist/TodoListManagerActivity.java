package il.ac.huji.todolist;

import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseAnalytics;
import com.parse.ParseUser;

public class TodoListManagerActivity extends Activity {

	private ArrayList<TodoRow> toDoList;
	private ToDoAdapter<TodoRow> adaptToDO;
	private MyDBHelper dbHelper;
	private MyDB db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todo_list_manager);
		toDoList = new ArrayList<TodoRow>();
		ListView list = (ListView) findViewById(R.id.lstTodoItems);

		adaptToDO = new ToDoAdapter<TodoRow>(this, android.R.layout.simple_list_item_1, toDoList);
		list.setAdapter(adaptToDO);
		
		Parse.initialize(this, "8LccucGCxH90FYy5v7bn9H59MqAnfmGP5aqvcl4R", "Rcz0YER1ZjCpN1up4NNitym1eDgM36DybjpDRo0z");
		ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();
		// Optionally enable public read access while disabling public write access.
		// defaultACL.setPublicReadAccess(true);
		ParseACL.setDefaultACL(defaultACL, true);

		db = new MyDB(getBaseContext());
		Cursor cursor = db.selectRecords();
		while(cursor.moveToNext()) {
			TodoRow row = new TodoRow();
			row.setText(cursor.getString(cursor.getColumnIndex("title")));
//			Date date = new Date(cursor.getString(cursor.getColumnIndex("due")));
			Date date = new Date(cursor.getLong(cursor.getColumnIndex("due")));
			row.setDateFromDate(date);
			int id = cursor.getInt(cursor.getColumnIndex("_id"));
			row.setId(id);
			adaptToDO.add(row);
			Log.w("myDebug", "onCreate of view. Added row: " + row.toString());
		}
		adaptToDO.notifyDataSetChanged();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.todo_list_manager, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menuItemAdd:
			Intent intent = new Intent(this, AddNewTodoItemActivity.class);
			startActivityForResult(intent, 1);
		}
		return true;
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (data == null)
			return;
		if (requestCode == 1) {
			if (resultCode == RESULT_OK) {
				String text = data.getStringExtra("title");
				Date date = (Date) data.getSerializableExtra("dueDate");
				TodoRow row = new TodoRow();
				row.setText(text);
				row.setDateFromDate(date);
				adaptToDO.add(row);
				adaptToDO.notifyDataSetChanged();
				db.addItem(text, date);
			}
			if (resultCode == RESULT_CANCELED) {
			}
		}
		if (requestCode == 2) {
			String text = data.getStringExtra("title");
			String date = data.getStringExtra("dueDate");
			String action = data.getStringExtra("action");
			int id = data.getIntExtra("id", -1);
			if (action.equals("delete")) {
				Integer i = 0;
				for (TodoRow row : toDoList) {
					if (i.toString().equals(data.getStringExtra("position"))) {
						adaptToDO.remove(row);
						adaptToDO.notifyDataSetChanged();
						db.removeItem(row.getId());
						return;
					}
					i++;
				}
			}
			if (action.equals("call")) {
				String number = text.substring(5);
				Intent dial = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
				startActivity(dial);
			}
		}
	}

}
