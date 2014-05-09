package com.JordHan.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.util.ArrayList;
import java.util.List;
import com.JordHan.Gloggr.Model.Game;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final int DATABASE_VERSION = 1; // Change when database structure changes
    
    // Database file game (move to helper class eventually)
    private static final String databaseLocation = android.os.Environment.getExternalStorageDirectory().getAbsolutePath()
            + "/Gloggr/" + "GloggrDB.sqlite";
    
    // Dao object used to access tables
    private Dao<Game, Integer> gameDao = null;

    public DatabaseHelper(Context context) {
        super(context, databaseLocation, null, DATABASE_VERSION);
    }

    // When program first installed/run, create database and tables
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Game.class);
        } catch (SQLException ex) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", ex);
        } catch (java.sql.SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Used to update database structure
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            // List of SQL statements
            List<String> allSql = new ArrayList<String>();
            switch (oldVersion) {
                case 1:
                // Add required SQL staments to allSql
            }
            for (String sql : allSql) { // Execute list of SQL statements
                database.execSQL(sql);
            }
        } catch (SQLException ex) {
            Log.e(DatabaseHelper.class.getName(), "Exception during onUpgrade", ex);
            throw new RuntimeException(ex);
        }
    }

    // Get gameDao
    public Dao<Game, Integer> getGameDao() {
        // If it's null, try to get it before returning
        if (gameDao == null) {
            try {
                gameDao = getDao(Game.class);
            } catch (java.sql.SQLException ex) {
                ex.printStackTrace();
            }
        }

        return gameDao;
    }
}
