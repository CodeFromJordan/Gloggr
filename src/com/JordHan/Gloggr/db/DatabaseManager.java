package com.JordHan.Gloggr.db;

import android.content.Context;
import android.database.SQLException;
import java.util.List;
import com.JordHan.Gloggr.Model.Game;

public class DatabaseManager {

    static private DatabaseManager instance;

    // Singleton
    static public void init(Context ctx) {
        if (instance == null) {
            instance = new DatabaseManager(ctx);
        }
    }

    static public DatabaseManager getInstance() {
        return instance;
    }

    private DatabaseHelper helper;

    private DatabaseManager(Context ctx) {
        helper = new DatabaseHelper(ctx);
    }

    private DatabaseHelper getHelper() {
        return this.helper;
    }

    // Get all games from database
    public List<Game> getAllgames() throws SQLException {
        List<Game> games = null;

        try {
            games = getHelper().getGameDao().queryForAll();
        } catch (java.sql.SQLException ex) {
            ex.printStackTrace();
        }

        return games;
    }

    // Add new game to database
    public void addNewGame(Game newGame) {
        try {
            getHelper().getGameDao().create(newGame);
        } catch (java.sql.SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    // Delete game from database
    public void deleteGame(Game game) {
        try {
            getHelper().getGameDao().delete(game);
        } catch (java.sql.SQLException ex) {
            ex.printStackTrace();
        }
    }
}
