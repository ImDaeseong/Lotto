package com.im.daeseong.lottoplayer.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    private static final String TAG = DbHelper.class.getSimpleName();

    //데이타베이스 버전
    private static final int DATABASE_VERSION = 1;

    //데이타베이스 이름
    private static final String DATABASE_NAME = "LottoDB.db";

    private SQLiteDatabase database;

    private static DbHelper instance;
    public static DbHelper getInstance(Context context ) {
        if (instance == null) {
            instance = new DbHelper(context);
        }
        return instance;
    }

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        try {
            database = SQLiteDatabase.openDatabase(context.getDatabasePath(DATABASE_NAME).toString(), null, SQLiteDatabase.OPEN_READWRITE);
        }catch (Exception e){
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*
        try {

            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            String createTable = "CREATE TABLE " +
                    TABLE_NAME + "("
                    + COLUMN_RINDEX + " INTEGER PRIMARY KEY, "
                    + COLUMN_DATA + " TEXT NOT NULL, "
                    + COLUMN_PART1 + " INTEGER, "
                    + COLUMN_PART2 + " INTEGER, "
                    + COLUMN_PART3 + " INTEGER, "
                    + COLUMN_PART4 + " INTEGER, "
                    + COLUMN_PART5 + " INTEGER, "
                    + COLUMN_PART6 + " INTEGER, "
                    + COLUMN_BONUS + " INTEGER);";
            db.execSQL(createTable);

        }catch (Exception e){
        }
        */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*
        try {

            if(oldVersion == 1 && newVersion == 2){

                db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
                String createTable = "CREATE TABLE " +
                        TABLE_NAME + "("
                        + COLUMN_RINDEX + " INTEGER PRIMARY KEY, "
                        + COLUMN_DATA + " TEXT NOT NULL, "
                        + COLUMN_PART1 + " INTEGER, "
                        + COLUMN_PART2 + " INTEGER, "
                        + COLUMN_PART3 + " INTEGER, "
                        + COLUMN_PART4 + " INTEGER, "
                        + COLUMN_PART5 + " INTEGER, "
                        + COLUMN_PART6 + " INTEGER, "
                        + COLUMN_BONUS + " INTEGER);";
                db.execSQL(createTable);
            }

        }catch (Exception e){
        }
        */
    }

    @Override
    public synchronized void close() {

        if (database != null) {
            database.close();
        }
        super.close();
    }
}


