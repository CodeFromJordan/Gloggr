package com.JordHan.Gloggr;

import android.app.Activity;
import android.os.Bundle;

public class GameListActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); // Push instance of activity to super
		super.setTitle(R.string.game_list_title); // Set title
		
		// Set up layout
		//setContentView(R.layout.gamelist_activity);
	}
}
