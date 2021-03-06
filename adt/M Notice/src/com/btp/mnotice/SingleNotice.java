package com.btp.mnotice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class SingleNotice extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
        this.setContentView(R.layout.detailed_view);
        
        Intent i = getIntent();
        
        // getting attached intent data
        String notice = i.getStringExtra("singleNoticeDetail");
        
		String[] singleNotice = new String[]{};

		singleNotice = notice.split(":");
        
		
		//String toDisplay = chapter.noticeID + ":" + chapter.timestamp + ":" + chapter.addressee + ":" + chapter.subject + ":" + chapter.text + ":" + chapter.issuingAuthority;

		
        TextView noticeID = (TextView) findViewById(R.id.noticeID);
        noticeID.setText(singleNotice[0]);

        TextView timestamp = (TextView) findViewById(R.id.timestamp);
        timestamp.setText(singleNotice[1]);
        
        
        TextView addressee = (TextView) findViewById(R.id.addressee);
        addressee.setText(singleNotice[2]);
        
        
        TextView subject = (TextView) findViewById(R.id.subject);
        subject.setText(singleNotice[3]);
        
        
		
        TextView text = (TextView) findViewById(R.id.text);
        text.setText(singleNotice[4]);
        text.setMovementMethod(new ScrollingMovementMethod());
        
        
        TextView issuingAuthority = (TextView) findViewById(R.id.issuingAuthority);
        issuingAuthority.setText(singleNotice[5]);
	}

}
