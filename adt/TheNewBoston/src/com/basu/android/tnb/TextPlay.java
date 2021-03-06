package com.basu.android.tnb;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class TextPlay extends Activity implements View.OnClickListener {

	Button checkCommand;
	ToggleButton passToggle;
	EditText input;
	TextView display;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text);

		// get the references
		getreferences();

		// set up the on click listener on toggle button
		passToggle.setOnClickListener(this);
		checkCommand.setOnClickListener(this);
		
	}

	private void getreferences() {
		// TODO Auto-generated method stub

		checkCommand = (Button) findViewById(R.id.bResults);
		passToggle = (ToggleButton) findViewById(R.id.tbPassword);
		input = (EditText) findViewById(R.id.etCommands);
		display = (TextView) findViewById(R.id.tvResults);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bResults:
			String check = input.getText().toString();
			check = check.toLowerCase();
			if (check.contentEquals("left")) {
				display.setGravity(Gravity.LEFT);
			} else if (check.contentEquals("center")) {
				display.setGravity(Gravity.CENTER);
			} else if (check.contentEquals("right")) {
				display.setGravity(Gravity.RIGHT);
			} else if (check.contentEquals("blue")) {
				display.setTextColor(Color.BLUE);
			} else if (check.contentEquals("loki")) {
				Random randomNumber = new Random();
				display.setText("Loki");
				display.setTextSize(randomNumber.nextInt(50));
				display.setTextColor(Color.rgb(randomNumber.nextInt(265),
						randomNumber.nextInt(265), randomNumber.nextInt(265)));
				switch (randomNumber.nextInt(3)) {
				case 0:
					display.setGravity(Gravity.LEFT);
					break;
				case 1:
					display.setGravity(Gravity.CENTER);
					break;
				case 2:
					display.setGravity(Gravity.RIGHT);
					break;
				}

			} else {
				display.setText("invalid");
				display.setGravity(Gravity.CENTER);
				display.setTextColor(Color.BLACK);
			}
			break;
		case R.id.tbPassword:
			if (passToggle.isChecked()) {
				input.setInputType(InputType.TYPE_CLASS_TEXT
						| InputType.TYPE_TEXT_VARIATION_PASSWORD);
			} else {
				input.setInputType(InputType.TYPE_CLASS_TEXT);
			}
			break;
		}

	}
}
