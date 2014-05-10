package com.JordHan.Gloggr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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
        Intent activityToSwitchTo = new Intent();

        switch(item.getItemId()) {
        case R.id.itemMainActivity:
            activityToSwitchTo = new Intent(getBaseContext(), MainActivity.class);
            startActivity(activityToSwitchTo);
        	break;
        case R.id.itemCurrentlyPlayingActivity:
            Toast.makeText(getApplicationContext(), "Currently Playing", Toast.LENGTH_SHORT).show();
        	break;
        case R.id.itemgameListActivity:
        	Toast.makeText(getApplicationContext(), "Game List", Toast.LENGTH_SHORT).show();
        	break;
        default:
        	Toast.makeText(getApplicationContext(), "Error opening activity", Toast.LENGTH_SHORT).show();
        	return false;
        }
        
        //startActivity(activityToSwitchTo);
        return true;
    }
}
