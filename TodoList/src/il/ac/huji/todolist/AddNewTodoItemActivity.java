package il.ac.huji.todolist;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

public class AddNewTodoItemActivity extends Activity {

	private ArrayList<String> toDoList;
	private ToDoAdapter<String> adaptToDO;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_add_new_todo_item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// getMenuInflater().inflate(R.menu.todo_list_manager, menu);
		// return true;
		return false;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// switch (item.getItemId()) {
		// case R.id.menuItemAdd:
		// TextView addtext = (TextView) findViewById(R.id.edtNewItem);
		// String txt = addtext.getText().toString();
		// adaptToDO.add(txt);
		// adaptToDO.notifyDataSetChanged();
		// addtext.setText("");
		// }
		// return true;
		return false;
	}

	public void addItemToList(View view) {
		TextView addItemTextView = (TextView) findViewById(R.id.edtNewItem);
		String addItemText = addItemTextView.getText().toString();
		DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
		int year = datePicker.getYear();
		int month = datePicker.getMonth() + 1;
		int day = datePicker.getDayOfMonth();
		String dateString = day + "/" + month + "/" + year;
		Log.e("myDebug", "in addItemToList. text = " + addItemText + ", date = " + dateString);
		Intent returnIntent = new Intent();
		returnIntent.putExtra("text", addItemText);
		returnIntent.putExtra("date", dateString);
		setResult(RESULT_OK, returnIntent);
		finish();
	}

	public void selfDestruct(View view) {
		Log.e("myDebug", "in selfDestruct");
		finish();
	}

}
