package com.btp.mnotice;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Admin extends Activity {
	
	final Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin);
		
		
		final EditText from = (EditText) findViewById(R.id.editFrom);
		final EditText header = (EditText) findViewById(R.id.editHeader);
		final EditText detail = (EditText) findViewById(R.id.editDetail);

		
		Button add = (Button)findViewById(R.id.add);
		add.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				
				String fromStr = from.getText().toString();
				String headerStr = header.getText().toString();
				String detailStr = detail.getText().toString();
				
				String updateStr = "\n" + fromStr + ":" + headerStr + ":" + detailStr + "\n" ;
				
				update(updateStr);
				
				from.setText("");
				header.setText("");
				detail.setText("");
				
				
				//	alert box
				
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						context);
		 
					// set title
					alertDialogBuilder.setTitle("New Notice added");
		 
					// set dialog message
					alertDialogBuilder
						.setMessage("Press Back to add more.")
						.setCancelable(true);
		 
						// create alert dialog
						AlertDialog alertDialog = alertDialogBuilder.create();
		 
						// show it
						alertDialog.show();
				
				
				
				
			}
		});	
		
		
	}
	
	public void update(String str)
	{
		try
		{
			File file = new File(Environment.getExternalStorageDirectory().getPath() + "/inputfile1.txt");
			
			if(!file.exists())
			{
				file.createNewFile();
			}
			
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true); 
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(str);
			bw.close();
		}
		catch(Exception e)
		{
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.admin, menu);
		return true;
	}

}
