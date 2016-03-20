package in.teachcoder.journalapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import in.teachcoder.journalapp.Constants;

/**
 * Created by Arnav on 20-Mar-16.
 */
public class MyDB {
    private Context context;
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public MyDB(Context c){
        dbHelper = new DBHelper(c);
    }

    public void open(){
        db = dbHelper.getWritableDatabase();
    }

    public void close(){
        db.close();
    }

    public long insertEntry(String title, String highlight, String content, Long date){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.TITLE_NAME, title);
        contentValues.put(Constants.HIGHLIGHT_NAME, highlight);
        contentValues.put(Constants.CONTENT_NAME, content);
        contentValues.put(Constants.DATE_NAME, date);
        return db.insert(Constants.TABLE_NAME,null,contentValues);
    }

   
}
