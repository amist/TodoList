package il.ac.huji.todolist;

import java.util.ArrayList;
import java.util.Arrays;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.Resources;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class TodoListManagerActivity extends Activity {

	private ArrayList<String> toDoList;
	private ToDoAdapter<String> adaptToDO;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todo_list_manager);

		Resources res = getResources();

		toDoList = new ArrayList<String>();
		ListView list = (ListView) findViewById(R.id.lstTodoItems);

		adaptToDO = new ToDoAdapter<String>(this, android.R.layout.simple_list_item_1, toDoList);

		list.setAdapter(adaptToDO);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.todo_list_manager, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menuItemAdd:
			TextView addtext = (TextView) findViewById(R.id.edtNewItem);
			String txt = addtext.getText().toString();
			adaptToDO.add(txt);
			adaptToDO.notifyDataSetChanged();
			addtext.setText("");
		}
		return true;
	}

}
