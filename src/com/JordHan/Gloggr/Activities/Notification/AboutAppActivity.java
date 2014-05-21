package com.JordHan.Gloggr.Activities.Notification;

import android.app.Activity;
import android.os.Bundle;

import com.JordHan.Gloggr.R;

public class AboutAppActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); // Push instance of activity to super
		
		super.setTitle(getString(R.string.about_app_title)); // Set title

		// Set up layout
		setContentView(R.layout.aboutapp_activity);
	}

}
