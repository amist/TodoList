package il.ac.huji.tipcalculator;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class TipCalculatorActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tip_calculator);
		final EditText billAmountTxt = (EditText) findViewById(R.id.edtBillAmount);
		final CheckBox roundTipCheck = (CheckBox) findViewById(R.id.chkRound);
		final Button calculateButton = (Button) findViewById(R.id.btnCalculate);
		final TextView tipResultText = (TextView) findViewById(R.id.txtTipResult);

		calculateButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String sumStr = billAmountTxt.getText().toString();
				if (sumStr.length() == 0) {
					tipResultText.setText("Please insert an amount");
//					Log.d("myDebug", "going to return because of an empty string");
					return;
				}
				float sum = Float.parseFloat(sumStr);
				sum = sum * 12 / 100;
				if (roundTipCheck.isChecked()) {
					sum = Math.round(sum);
				}
				tipResultText.setText("Tip: $" + Float.toString(sum));
			};
		});
	}

}
