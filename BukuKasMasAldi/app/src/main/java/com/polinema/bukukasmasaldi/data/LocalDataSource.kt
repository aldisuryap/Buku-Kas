package com.polinema.bukukasmasaldi.data

import android.content.ContentValues
import com.polinema.bukukasmasaldi.data.db.TransactionHelper
import com.polinema.bukukasmasaldi.data.db.UserHelper

class LocalDataSource(
    private val transactionHelper: TransactionHelper,
    private val userHelper: UserHelper
) {

    init {
        transactionHelper.open()
        userHelper.open()
    }

    fun insertUser(contentValues: ContentValues) = userHelper.insert(contentValues)

    fun getValidUser() = userHelper.queryById("1")

    fun updateUser(contentValues: ContentValues) = userHelper.update("1", contentValues)

    fun insertTransaction(contentValues: ContentValues) = transactionHelper.insert(contentValues)

    fun getAllTransaction() = transactionHelper.queryAll()

}