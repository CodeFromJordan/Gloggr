// Purpose: To handle searching of game titles within application

package com.JordHan.Gloggr.Services;

import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.JordHan.Gloggr.R;

public class GameSearchService extends AbstractService {

    private String query;
    private JSONArray results;

    // Create instance of service
    public GameSearchService(String query) {
    	String regularQuotes = "\"";
    	String encodedQuotes = URLEncoder.encode(regularQuotes);
    	
    	//this.query = regularQuotes + query + regularQuotes;
    	
        this.query = encodedQuotes + query + encodedQuotes;
    }

    // Getters
    public JSONArray getResults() {
        return results;
    }

    // Main service, use HttpClient to download bytes and convert to JSONArray
    public void run() {
        String api_key = "549850873039c81f04c7277ec35a913728e3cf65";
        
        String url = "http://www.giantbomb.com/api/search/?api_key=" + api_key + 
        		"&format=json&query=" + query + "&resources=game";

        boolean error = false;
        HttpClient httpClient = null;
        try {
            httpClient = new DefaultHttpClient();
            HttpResponse data = httpClient.execute(new HttpGet(url));
            HttpEntity entity = data.getEntity();

            String result = EntityUtils.toString(entity, "UTF8");

            JSONObject json = new JSONObject(result);
            results = json.getJSONArray("results");

            if (results.length() == 0) {
                error = true;
            }
        } catch (Exception e) {
            results = null;
            error = true;
        } finally {
            httpClient.getConnectionManager().shutdown();
        }

        super.serviceComplete(error);
    }
}
