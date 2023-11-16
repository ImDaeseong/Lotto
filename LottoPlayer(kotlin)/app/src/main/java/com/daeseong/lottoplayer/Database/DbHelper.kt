package com.daeseong.lottoplayer.Database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DbHelper private constructor(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {

        private val tag = DbHelper::class.java.name

        // Database version
        private const val DATABASE_VERSION = 1

        // Database name
        private const val DATABASE_NAME = "LottoDB.db"

        private var database: SQLiteDatabase? = null

        private var instance: DbHelper? = null

        @Synchronized
        fun getInstance(context: Context): DbHelper {
            if (instance == null) {
                instance = DbHelper(context.applicationContext)
            }
            return instance!!
        }
    }

    init {

        Log.e(tag, "init")

        try {
            database = SQLiteDatabase.openDatabase(context.getDatabasePath(DATABASE_NAME).toString(),null, SQLiteDatabase.OPEN_READWRITE)
        } catch (e: Exception) {
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        Log.e(tag, "onCreate")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.e(tag, "onUpgrade")
    }

    @Synchronized
    override fun close() {
        database?.close()
        super.close()
    }
}
