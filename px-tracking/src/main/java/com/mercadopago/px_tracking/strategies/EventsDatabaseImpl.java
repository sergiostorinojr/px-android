package com.mercadopago.px_tracking.strategies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mercadopago.px_tracking.model.AppInformation;
import com.mercadopago.px_tracking.model.DeviceInfo;
import com.mercadopago.px_tracking.model.Event;
import com.mercadopago.px_tracking.model.EventTrackIntent;
import com.mercadopago.px_tracking.model.ScreenViewEvent;
import com.mercadopago.px_tracking.utils.JsonConverter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class EventsDatabaseImpl extends SQLiteOpenHelper implements EventsDatabase {

    private static final String DATABASE_NAME = "mercadopago-sdk.db";
    private static final String TABLE_NAME = "events";
    private static final String ID = "_id";
    private static final String TRACK_JSON = "track";
    private static final String TIMESTAMP = "timestamp";
    private static final String SPLITTER = "%";
    private static final int DATABASE_VERSION = 1;

    private Integer batchSizeCache = 0;

    public EventsDatabaseImpl(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        //TODO SACAR!!!
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);
        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TIMESTAMP + " TEXT, " +
                TRACK_JSON + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void addTrack(EventTrackIntent eventTrackIntent) {
        ContentValues values = new ContentValues();
        values.put(TRACK_JSON, JsonConverter.getInstance().toJson(eventTrackIntent) + "%");
        values.put(TIMESTAMP, new Timestamp(System.currentTimeMillis()).toString());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
        batchSizeCache++;
    }

    @Override
    public Integer batchSize() {
        if (batchSizeCache == 0) {
            SQLiteDatabase db = getWritableDatabase();
            //TODO LIMITE POR TIMESTAMP
            Long count = DatabaseUtils.longForQuery(db, "SELECT COUNT(*) FROM " + TABLE_NAME, null);
            db.close();
            batchSizeCache = count.intValue();
        }
        return batchSizeCache;
    }

    @Override
    public EventTrackIntent retrieveBatch() {
        SQLiteDatabase db = getReadableDatabase();

        String[] columns = {"group_concat(" + TRACK_JSON + ")"};

        Cursor cursor = db.query(false, TABLE_NAME, columns, null, null, null, null, "_id asc", null);

        List<EventTrackIntent> tracks = new ArrayList<>();

        String[] trackJsons;
        while (cursor.moveToNext()) {
            trackJsons = cursor.getString(0).split(SPLITTER + ",");
            String lastJson =  trackJsons[trackJsons.length - 1];
            lastJson = lastJson.replace("%", "");
            trackJsons[trackJsons.length - 1] = lastJson;
            for (String trackJson : trackJsons) {
                //TODO 
            }
        }
        cursor.close();
        db.close();

        return compressTrackPayload(tracks);

    }

    private EventTrackIntent compressTrackPayload(List<EventTrackIntent> tracks) {

        if (tracks != null && !tracks.isEmpty()) {
            AppInformation appInformation = tracks.get(0).getApplication();
            DeviceInfo deviceInfo = tracks.get(0).getDevice();
            String clientId = tracks.get(0).getClientId();

            List<Event> events = new ArrayList<>();
            for (EventTrackIntent intent : tracks) {
                events.addAll(intent.getEvents());
            }
            return new EventTrackIntent(clientId, appInformation, deviceInfo, events);
        } else {
            return null;
        }
    }

    @Override
    public void rollback() {

    }
}
