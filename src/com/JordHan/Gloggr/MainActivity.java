package com.JordHan.Gloggr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.JordHan.Gloggr.Helper.MenuHelper;
import com.JordHan.db.DatabaseManager;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		DatabaseManager.init(this);
	}
	
    // When Menu button clicked
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// Populate menu with items in XML file
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }
    
    // When item in menu selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	// Call MenuHelper class to operate on menu item clicked, get an intent back
    	Intent activityToOpen = MenuHelper.performMenuOperation(item, getBaseContext(), getApplicationContext());
    	
    	if(activityToOpen != null) { // If Intent to an activity returned
    		startActivity(activityToOpen); // Open it
    		return true;
    	}
    	
    	return false; // Else return that there was an error
    }
}
