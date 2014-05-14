package com.JordHan.Gloggr;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class PreferencesActivity extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); // Push instance of activity to super
		super.setTitle("Preferences"); // Set title
		
		// Setup preference interface using resource
		addPreferencesFromResource(R.xml.preferences);
	}
}
