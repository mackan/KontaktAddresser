package se.kontaktAddresser;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;



public class ContactsManager extends Activity  {
	
	public void updateContacts(){
		ContentResolver cr = getContentResolver();
		 Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
	                null, null, null, null);
	        if (cur.getCount() > 0) {
		    while (cur.moveToNext()) {
		        String id = cur.getString(
	                        cur.getColumnIndex(ContactsContract.Contacts._ID));
		        Log.d("",id);

	        }

			
			
		}
		
		//return exists;

	}
}

