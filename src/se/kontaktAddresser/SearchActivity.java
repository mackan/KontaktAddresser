package se.kontaktAddresser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;


import android.app.Activity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class SearchActivity extends Activity {

	private Result result;
	
	
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        
    }
    
    public void onClick(View view){
    
    	updateContacts();
    	
    	Log.d("","click");
    }
    
    
	public void updateContacts(){
		ContentResolver cr = getContentResolver();
		 Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
	                null, null, null, null);
	        if (cur.getCount() > 0) {
		    while (cur.moveToNext()) {
		        String id = cur.getString(
	                        cur.getColumnIndex(ContactsContract.Contacts._ID));
		        String hasPhone = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)); 
		  
		           
		           Cursor phones = getContentResolver().query( ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id, null, null); 
		           while (phones.moveToNext()) { 
		              String phoneNumber = phones.getString(phones.getColumnIndex( ContactsContract.CommonDataKinds.Phone.NUMBER));                 
		             String [] address = getAddressFromWeb(phoneNumber).split(" ");
		              Log.d("", getAddressFromWeb(phoneNumber));  		              	              
		              Log.d("",phoneNumber);
		              
		             for( String a : address)
		            	 
		            	 Log.d("",a);
		              
		           }
		        phones.close(); 
		      
		        Log.d("",id);

	        }

			
			
		}
		
		//return exists;

	}
    
	private String getAddressFromWeb(String number) {

		result = null;
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet("http://rkseek.oblivioncreations.se/?"
				+ number + "&out=json");
		HttpResponse response;

		try {
			response = client.execute(request);

			InputStream in = response.getEntity().getContent();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(in));

			StringBuilder str = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				str.append(line);
			}
			in.close();

			String graph = str.toString();
			JSONArray jsonArray = new JSONArray(graph);
			result = new Result(jsonArray);
			
		} catch (ClientProtocolException e) {
			Log.e(e.getLocalizedMessage(), e.getMessage(), e);
		} catch (IOException e) {
			Log.e(e.getLocalizedMessage(), e.getMessage(), e);
		} catch (JSONException e) {
			Log.e(e.getLocalizedMessage(), e.getMessage(), e);
		}
		
		return result.getAddress()+" "+result.getCity();
	}
    
}
