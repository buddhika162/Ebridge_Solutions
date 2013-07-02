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
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class CahannalSchedule extends ListActivity {

	ArrayAdapter<String> aa;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
	    String message = intent.getStringExtra(ChannalListActivity.EXTRA_MESSAGE);
	    aa = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1);
	    // Create the text view
	    //TextView textView = new TextView(this);
	    //textView.setTextSize(40);
	    //textView.setText(message);
		//setContentView(textView);
		// Show the Up button in the action bar.
		//setupActionBar();
	    setListAdapter(aa);
		
		readServer(message);
		
	    
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
		getMenuInflater().inflate(R.menu.cahannal_schedule, menu);
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
	
	
	
	
	public void readServer(String channal){
		//ArrayAdapter<String> aa = null;
		//aa.add("kjn");
		//ListView listView1;
		//listView1 = (ListView)findViewById(R.id.listView1);
		InputStream is = null;
		String result = "";
		//the year data to send
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("channal",channal));
		 
		//http post
		try{
		        HttpClient httpclient = new DefaultHttpClient();
		        HttpPost httppost = new HttpPost("http://kavithatalent.lk/ebridge/mycall/tvserver/channsch.php");
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
		                        ", programe: "+json_data.getString("programe")
		                );
		                
		                aa.add(json_data.getString("programe")+"\n 	"+json_data.getString("starttime")+"	to "+json_data.getString("endtime"));
		        }
		
		        //listView1.setAdapter(aa);
		}catch(JSONException e){
		        Log.e("log_tag", "Error parsing data "+e.toString());
		}
	}
	
	
	
	
	
	
	
	
	
	

}
