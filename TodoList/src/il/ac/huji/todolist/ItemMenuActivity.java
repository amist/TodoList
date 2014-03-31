package il.ac.huji.todolist;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class ItemMenuActivity extends Activity implements OnClickListener {
	
	protected String todoTitle = "";
	protected String todoDate = "";
	protected String position = "";
	protected int id = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_item_menu);
		Bundle b = getIntent().getExtras();
		String text = b.getString("title");
		this.todoTitle = text;
		String date = b.getString("dueDate");
		this.todoDate = date;
		this.position = b.getString("position");
		this.id = b.getInt("id");
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return false;
	}

	public void selfDestruct(View view) {
		finish();
	}
	
	@SuppressLint("DefaultLocale")
	public boolean isCallText(String text) {
		if (text.startsWith("Call "))
			return true;
		return false;
	}

	@Override
	public void onClick(View v) {
		Intent returnIntent = new Intent();
		returnIntent.putExtra("title", this.todoTitle);
		returnIntent.putExtra("dueDate", this.todoDate);
		returnIntent.putExtra("position", this.position);
		returnIntent.putExtra("id", this.id);
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
