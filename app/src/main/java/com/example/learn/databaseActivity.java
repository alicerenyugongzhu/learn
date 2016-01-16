package com.example.learn;

import java.util.List;
import java.util.Random;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

public class databaseActivity extends ListActivity{
	private TestDatabaseSource dbsource;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.db_test);
		
		dbsource = new TestDatabaseSource(this);
		dbsource.Open();
		
		List<Comment> values = dbsource.getAllName();
		//Simple adapter
		ArrayAdapter<Comment> adp = new ArrayAdapter<Comment>(this, 
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adp);
	}
	
	public void onClick(View view){
		@SuppressWarnings("unchecked")
		ArrayAdapter<Comment> adp = (ArrayAdapter<Comment>) getListAdapter();
		Comment comment = null;
		//List<Comment> comments;
		
		switch(view.getId()){
		case R.id.add:
			String[] names = new String[]{"Alice", "Cherry", "Allen", "Yuan"};
			int nextInt = new Random().nextInt(3);
			comment = dbsource.CreateName(names[nextInt]);
			//comments = dbsource.getAllName();
			adp.add(comment);
			Log.d("alice", "Done for add");
			break;
		case R.id.delete:
			if(getListAdapter().getCount()>0){
				comment = (Comment) getListAdapter().getItem(0);
				dbsource.DeleteName(comment);
				adp.remove(comment);
			}
			break;
		}
		Log.d("alice", "Update list");
		adp.notifyDataSetChanged();		
	}
	
	@Override
	public void onResume(){
		dbsource.Open();
		super.onResume();
	}
	
	@Override
	public void onPause(){
		dbsource.Close();
		super.onPause();
	}
}
