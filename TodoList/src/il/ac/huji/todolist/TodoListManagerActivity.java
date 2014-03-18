package il.ac.huji.todolist;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
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
		if (requestCode == 1) {

			if (resultCode == RESULT_OK) {
				String text = data.getStringExtra("text");
				String date = data.getStringExtra("date");
				Log.e("myDebug", "onActivityResult. text = " + text + ", date = " + date);
				TodoRow row = new TodoRow();
				row.setText(text);
				row.setDate(date);
				adaptToDO.add(row);
				adaptToDO.notifyDataSetChanged();
			}
			if (resultCode == RESULT_CANCELED) {
			}
		}
	}

}
