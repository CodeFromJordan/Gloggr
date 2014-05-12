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

public class GameSearchService extends AbstractService {

    private String query;
    private JSONArray results;

    // Create instance of service
    public GameSearchService(String query) {
        this.query = URLEncoder.encode(query);
    }

    // Getters
    public JSONArray getResults() {
        return results;
    }

    // Main service, use HttpClient to download bytes and convert to JSONArray
    public void run() {
        String api_key = "";
        String url = "";

        boolean error = false;
        HttpClient httpClient = null;
        try {
            httpClient = new DefaultHttpClient();
            HttpResponse data = httpClient.execute(new HttpGet(url));
            HttpEntity entity = data.getEntity();

            String result = EntityUtils.toString(entity, "UTF8");

            JSONObject json = new JSONObject(result);
            json = json.getJSONObject("listings");
            results = json.getJSONArray("listing");

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
