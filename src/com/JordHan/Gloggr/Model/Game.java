package com.JordHan.Gloggr.Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.json.JSONException;
import org.json.JSONObject;

@DatabaseTable
public class Game {
    // Members and database field annotations
    @DatabaseField(id = true)
    private String id;
    
    // No argument constructor
    // Used for ORMLite
    public Game() { }
    
    // Constructor which uses JSONObject
    
    // Constructor which uses separate parameters
    public Game(String id) {
    	this.id = id;
    }
    
    // Getters
    public String getID(JSONObject game) {
        return this.id;
    }
}
