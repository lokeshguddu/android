package com.btp.mnotice;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ListViewWithBaseAdapter extends BaseAdapter {

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return codeLearnChapterList.size();
	}

	@Override
	public codeLearnChapter getItem(int position) {
		// TODO Auto-generated method stub
		return codeLearnChapterList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
    public class codeLearnChapter
    {
        String chapterName;
        String chapterDescription;
    }
    
    public List<codeLearnChapter> getDataForListView()
    {
        List<codeLearnChapter> codeLearnChaptersList = new ArrayList<codeLearnChapter>();

        for(int i=0;i<10;i++)
        {

            codeLearnChapter chapter = new codeLearnChapter();
            chapter.chapterName = "Chapter "+i;
            chapter.chapterDescription = "This is description for chapter "+i;
            codeLearnChaptersList.add(chapter);
        }
        return codeLearnChaptersList;
    }
    
    List<codeLearnChapter> codeLearnChapterList = getDataForListView();
    
    LayoutInflater inflater = (LayoutInflater) ListViewWithBaseAdapter.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

}
