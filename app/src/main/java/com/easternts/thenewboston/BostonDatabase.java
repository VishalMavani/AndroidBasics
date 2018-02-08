package com.easternts.thenewboston;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Bansi..!! on 06-07-2016.
 */
public class BostonDatabase {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "TheNewBoston";
    private static final String DATABASE_TABLE = "PersonalInfo";

    public static final String KEY_TABLE_ID = "table_id";
    public static final String KEY_FIRST_NAME = "first_name";
    public static final String KEY_LAST_NAME = "last_name";

    private DatabaseHelper databaseHelper;
    private final Context context;
    private SQLiteDatabase sqLiteDatabase;

    public BostonDatabase(Context context) {
        this.context = context;
    }

    public BostonDatabase openDatabase() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        return this;
    }

    public void closeDatabase()throws SQLException {
        databaseHelper.close();
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String sql = "CREATE TABLE " + DATABASE_TABLE + " (" +
                    KEY_TABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KEY_FIRST_NAME + " TEXT NOT NULL, " +
                    KEY_LAST_NAME + " TEXT NOT NULL);";
            db.execSQL(sql);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }

    public long insertPersonalInfo(String firstName, String lastName) throws SQLException {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_FIRST_NAME, firstName);
        contentValues.put(KEY_LAST_NAME, lastName);
        return sqLiteDatabase.insert(DATABASE_TABLE, null, contentValues);
    }

    public void updatePersonalInfoBasedOnID(String rowId, String firstName, String lastName) throws SQLException {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_FIRST_NAME, firstName);
        contentValues.put(KEY_LAST_NAME, lastName);
        sqLiteDatabase.update(DATABASE_TABLE, contentValues, KEY_TABLE_ID + "=" + rowId, null);
    }

    public void deletePersonalInfo(String rowID) throws SQLException {
        sqLiteDatabase.delete(DATABASE_TABLE, KEY_TABLE_ID + "=" + rowID, null);
    }

    public String selectPersonalInfo() throws SQLException {
        String result = "";
        String[] columns = new String[] {KEY_TABLE_ID, KEY_FIRST_NAME, KEY_LAST_NAME};
        Cursor c = sqLiteDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            result = result + c.getString(c.getColumnIndex(KEY_TABLE_ID)) + " " +
                    c.getString(c.getColumnIndex(KEY_FIRST_NAME)) + " " +
                    c.getString(c.getColumnIndex(KEY_LAST_NAME)) + "\n";
        }
        return result;
    }

    public String[] selectPersonalInfoBasedOnID(String rowId) throws SQLException {
        String personalInfo[] = new String[2] ;
        String[] columns = new String[] {KEY_TABLE_ID, KEY_FIRST_NAME, KEY_LAST_NAME};
        Cursor c = sqLiteDatabase.query(DATABASE_TABLE, columns, KEY_TABLE_ID + "=" + rowId, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
            personalInfo[0] = c.getString(1);
            personalInfo[1] = c.getString(2);
            return personalInfo;
        }
        return null;
    }

}
