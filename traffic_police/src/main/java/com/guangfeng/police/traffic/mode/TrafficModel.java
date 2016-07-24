package com.guangfeng.police.traffic.mode;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.guangfeng.police.traffic.ItemListActivity;
import com.guangfeng.police.traffic.mode.TrafficContract.FeedEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by heyongjian
 * on 16/7/16.
 */
public class TrafficModel {
    TrafficDbHelper mDbHelper;
    List<TrafficTicket> mTickets;
    RecyclerView.Adapter<ItemListActivity.SimpleItemRecyclerViewAdapter.ViewHolder> adapter;

    public TrafficModel(Context context) {
        mDbHelper = new TrafficDbHelper(context);
    }

    public SQLiteOpenHelper getDbHelper() {
        return mDbHelper;
    }

    public boolean addTicket(TrafficTicket ticket){
        long newRowId;
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FeedEntry.COLUMN_NAME_ENTRY_ID, ticket.getEntryId());
        cv.put(FeedEntry.COLUMN_NAME_VIOLATIONS_DATE, ticket.getViolationsDate());
        cv.put(FeedEntry.COLUMN_NAME_VIOLATIONS_CODES, listToString(ticket.getViolationsCodes()));
        cv.put(FeedEntry.COLUMN_NAME_CAR_NUMBER, ticket.getCarNumber());
        cv.put(FeedEntry.COLUMN_NAME_CAR_MODEL, ticket.getCarModel());
        cv.put(FeedEntry.COLUMN_NAME_CAR_MILEAGE, ticket.getCarMileage());
        cv.put(FeedEntry.COLUMN_NAME_CAR_PHOTOS, listToString(ticket.getCarPhotos()));
        cv.put(FeedEntry.COLUMN_NAME_CAR_STORE_LOCATION, ticket.getCarStoreLocation());
        cv.put(FeedEntry.COLUMN_NAME_DUTY_POLICE, ticket.getDutyPolice());
        cv.put(FeedEntry.COLUMN_NAME_CAR_MANAGER, ticket.getCarManager());
        cv.put(FeedEntry.COLUMN_NAME_CAR_RELEASE_DATE, ticket.getCarReleaseDate());
        cv.put(FeedEntry.COLUMN_NAME_CAR_PROCESS_RESULTS, listToString(ticket.getCarProcessResults()));
        newRowId = db.insert(FeedEntry.TABLE_NAME, null, cv);

        mTickets.add(ticket);
        adapter.notifyDataSetChanged();
        Log.d("TrafficModel","------1addTicket ID " + newRowId);
        Log.d("TrafficModel","------2addTicket ID " + cv );
        Log.d("TrafficModel","------3addTicket ID " + ticket.toString() );
        if (newRowId > 0)
            return true;
        return false;
    }

    public void modifyTicket(){

    }


    public void deleteTicket(){

    }

    public List<TrafficTicket> listTickets(boolean forceReload){
        if (!forceReload && mTickets != null)
            return mTickets;
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + FeedEntry.TABLE_NAME, null);
        List<TrafficTicket> tickets = new ArrayList<>();
        while (cursor.moveToNext()){
            TrafficTicket ticket = new TrafficTicket();
            ticket.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            ticket.setEntryId(cursor.getString(cursor.getColumnIndex(FeedEntry.COLUMN_NAME_ENTRY_ID)));
            ticket.setViolationsDate(cursor.getString(cursor.getColumnIndex(FeedEntry.COLUMN_NAME_VIOLATIONS_DATE)));
            ticket.setCarNumber(cursor.getString(cursor.getColumnIndex(FeedEntry.COLUMN_NAME_CAR_NUMBER)));
            ticket.setCarModel(cursor.getString(cursor.getColumnIndex(FeedEntry.COLUMN_NAME_CAR_MODEL)));
            ticket.setCarMileage(cursor.getString(cursor.getColumnIndex(FeedEntry.COLUMN_NAME_CAR_MILEAGE)));
            ticket.setCarStoreLocation(cursor.getString(cursor.getColumnIndex(FeedEntry.COLUMN_NAME_CAR_STORE_LOCATION)));
            ticket.setDutyPolice(cursor.getString(cursor.getColumnIndex(FeedEntry.COLUMN_NAME_DUTY_POLICE)));
            ticket.setCarManager(cursor.getString(cursor.getColumnIndex(FeedEntry.COLUMN_NAME_CAR_MANAGER)));
            ticket.setCarReleaseDate(cursor.getString(cursor.getColumnIndex(FeedEntry.COLUMN_NAME_CAR_RELEASE_DATE)));

            ticket.setViolationsCodes(stringToList(cursor.getString(cursor.getColumnIndex(FeedEntry.COLUMN_NAME_VIOLATIONS_CODES))));
            ticket.setCarPhotos(stringToList(cursor.getString(cursor.getColumnIndex(FeedEntry.COLUMN_NAME_CAR_PHOTOS))));
            ticket.setCarProcessResults(stringToList(cursor.getString(cursor.getColumnIndex(FeedEntry.COLUMN_NAME_CAR_PROCESS_RESULTS))));

            tickets.add(ticket);
        }
        cursor.close();

        for (TrafficTicket ticket: tickets){
            Log.d("TrafficModel","-----listTicket " + ticket);
        }

        mTickets = tickets;
        return mTickets;
    }

    public void setAdapter(RecyclerView.Adapter<ItemListActivity.SimpleItemRecyclerViewAdapter.ViewHolder> adapters) {
        adapter = adapters;
    }

    private String listToString(List<String> list) {
        StringBuffer stringBuffer = new StringBuffer();
        for (String s: list){
            stringBuffer.append(s).append("%");
        }
        Log.d("TrafficModel","------listTostring " + stringBuffer.toString());
        return stringBuffer.toString();
    }

    private List<String> stringToList(String s) {
        String [] array = s.split("%");
        Log.d("TrafficModel", "-----stringToArray " + array);
        return Arrays.asList(array);
    }
}
