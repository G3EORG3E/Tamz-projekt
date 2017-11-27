package com.example.g3org3.horoscopes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by G3ORG3 on 27.11.2017.
 */

public class FavouritesDbHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "horscopeDb";

    // Contacts table name
    private static final String TABLE_HOROSCOPE = "horoscope";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_HOROSCOPE = "horoscope";
    private static final String KEY_INTENSITY = "intensity";
    private static final String KEY_MOOD = "mood";
    private static final String KEY_KEYWORDS = "keywords";
    private static final String KEY_DATE = "date";
    private static final String KEY_SUNSIGN = "sunsign";
    private static final String KEY_NOTE = "note";

    public FavouritesDbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_HOROSCOPE_TABLE = "CREATE TABLE " + TABLE_HOROSCOPE + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_HOROSCOPE + " TEXT,"
                + KEY_INTENSITY + " TEXT,"
                + KEY_MOOD + " TEXT,"
                + KEY_KEYWORDS + " TEXT,"
                + KEY_DATE + " TEXT,"
                + KEY_SUNSIGN + " TEXT,"
                + KEY_NOTE + " TEXT" + ")";
        db.execSQL(CREATE_HOROSCOPE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HOROSCOPE);
        onCreate(db);
    }

    public void resetHorosopeTable() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HOROSCOPE);

        String CREATE_HOROSCOPE_TABLE = "CREATE TABLE " + TABLE_HOROSCOPE + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_HOROSCOPE + " TEXT,"
                + KEY_INTENSITY + " TEXT,"
                + KEY_MOOD + " TEXT,"
                + KEY_KEYWORDS + " TEXT,"
                + KEY_DATE + " TEXT,"
                + KEY_SUNSIGN + " TEXT,"
                + KEY_NOTE + " TEXT" + ")";
        db.execSQL(CREATE_HOROSCOPE_TABLE);
        db.close();
    }

    // Adding new
    public void addHoroscope(Sunsign sunsign) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_HOROSCOPE, sunsign.getHoroscope());
        values.put(KEY_INTENSITY, sunsign.getIntensity());
        values.put(KEY_MOOD, sunsign.getMood());
        values.put(KEY_KEYWORDS, sunsign.getKeywords());
        values.put(KEY_DATE, sunsign.getDate());
        values.put(KEY_SUNSIGN, sunsign.getSunsign());
        values.put(KEY_NOTE, sunsign.getNote());

        db.insert(TABLE_HOROSCOPE, null, values);
        db.close();
    }

    // Getting single
    public Sunsign getHoroscope(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_HOROSCOPE,
                new String[] { KEY_ID, KEY_HOROSCOPE, KEY_INTENSITY, KEY_MOOD, KEY_KEYWORDS, KEY_DATE, KEY_SUNSIGN, KEY_NOTE },
                KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Sunsign sunsign = new Sunsign(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6),
                cursor.getString(7));

        return sunsign;
    }

    // Getting All
    public List<Sunsign> getAllHoroscopes() {
        List<Sunsign> contactList = new ArrayList<Sunsign>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_HOROSCOPE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                Sunsign sunsign = new Sunsign();
                sunsign.setId(Integer.parseInt(cursor.getString(0)));
                sunsign.setHoroscope(cursor.getString(1));
                sunsign.setIntensity(cursor.getString(2));
                sunsign.setMood(cursor.getString(3));
                sunsign.setKeywords(cursor.getString(4));
                sunsign.setDate(cursor.getString(5));
                sunsign.setSunsign(cursor.getString(6));
                sunsign.setNote(cursor.getString(7));

                contactList.add(sunsign);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // Getting Count
    public int getHoroscopeCount() {
        String countQuery = "SELECT  * FROM " + TABLE_HOROSCOPE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    // Updating single
    public int updateHoroscope(Sunsign sunsign) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_HOROSCOPE, sunsign.getHoroscope());
        values.put(KEY_INTENSITY, sunsign.getIntensity());
        values.put(KEY_MOOD, sunsign.getMood());
        values.put(KEY_KEYWORDS, sunsign.getKeywords());
        values.put(KEY_DATE, sunsign.getDate());
        values.put(KEY_SUNSIGN, sunsign.getSunsign());
        values.put(KEY_NOTE, sunsign.getNote());

        // updating row
        return db.update(TABLE_HOROSCOPE, values, KEY_ID + " = ?",
                new String[] { String.valueOf(sunsign.getId()) });
    }

    // Deleting single
    public void deleteHoroscope(Sunsign sunsign) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_HOROSCOPE, KEY_ID + " = ?",
                new String[] { String.valueOf(sunsign.getId()) });
        db.close();
    }


}
