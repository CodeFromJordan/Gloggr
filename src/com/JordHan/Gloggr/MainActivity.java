package com.JordHan.Gloggr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.JordHan.Gloggr.Helper.MenuHelper;
import com.JordHan.Gloggr.db.DatabaseManager;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); // Push instance of activity to super
		
		// Setup interface
		setContentView(R.layout.main_activity); // Load interface
		ImageView imgProfilePicture = (ImageView)findViewById(R.id.imgProfilePicture);
		
		imgProfilePicture.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(getApplicationContext(), "Image clicked", Toast.LENGTH_SHORT).show();
			}
		});
		
		DatabaseManager.init(this); // Create instance of DatabaseManager ready for ORM
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
