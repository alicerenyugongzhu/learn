package com.example.learn;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DrawerActivity extends Activity {
	
	private DrawerLayout mdl;
	private ActionBarDrawerToggle dt;
	private ListView mlist;
	//private String[] myTitle;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drawer_layout);
		
		mdl = (DrawerLayout)findViewById(R.id.drawer);
		initListView();
		//mdl.setDrawerShadow(R.drawable.image1, GravityCompat.START);
		dt = new ActionBarDrawerToggle(this, mdl, com.example.learn.R.drawable.kula, com.example.learn.R.string.open, com.example.learn.R.string.close){
            public void onDrawerClosed(View view)
            {

               // invalidateOptionsMenu(); // creates call to
                                            // onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView)
            {

               // invalidateOptionsMenu(); // creates call to
                                            // onPrepareOptionsMenu()
            }
		};
		mdl.setDrawerListener(dt);
		mdl.openDrawer(Gravity.LEFT);
		//getActionBar().setDisplayHomeAsUpEnabled(true);
	}
	private void initListView() {
		// TODO Auto-generated method stub
		mlist = (ListView)findViewById(R.id.left_drawer);
		//myTitle = getResources().getStringArray(R.array.city);
		List<String> data = new ArrayList<String>();
		data.add("Alice");
		data.add("Alice1");
		data.add("Alice2");
		data.add("Alice3");
		mlist.setAdapter(new ArrayAdapter<String>(this, 
				android.R.layout.simple_expandable_list_item_1, data));
		
	}
	@Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        dt.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        dt.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (dt.onOptionsItemSelected(item))
        {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

}