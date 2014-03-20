package il.ac.huji.todolist;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.TextView;

public class AddNewTodoItemActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_add_new_todo_item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return false;
	}

	public void addItemToList(View view) {
		TextView addItemTextView = (TextView) findViewById(R.id.edtNewItem);
		String addItemText = addItemTextView.getText().toString();
		DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
		cal.set(Calendar.MONTH, datePicker.getMonth());
		cal.set(Calendar.YEAR, datePicker.getYear());
		Date date = new Date(cal.getTimeInMillis());
		
		Intent returnIntent = new Intent();
		returnIntent.putExtra("title", addItemText);
		returnIntent.putExtra("dueDate", date);
		setResult(RESULT_OK, returnIntent);
		finish();
	}

	public void selfDestruct(View view) {
		Log.e("myDebug", "in selfDestruct");
		finish();
	}

}
