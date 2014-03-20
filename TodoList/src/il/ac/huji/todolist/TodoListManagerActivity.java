package il.ac.huji.todolist;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

public class TodoListManagerActivity extends Activity {

	private ArrayList<TodoRow> toDoList;
	private ToDoAdapter<TodoRow> adaptToDO;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todo_list_manager);
		toDoList = new ArrayList<TodoRow>();
		ListView list = (ListView) findViewById(R.id.lstTodoItems);

		adaptToDO = new ToDoAdapter<TodoRow>(this, android.R.layout.simple_list_item_1, toDoList);

		list.setAdapter(adaptToDO);

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
				String date = data.getStringExtra("dueDate");
				Log.e("myDebug", "onActivityResult. text = " + text + ", date = " + date);
				TodoRow row = new TodoRow();
				row.setText(text);
				row.setDateFromString(date);
				adaptToDO.add(row);
				adaptToDO.notifyDataSetChanged();
			}
			if (resultCode == RESULT_CANCELED) {
			}
		}
		if (requestCode == 2) {
			String text = data.getStringExtra("title");
			String date = data.getStringExtra("dueDate");
			String action = data.getStringExtra("action");
			Log.e("myDebug", "onActivityResult, after deletion. text = " + text + ", date = " + date);
			if (action.equals("delete")) {
				for (TodoRow row : toDoList) {
					if (row.toString().equals(text + " - " + date))
						adaptToDO.remove(row);
					adaptToDO.notifyDataSetChanged();
					return;
				}
			}
			if (action.equals("call")) {
				String number = text.substring(5);
				Log.e("myDebug", "number to call: |" + number + "|");
				Intent dial = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
				startActivity(dial);
			}
			// toDoList.remove();
		}
	}

}
