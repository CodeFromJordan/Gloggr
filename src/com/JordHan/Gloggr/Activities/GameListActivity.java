package com.JordHan.Gloggr.Activities;

import android.app.ListActivity;
import android.os.Bundle;

import com.JordHan.Gloggr.R;
import com.JordHan.Gloggr.Model.Game;
import com.JordHan.Gloggr.Services.AbstractService;

public class GameListActivity extends ListActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); // Push instance of activity to
											// super
		super.setTitle(getString(R.string.game_list_title)); // Set title

		// Set up layout
		// setContentView(R.layout.gamelist_activity);
	}
}
