package com.polinema.bukukasmasaldi.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.polinema.bukukasmasaldi.data.db.DatabaseContract.TransactionColumns.Companion.AMOUNT
import com.polinema.bukukasmasaldi.data.db.DatabaseContract.TransactionColumns.Companion.DATE
import com.polinema.bukukasmasaldi.data.db.DatabaseContract.TransactionColumns.Companion.DESCRIPTION
import com.polinema.bukukasmasaldi.data.db.DatabaseContract.TransactionColumns.Companion.IS_EXPANSE
import com.polinema.bukukasmasaldi.data.db.DatabaseContract.UserColumns.Companion.PASSWORD
import com.polinema.bukukasmasaldi.data.db.DatabaseContract.UserColumns.Companion.USERNAME
import com.polinema.bukukasmasaldi.data.db.DatabaseContract.TransactionColumns.Companion.TABLE_NAME as TABLE_TRANSACTION
import com.polinema.bukukasmasaldi.data.db.DatabaseContract.UserColumns.Companion.TABLE_NAME as TABLE_USER
import com.polinema.bukukasmasaldi.data.db.DatabaseContract.TransactionColumns.Companion._ID as ID_TRANSACTION
import com.polinema.bukukasmasaldi.data.db.DatabaseContract.UserColumns.Companion._ID as ID_USER

internal class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE_TRANSACTION)
        db?.execSQL(SQL_CREATE_TABLE_USER)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_TRANSACTION")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
        onCreate(db)
    }

    companion object {

        private const val DATABASE_NAME = "bukukasnusantara.db"

        private const val DATABASE_VERSION = 1

        private const val SQL_CREATE_TABLE_TRANSACTION = "CREATE TABLE $TABLE_TRANSACTION " +
                "($ID_TRANSACTION INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$DATE TEXT NOT NULL, " +
                "$AMOUNT INTEGER NOT NULL, " +
                "$DESCRIPTION TEXT NOT NULL, " +
                "$IS_EXPANSE INTEGER NOT NULL)"

        private const val SQL_CREATE_TABLE_USER = "CREATE TABLE $TABLE_USER " +
                "($ID_USER INTEGER PRIMARY KEY, " +
                "$USERNAME TEXT NOT NULL, " +
                "$PASSWORD TEXT NOT NULL)"
    }

}