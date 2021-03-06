package com.btp.mnotice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.Toast;

public class MyHTTPD extends NanoHTTPD 
{
	InputStream data;
	int counter ;
	Activity c;
	WifiManager wifi;
	String word = "";
	String my_ip="";
	String clientip="";
	String query="";

	public String my_path="";
	public static Charset charset = Charset.forName("UTF-8");
	public static CharsetEncoder encoder = charset.newEncoder();
	public static int count = 0 ; 
	public static String client_list="";
	public static String filename="default";
	public static String[] max_cpu,min_cpu,ram;
	public static ArrayList<HashMap<String, String>> song_list = new ArrayList<HashMap<String, String>>();

	static String finalResult = "";

	public MyHTTPD(Activity a, String ipaddr, String path) throws IOException
	{
		super(ipaddr, 8765);
		c = a;
		my_ip = ipaddr;
		my_path = path;
	}

	@Override
	public Response serve(String uri, Method method, Map<String, String> header, final Map<String, String> parms, Map<String, String> files)
	{
		try 
		{
			if(parms.get("getinfo") != null)
			{
				counter=0;
				clientip=parms.get("clientip");
				query=parms.get("query");
				
				c.runOnUiThread(new Runnable() 
				{
					@SuppressWarnings("deprecation")
					public void run()
					{
						try 
						{
							Toast.makeText(c,"received notice query for "+query, Toast.LENGTH_LONG).show();
							new sendinfotoclient(clientip,query).execute();
						}
						catch(Exception e)
						{

						}
					}
				});}
			if(parms.get("query_return") != null)
			{
				final String result=parms.get("result");
				final String resultListView=parms.get("resultListView");
				final String totalResultFound=parms.get("totalResultFound");
				
				c.runOnUiThread(new Runnable()
				{
					@SuppressWarnings("deprecation")
					public void run()
					{
						try 
						{
							finalResult = "";
							finalResult = finalResult.concat(resultListView);
							
							if(finalResult.length() == 0)
								Toast.makeText(c,"Empty Search Result", Toast.LENGTH_LONG).show();
							if(finalResult.length() > 0)
								Toast.makeText(c,"Total Result Found : "+ totalResultFound, Toast.LENGTH_LONG).show();
							
							MainActivity.text.setText("Total Result Found : "+ totalResultFound + "\n" +result);
							SearchActivity.text.setText("Total Result Found : "+ totalResultFound + "\n" +result);
							
						}
						
						catch(Exception e)
						{

						}
					}
				});}
		} 
		catch (Exception e) 
		{
			
		}
		return null;
	}
	
	public class sendinfotoclient extends AsyncTask<String, String, String> 
	{
		String clientip="";
		String query="";
		
		public sendinfotoclient(String clientip11,String query11)
		{
			clientip=clientip11;
			query=query11;
		}
		
		protected void onPreExecute() 
		{
			Toast.makeText(c, "Sending..", Toast.LENGTH_LONG).show();
		}

		protected void onPostExecute(String result)
		{

		}
		
		@Override
		protected String doInBackground(String... arg0)
		{
			String info="";
			String infoListView="";
			int totalResultFound = 0;
			String totalResultFoundStr = ""; 
			
			try
			{
				File set1 = new File(Environment.getExternalStorageDirectory().getPath() + "/inputfile.txt");		
				if(set1.canRead())
				{
					BufferedReader buffer1 = new BufferedReader(new FileReader(set1));
					String l = "";
					while ((l = buffer1.readLine()) != null) 
					{
						String[] split=l.split(":");
						if(split[0].contentEquals(query))
						{
							totalResultFound++;
							info = info + query +  " : "+ split[1]+"\n";
							infoListView = infoListView + query  + " : "+ split[1] +":" + split[2] +"#";
						}
					}
					totalResultFoundStr = ""+totalResultFound;
					buffer1.close();
				}
			}
			catch(Exception e)
			{

			}
			try
			{
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost("http://"+clientip+":8765");
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
				nameValuePairs.add(new BasicNameValuePair("query_return", "1"));
				nameValuePairs.add(new BasicNameValuePair("result",info));
				nameValuePairs.add(new BasicNameValuePair("resultListView",infoListView));
				nameValuePairs.add(new BasicNameValuePair("totalResultFound",totalResultFoundStr));
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				httpclient.execute(httppost);
			}
			catch(Exception e)
			{

			}
			return clientip;
		}
	}
}