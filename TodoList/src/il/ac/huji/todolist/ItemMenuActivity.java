package il.ac.huji.todolist;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

public class ItemMenuActivity extends Activity implements OnClickListener {
	
	protected String todoTitle = "";
	protected String todoDate = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_item_menu);
		Bundle b = getIntent().getExtras();
//		Log.e("myDebug", "in onCreate of ItemMenuActivity. title = " + b.getString("title"));
		String text = b.getString("title");
		this.todoTitle = text;
		String date = b.getString("dueDate");
		this.todoDate = date;
		TextView title = (TextView) findViewById(R.id.itemMenuTitle);
		title.setText(text);
		Button call = (Button) findViewById(R.id.menuItemCall);
		call.setOnClickListener(this);
		call.setText(text);
		if (isCallText(text)) {
			call.setVisibility(View.VISIBLE);
		} else {
			call.setVisibility(View.GONE);
		}
		Button delete = (Button) findViewById(R.id.menuItemDelete);
		delete.setOnClickListener(this);
	}
	
//	public void onDelete() {
//		Intent returnIntent = new Intent();
//		returnIntent.putExtra("text", "some text");
//		setResult(RESULT_OK, returnIntent);
//		finish();
//	}

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

	// public void addItemToList(View view) {
	// TextView addItemTextView = (TextView) findViewById(R.id.edtNewItem);
	// String addItemText = addItemTextView.getText().toString();
	// DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
	// int year = datePicker.getYear();
	// int month = datePicker.getMonth() + 1;
	// int day = datePicker.getDayOfMonth();
	// String dateString = day + "/" + month + "/" + year;
	// Log.e("myDebug", "in addItemToList. text = " + addItemText + ", date = "
	// + dateString);
	// Intent returnIntent = new Intent();
	// returnIntent.putExtra("text", addItemText);
	// returnIntent.putExtra("date", dateString);
	// setResult(RESULT_OK, returnIntent);
	// finish();
	// }

	public void selfDestruct(View view) {
		Log.e("myDebug", "in selfDestruct");
		finish();
	}
	
	public boolean isCallText(String text) {
		if (text.startsWith("Call "))
			return true;
		return false;
	}

	@Override
	public void onClick(View v) {
		Log.e("myDebug", "in onClick");
		Log.e("myDebug", "pressed delete");
		Intent returnIntent = new Intent();
		returnIntent.putExtra("title", this.todoTitle);
		returnIntent.putExtra("dueDate", this.todoDate);
		if (v.getId()==R.id.menuItemDelete) {
			returnIntent.putExtra("action", "delete");
		}
		if (v.getId()==R.id.menuItemCall) {
			returnIntent.putExtra("action", "call");
		}
		setResult(RESULT_OK, returnIntent);
		finish();
	}

}
