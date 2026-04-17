package com.mycollections.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME  = "collections.db";
    private static final int    DATABASE_VERSION = 1;

    public static final String TABLE_ITEMS        = "items";
    public static final String COLUMN_ID          = "_id";
    public static final String COLUMN_TITLE       = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_DATE        = "date_added";

    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_ITEMS + " (" +
            COLUMN_ID          + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_TITLE       + " TEXT NOT NULL, " +
            COLUMN_DESCRIPTION + " TEXT, " +
            COLUMN_DATE        + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        onCreate(db);
    }

    public long insertItem(CollectionItem item) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE,       item.getTitle());
        cv.put(COLUMN_DESCRIPTION, item.getDescription());
        cv.put(COLUMN_DATE,        item.getDateAdded());
        long id = db.insert(TABLE_ITEMS, null, cv);
        db.close();
        return id;
    }

    public List<CollectionItem> getAllItems() {
        List<CollectionItem> list = new ArrayList<>();
        SQLiteDatabase db     = getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_ITEMS + " ORDER BY " + COLUMN_ID + " DESC", null);
        if (cursor.moveToFirst()) {
            do {
                CollectionItem item = new CollectionItem();
                item.setId(          cursor.getLong  (cursor.getColumnIndexOrThrow(COLUMN_ID)));
                item.setTitle(       cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)));
                item.setDescription( cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION)));
                item.setDateAdded(   cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE)));
                list.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    public void deleteItem(long id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_ITEMS, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
}
