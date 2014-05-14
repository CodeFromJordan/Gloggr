package com.JordHan.Gloggr;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.JordHan.Gloggr.Helper.MenuHelper;
import com.JordHan.Gloggr.db.DatabaseManager;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); // Push instance of activity to
											// super

		// Setup interface
		setContentView(R.layout.main_activity); // Load interface

		// Get access to views
		ImageView imgProfilePicture = (ImageView) findViewById(R.id.imgProfilePicture);
		TextView txtWelcomeMessage = (TextView) findViewById(R.id.txtProfileWelcomeMessage);

		// Get values from resources
		String welcomeMessage = getString(R.string.profileWelcomeMessage);
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(MainActivity.this);
		String first_name = preferences.getString("first_name", "Current User");

		// Customise interface values
		txtWelcomeMessage.setText("* " + first_name + welcomeMessage + " *");

		// Create listeners for controls
		imgProfilePicture.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(getApplicationContext(), "Image clicked",
						Toast.LENGTH_SHORT).show();
			}
		});

		DatabaseManager.init(this); // Create instance of DatabaseManager ready
									// for ORM
	}
	
	// Used to refresh activity on resume (mainly for refreshing after preference change)
	@Override
	public void onResume() {
		super.onResume();
		this.onCreate(null);
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
		// Call MenuHelper class to operate on menu item clicked, get an intent
		// back
		Intent activityToOpen = MenuHelper.performMenuOperation(item,
				getBaseContext(), getApplicationContext());

		if (activityToOpen != null) { // If Intent to an activity returned
			startActivity(activityToOpen); // Open it
			return true;
		}

		return false; // Else return that there was an error
	}
}
