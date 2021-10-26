package com.polinema.bukukasmasaldi.utils

import android.content.ContentValues
import com.polinema.bukukasmasaldi.data.db.DatabaseContract

object UserGenerator {
    fun getFirstUser() : ContentValues {
        val userValues = ContentValues()
        userValues.put(DatabaseContract.UserColumns._ID, 1)
        userValues.put(DatabaseContract.UserColumns.USERNAME, "user")
        userValues.put(DatabaseContract.UserColumns.PASSWORD, "user")
        return userValues
    }
}