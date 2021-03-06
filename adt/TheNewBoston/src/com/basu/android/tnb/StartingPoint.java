package com.basu.android.tnb;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartingPoint extends Activity {
	int counter;
	Button add, sub;
	TextView display;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_starting_point);

		// initialize the counter
		counter = 0;

		// get button and text view references
		add = (Button) findViewById(R.id.bAdd);
		sub = (Button) findViewById(R.id.bSub);
		display = (TextView) findViewById(R.id.tvDisplay);

		// setup on click listener for buttons
		add.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				counter++;
				updateDisplay(counter);
			}
		});

		sub.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				counter--;
				updateDisplay(counter);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.starting_point, menu);
		return true;
	}
	
	public void updateDisplay(int counter) {
		display.setText("Your total is " + counter);
	}

}
