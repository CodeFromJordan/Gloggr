package com.JordHan.Gloggr.Helper;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.Toast;

import com.JordHan.Gloggr.R;
import com.JordHan.Gloggr.Activities.CurrentlyPlayingActivity;
import com.JordHan.Gloggr.Activities.GameListActivity;
import com.JordHan.Gloggr.Activities.MainActivity;
import com.JordHan.Gloggr.Activities.PreferencesActivity;

public class MenuHelper {
	// Pass in MenuItem
	// Return an Intent to an activity to open
	public static Intent performMenuOperation(MenuItem item, Context baseContext, Context applicationContext) {
        Intent activityToSwitchTo = new Intent();

        switch(item.getItemId()) {
        case R.id.itemMainActivity:
            activityToSwitchTo = new Intent(baseContext, MainActivity.class);
        	break;
        case R.id.itemCurrentlyPlayingActivity:
        	activityToSwitchTo = new Intent(baseContext, CurrentlyPlayingActivity.class);
        	break;
        case R.id.itemGameListActivity:
        	activityToSwitchTo = new Intent(baseContext, GameListActivity.class);
        	break;
        case R.id.itemPreferencesActivity:
        	activityToSwitchTo = new Intent(baseContext, PreferencesActivity.class);
        	break;
        default:
        	Toast.makeText(applicationContext, "Error opening activity", Toast.LENGTH_SHORT).show();
        	activityToSwitchTo = null;
        }
        
        return activityToSwitchTo;
	}
}
