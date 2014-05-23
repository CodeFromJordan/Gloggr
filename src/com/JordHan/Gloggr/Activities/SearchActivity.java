package com.JordHan.Gloggr.Activities;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.JordHan.Gloggr.R;
import com.JordHan.Gloggr.Model.Game;
import com.JordHan.Gloggr.Services.AbstractService;
import com.JordHan.Gloggr.Services.GameSearchService;
import com.JordHan.Gloggr.Services.IServiceListener;

public class SearchActivity extends ListActivity implements IServiceListener {

	private Thread thread;
	private ArrayList<Game> searchResults;
	public static final String GAME_SEARCH_CLICKED = "game_result_selected";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); // Push instance of activity to
											// super
		super.setTitle(getString(R.string.search_results_title)); // Set title

		// Set up objects
		searchResults = new ArrayList<Game>();

		// Used to handle new intents when activity created
		handleIntent(getIntent());

		// Set up layout
		setContentView(R.layout.search_activity);
	}

	// Allows intent to be picked up from own activity
	@Override
	public void onNewIntent(Intent intent) {
		setIntent(intent);
		handleIntent(intent); // Check if it's search intent
	}

	// Used to handle individual intents
	public void handleIntent(Intent intent) {
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) { // Detect if
																// intent is for
																// searching
			String query = intent.getStringExtra(SearchManager.QUERY);
			doSearch(query);
		}
	}

	// When a search result is clicked
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		if (position < searchResults.size()) { // If it is within boundary of
												// number of results

			// Create an intent to pass back to gamelist_activity
			Intent clickedResultIntent = new Intent();
			clickedResultIntent.setAction(GAME_SEARCH_CLICKED);
			clickedResultIntent.putExtra("game_data",
					searchResults.get(position).toString());

			this.sendBroadcast(clickedResultIntent);

			this.finish(); // Close search results activity
		}
	}

	// Used to perform actual search
	public void doSearch(String query) {
		GameSearchService service = new GameSearchService(query);

		String[] result = new String[] { "Searching for games.." };

		service.addListener(this);
		thread = new Thread(service);
		thread.start();

		// Only setting initial message set above
		setListAdapter(new ArrayAdapter<String>(this, R.layout.game_list_cell,
				R.id.text, result));
	}

	@Override
	public void ServiceComplete(AbstractService service) { // GameSearchService
															// calls back here
															// when finished
		if (!service.hasError()) {

			GameSearchService gameSearchService = (GameSearchService) service;

			JSONArray initialResults = gameSearchService.getResults();

			final int numberOfResults = initialResults.length();

			JSONArray games = gameSearchService.getResults();

			searchResults.clear(); // Empty initial message

			for (int resultCounter = 0; resultCounter < numberOfResults; resultCounter++) {
				try {
					JSONObject gameToAddJSON = initialResults
							.getJSONObject(resultCounter); // Get JSON of
															// individual result

					// Must add entry for each platform
					for (int platformCounter = 0; platformCounter < gameToAddJSON
							.getJSONArray("platforms").length(); platformCounter++) {
						// Parse information to Game object
						String id = gameToAddJSON.getString("id");
						String title = gameToAddJSON.getString("name");
						String system = gameToAddJSON.getJSONArray("platforms")
								.getJSONObject(platformCounter).getString("name");
						int percentageFinished = 0;
						int rating = -1;
						String notes = "";
						searchResults.add(new Game(id, title, system,
								percentageFinished, rating, notes));
					}

				} catch (JSONException ex) {
				}
			}

			// Map array of games to list
			setListAdapter(new ArrayAdapter<Game>(this,
					R.layout.game_list_cell, R.id.text, searchResults));
		}
	}

	// When Menu button clicked
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Populate menu with items in XML file
		getMenuInflater().inflate(R.menu.search_menu, menu);

		// Add SearchWidget
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(
				R.id.itemSearchPerform).getActionView();
		searchView.setSearchableInfo(searchManager
				.getSearchableInfo(getComponentName()));

		return true;
	}
}
