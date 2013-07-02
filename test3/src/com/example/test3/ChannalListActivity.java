package com.example.test3;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class ChannalListActivity extends ListActivity {
	public final static String EXTRA_MESSAGE = "com.example.test3.MESSAGE";
	ArrayAdapter<String> aa;
	//ListView listView1;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_display_message);
		
		///////////////////////////////////////////////////////////////////////////////
		
		aa = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1);
		//aa.add("kjn");
		
		//listView1 = (ListView)findViewById(R.id.listView1);
		/*InputStream is = null;
		String result = "";
		
		//the year data to send
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("year","1980"));
		 
		//http post
		try{
		        HttpClient httpclient = new DefaultHttpClient();
		        HttpPost httppost = new HttpPost("http://192.168.177.1/tvserver/tvser.php");
		        System.out.println("ha haaaaaaa"+httppost.toString());
		        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		        System.out.println("set entity");
		        HttpResponse response = httpclient.execute(httppost);
		        System.out.println("resssssssssss"+response.toString());
		        HttpEntity entity = response.getEntity();
		        System.out.println("entity"+entity.toString());
		        is = entity.getContent();
		        System.out.println("huuuuuuuuu"+is.toString());
		}catch(Exception e){
		        Log.e("log_tag", "Error in http connection "+e.toString());
		}
		//convert response to string
		try{
		        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
		        StringBuilder sb = new StringBuilder();
		        String line ="";
		        while ((line = reader.readLine()) != null) {
		        		System.out.println("line"+line);
		                sb.append(line + "\n");
		        }
		        is.close();
		 
		        result=sb.toString();
		        result =  result.trim();
		        result = result.substring(3);
		        System.out.println("result"+result);
		}catch(Exception e){
		        Log.e("log_tag", "Error converting result "+e.toString());
		}
		 
		//parse json data
		try{
				System.out.println("result"+result.toString());
		        JSONArray jArray = new JSONArray(result);
		        for(int i=0;i<jArray.length();i++){
		                JSONObject json_data = jArray.getJSONObject(i);
		                Log.i("log_tag","id: "+json_data.getInt("id")+
		                        ", name: "+json_data.getString("name")
		                );
		                
		                aa.add(json_data.getString("name"));
		        }
		
		        listView1.setAdapter(aa);
		}catch(JSONException e){
		        Log.e("log_tag", "Error parsing data "+e.toString());
		}
		*/
		
		setListAdapter(aa);
		
		readServer();
		
		
		
		
		
		
		
		///////////////////////////////////////////////////////////////////////////////
		/*Intent intent = getIntent();
	    String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

	    // Create the text view
	    TextView textView = new TextView(this);
	    textView.setTextSize(40);
	    textView.setText(message);

	    // Set the text view as the activity layout
	    setContentView(textView);*/
		
		//setContentView(R.layout.activity_display_message);
		// Show the Up button in the action bar.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
		//setupActionBar();
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_message, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
	super.onListItemClick(l, v, position, id);
	//System.out.println("selected"+l+"view"+v+"posi"+position+"id"+id);
	String text = " position:" + position + "  " + aa.getItem(position);
	System.out.println("selected"+text);
	
	Intent intent = new Intent(this, CahannalSchedule.class);
	//EditText editText = (EditText) findViewById(R.id.editText1);
	String message = aa.getItem(position).toString();
	intent.putExtra(EXTRA_MESSAGE, message);
	 startActivity(intent);
	//selection.setText(text);
	}
	
	
	public void readServer(){
		//ArrayAdapter<String> aa = null;
		//aa.add("kjn");
		//ListView listView1;
		//listView1 = (ListView)findViewById(R.id.listView1);
		InputStream is = null;
		String result = "";
		//the year data to send
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("year","1980"));
		 
		//http post
		try{
		        HttpClient httpclient = new DefaultHttpClient();
		        HttpPost httppost = new HttpPost("http://kavithatalent.lk/ebridge/mycall/tvserver/tvser.php");
		        System.out.println("ha haaaaaaa"+httppost.toString());
		        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		        System.out.println("set entity");
		        HttpResponse response = httpclient.execute(httppost);
		        System.out.println("resssssssssss"+response.toString());
		        HttpEntity entity = response.getEntity();
		        System.out.println("entity"+entity.toString());
		        is = entity.getContent();
		        System.out.println("huuuuuuuuu"+is.toString());
		}catch(Exception e){
		        Log.e("log_tag", "Error in http connection "+e.toString());
		}
		//convert response to string
		try{
		        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
		        StringBuilder sb = new StringBuilder();
		        String line ="";
		        while ((line = reader.readLine()) != null) {
		        		System.out.println("line"+line);
		                sb.append(line + "\n");
		        }
		        is.close();
		 
		        result=sb.toString();
		        result =  result.trim();
		        result = result.substring(3);
		        System.out.println("result"+result);
		}catch(Exception e){
		        Log.e("log_tag", "Error converting result "+e.toString());
		}
		 
		//parse json data
		try{
				System.out.println("result"+result.toString());
		        JSONArray jArray = new JSONArray(result);
		        for(int i=0;i<jArray.length();i++){
		                JSONObject json_data = jArray.getJSONObject(i);
		                Log.i("log_tag","id: "+json_data.getInt("id")+
		                        ", name: "+json_data.getString("name")
		                );
		                
		                aa.add(json_data.getString("name"));
		        }
		
		        //listView1.setAdapter(aa);
		}catch(JSONException e){
		        Log.e("log_tag", "Error parsing data "+e.toString());
		}
	}
	
	

}
