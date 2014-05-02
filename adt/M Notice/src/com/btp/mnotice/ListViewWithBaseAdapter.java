package com.btp.mnotice;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class ListViewWithBaseAdapter extends Activity
{

	public String[] ListItems = new String[]{};

	public class codeLeanChapter
	{
		String header;
		String noticeID;
		String subject;
		String timestamp;
		String addressee;
		String text;
		String issuingAuthority;
		String category;
		String footer;
	}




	CodeLearnAdapter chapterListAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view_with_simple_adapter);


		chapterListAdapter = new CodeLearnAdapter();

		ListView codeLearnLessons = (ListView)findViewById(R.id.listView1);
		codeLearnLessons.setAdapter(chapterListAdapter);

		codeLearnLessons.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				codeLeanChapter chapter = chapterListAdapter.getCodeLearnChapter(arg2);



				// Launching new Activity on selecting single List Item
				Intent i = new Intent(getApplicationContext(), SingleNotice.class);
				
				
				String toDisplay = chapter.noticeID + ":" + chapter.timestamp + ":" + chapter.addressee + ":" + chapter.subject + ":" + chapter.text + ":" + chapter.issuingAuthority;


				// sending data to new activity
				i.putExtra("singleNoticeDetail", toDisplay);
				startActivity(i);





				Toast.makeText(ListViewWithBaseAdapter.this, chapter.noticeID,Toast.LENGTH_LONG).show();

			}
		});
	}


	public class CodeLearnAdapter extends BaseAdapter {

		List<codeLeanChapter> codeLeanChapterList = getDataForListView();
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return codeLeanChapterList.size();
		}

		@Override
		public codeLeanChapter getItem(int arg0) {
			// TODO Auto-generated method stub
			return codeLeanChapterList.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {

			if(arg1==null)
			{
				LayoutInflater inflater = (LayoutInflater) ListViewWithBaseAdapter.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				arg1 = inflater.inflate(R.layout.listitem, arg2,false);
			}

			TextView chapterName = (TextView)arg1.findViewById(R.id.textView1);
			TextView chapterDesc = (TextView)arg1.findViewById(R.id.textView2);

			codeLeanChapter chapter = codeLeanChapterList.get(arg0);

			chapterName.setText(chapter.header);
			chapterDesc.setText(chapter.subject);

			return arg1;
		}

		public codeLeanChapter getCodeLearnChapter(int position)
		{
			return codeLeanChapterList.get(position);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_view_with_simple_adapter, menu);
		return true;
	}

	public List<codeLeanChapter> getDataForListView()
	{

		ListItems = MyHTTPD.finalResult.split("#");

		List<codeLeanChapter> codeLeanChaptersList = new ArrayList<codeLeanChapter>();

		for(int i=0;i<10;i++)
		{
			String[] singleNotice = new String[]{};

			singleNotice = ListItems[i].split(":");

			codeLeanChapter chapter = new codeLeanChapter();




			chapter.header = singleNotice[0];
			chapter.noticeID = singleNotice[1];
			chapter.subject = singleNotice[2];
			chapter.timestamp = singleNotice[3];
			chapter.addressee = singleNotice[4];
			chapter.text = singleNotice[5];
			chapter.issuingAuthority = singleNotice[6];
			chapter.category = singleNotice[7];
			chapter.footer = singleNotice[8];




			codeLeanChaptersList.add(chapter);
		}

		return codeLeanChaptersList;

	}
}
