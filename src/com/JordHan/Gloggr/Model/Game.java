package com.JordHan.Gloggr.Model;

import org.json.JSONObject;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Game {
    // Members and database field annotations
	// Game identification information
    @DatabaseField(id = true)
    private String id;  
    private String title;
    private String system;
    // User specific information
    private int percentageFinished;
    private int rating;
    private String notes;
    
    // No argument constructor
    // Used for ORMLite
    public Game() { }
    
    // Constructor which uses JSONObject
    public Game(JSONObject game) {
    	// Parse JSON here
    }
    
    // Constructor which uses separate parameters
    public Game(String id, String title, String system, 
    		int percentageFinished, int rating, String notes) {
    	this.id = id;
    	this.title = title;
    	this.system = system;
    	this.percentageFinished = percentageFinished;
    	this.rating = rating;
    	this.notes = notes;
    }
    
    // Getters
    public String getID() {
        return this.id;
    }
    
    public String getTitle() {
    	return this.title;
    }
    
    public String getSystem() {
    	return this.system;
    }
    
    public int getPercentageFinished() {
    	return this.percentageFinished;
    }
    
    public int getRating() {
    	return this.rating;
    }
    
    public String getNotes() {
    	return this.notes;
    }
}