package com.guangfeng.police.traffic.mode;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.guangfeng.police.traffic.mode.TrafficContract.FeedEntry;

/**
 * Created by heyongjian
 * on 16/7/15.
 */
public class TrafficDbHelper extends SQLiteOpenHelper{
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_VIOLATIONS_CODES + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_VIOLATIONS_DATE + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_CAR_NUMBER + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_CAR_MODEL + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_CAR_MILEAGE + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_CAR_PHOTOS + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_CAR_STORE_LOCATION + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_DUTY_POLICE + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_CAR_MANAGER + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_CAR_PROCESS_RESULTS + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_CAR_RELEASE_DATE + TEXT_TYPE +
                    " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;

    public TrafficDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("DBhelper","-----helper: " );
    }
    public void onCreate(SQLiteDatabase db) {
        Log.d("DBhelper","-----onCreate: " );
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
