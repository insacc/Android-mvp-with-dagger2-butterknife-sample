package org.insacc.toddssyndrome.Database;

import android.database.sqlite.SQLiteDatabase;

import rx.Subscription;

/**
 * Created by can on 18.02.2017.
 */

public class BaseDatabaseService {

    private DatabaseHelper mDatabaseHelper;


    private SQLiteDatabase mDatabase;

    public BaseDatabaseService(DatabaseHelper databaseHelper) {
        this.mDatabaseHelper = databaseHelper;
    }

    public SQLiteDatabase getDatabase() {
        return mDatabase;
    }


    public void openDatabase() {
        if (mDatabaseHelper != null)
            mDatabase = mDatabaseHelper.getWritableDatabase();

    }

    public void closeDatabase() {
        if (mDatabase != null) {
            mDatabase.close();
            mDatabase = null;
        }
    }


}
