package com.btp.mnotice;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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
		
		
		final EditText header = (EditText) findViewById(R.id.editHeader);
		final EditText noticeID = (EditText) findViewById(R.id.editNoticeID);
		final EditText subject = (EditText) findViewById(R.id.editSubject);
		
		final EditText addressee = (EditText) findViewById(R.id.editAddressee);
		final EditText text = (EditText) findViewById(R.id.editText);
		final EditText authority = (EditText) findViewById(R.id.editIssuingAuthority);
		final EditText category = (EditText) findViewById(R.id.editCategory);
		final EditText footer = (EditText) findViewById(R.id.editFooter);

		
		Button add = (Button)findViewById(R.id.add);
		add.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				
				String hdr = header.getText().toString();
				String ntc = noticeID.getText().toString();
				String sub = subject.getText().toString();
				String adr = addressee.getText().toString();
				String txt = text.getText().toString();
				String ath = authority.getText().toString();
				String cat = category.getText().toString();
				String ftr = footer.getText().toString();
				
				Calendar currentDate = Calendar.getInstance(); //Get the current date
				SimpleDateFormat formatter= new SimpleDateFormat("yyyy/MMM/dd"); //format it as per your requirement
				String timestamp = formatter.format(currentDate.getTime());
				
				String updateStr = "\n" + hdr + ":" + ntc + ":" + sub + ":" + timestamp + ":" + adr + ":" + txt + ":" + ath + ":" + cat + ":" + ftr + "\n" ;
				
				
				
				update(updateStr);
				
				header.setText("");
				noticeID.setText("");
				subject.setText("");	
				addressee.setText("");
				text.setText("");
				authority.setText("");
				category.setText("");
				footer.setText("");
				
				
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
			File file = new File(Environment.getExternalStorageDirectory().getPath() + "/inputfile2.txt");
			
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
