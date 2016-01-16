/**
 * The Cursor is usually used for the database operation
 */

package com.example.learn;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.provider.ContactsContract;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class simCursorAdp extends Activity{

	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		ListView listView = new ListView(this);
		//User Content Provider
		/*(Uri uri = ContactsContract.Contacts.CONTENT_URI;
		String[] projection = new String[]{ContactsContract.Contacts._ID, 
				ContactsContract.Contacts.DISPLAY_NAME};
		String selection = ContactsContract.Contacts.IN_VISIBLE_GROUP + "='" + ("1") + "'";
		String sortOrder = ContactsContract.Contacts.DISPLAY_NAME +  "COLLATE LOCALIZED ASC";
		@SuppressWarnings("deprecation")
		Cursor cursor = managedQuery(uri, projection, selection, null, sortOrder);*/
		
		
		// Data is from the Content List in your phone
		Cursor cursor = getContentResolver().query(People.CONTENT_URI, null, null, null, null);
		startManagingCursor(cursor);
		
		ListAdapter listAdp = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1,
				cursor, new String[]{People.NAME}, new int[]{android.R.id.text1});
		
		listView.setAdapter(listAdp);
		setContentView(listView);
		
		OnItemClickListener listener = new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				setTitle(arg0.getItemAtPosition(arg2).toString());
				Toast.makeText(simCursorAdp.this, "I choose :" + arg2, Toast.LENGTH_SHORT).show();
			}
			
		};
		
		listView.setOnItemClickListener(listener);
		
				
	}
}