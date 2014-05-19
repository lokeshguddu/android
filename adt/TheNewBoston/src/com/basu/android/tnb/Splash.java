package com.basu.android.tnb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		// set up thread and the timer
		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(2000);	// sleep for 2 seconds
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					Intent openStartingPoint = new Intent("com.basu.android.tnb.STARTINGPOINT");
					startActivity(openStartingPoint);
				}
			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();	// finish the splash activity
	}
}
