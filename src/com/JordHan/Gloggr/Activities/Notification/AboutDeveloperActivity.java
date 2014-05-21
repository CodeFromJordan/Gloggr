package com.JordHan.Gloggr.Activities.Notification;

import com.JordHan.Gloggr.R;

import android.app.Activity;
import android.os.Bundle;

public class AboutDeveloperActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); // Push instance of activity to super
		
		super.setTitle(getString(R.string.about_developer_title)); // Set title

		// Set up layout
		setContentView(R.layout.aboutdeveloper_activity);
	}

}
