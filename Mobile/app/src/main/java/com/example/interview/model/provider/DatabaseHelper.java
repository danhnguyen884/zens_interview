package com.example.interview.model.provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.interview.model.db.ContentJoke;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contentsManager";
    private static final String TABLE_CONTENT_S = "contents";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_IS_LIKE = "is_like";
    private static final String KEY_IS_READ = "is_read";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_contentS_TABLE = "CREATE TABLE " + TABLE_CONTENT_S + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_IS_LIKE + " INTEGER,"
                + KEY_IS_READ + " INTEGER" + ")";
        db.execSQL(CREATE_contentS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTENT_S);

        // Create tables again
        onCreate(db);
    }

    public void addContent(ContentJoke content) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, content.getContentText());
        values.put(KEY_IS_LIKE, content.getIsLike());
        values.put(KEY_IS_READ, content.getIsRead());

        // Inserting Row
        if (!rowIdExists(String.valueOf(content.getId()))) {
            db.insert(TABLE_CONTENT_S, null, values);
        }

        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get all contents in a list view
    public List<ContentJoke> getAllContents() {
        List<ContentJoke> contentList = new ArrayList<ContentJoke>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_CONTENT_S;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ContentJoke content = new ContentJoke();
                content.setId(Integer.parseInt(cursor.getString(0)));
                content.setContentText(cursor.getString(1));
                content.setIsLike(Integer.parseInt(cursor.getString(2)));
                // Adding content to list
                contentList.add(content);
            } while (cursor.moveToNext());
        }

        // return content list
        return contentList;
    }

    // code to update the single content
    public int updateContent(ContentJoke content) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, content.getContentText());
        values.put(KEY_IS_LIKE, content.getIsLike());
        values.put(KEY_IS_READ, content.getIsRead());

        // updating row
        return db.update(TABLE_CONTENT_S, values, KEY_ID + " = ?",
                new String[]{String.valueOf(content.getId())});
    }

    public boolean rowIdExists(String StrId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select id from " + TABLE_CONTENT_S
                + " where id=?", new String[]{StrId});
        return (cursor.getCount() > 0);
    }

    public void clearDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_CONTENT_S);
    }

}