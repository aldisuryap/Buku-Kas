package com.polinema.bukukasmasaldi.utils

import android.database.Cursor
import com.polinema.bukukasmasaldi.data.db.DatabaseContract
import com.polinema.bukukasmasaldi.data.model.Transaction
import com.polinema.bukukasmasaldi.data.model.User

object MappingHelper {
    fun mapCursorToUser(userCursor: Cursor) : User {

        val user = User()

        if (userCursor.moveToFirst() && userCursor.count >= 1) {

            userCursor.apply {
                val id = getInt(getColumnIndexOrThrow(DatabaseContract.UserColumns._ID))
                val username = getString(getColumnIndexOrThrow(DatabaseContract.UserColumns.USERNAME))
                val password = getString(getColumnIndexOrThrow(DatabaseContract.UserColumns.PASSWORD))

                user.id = id
                user.username = username
                user.password = password
            }
        }

        return user
    }

    fun mapCursorToArrayListTransaction(transactionCursor: Cursor) : ArrayList<Transaction> {
        val transactionList = ArrayList<Transaction>()

        transactionCursor.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(DatabaseContract.TransactionColumns._ID))
                val date = getString(getColumnIndexOrThrow(DatabaseContract.TransactionColumns.DATE))
                val amount = getInt(getColumnIndexOrThrow(DatabaseContract.TransactionColumns.AMOUNT))
                val description = getString(getColumnIndexOrThrow(DatabaseContract.TransactionColumns.DESCRIPTION))
                val isExpanse = getInt(getColumnIndexOrThrow(DatabaseContract.TransactionColumns.IS_EXPANSE))

                transactionList.add(Transaction(id, date, amount, description, isExpanse))
            }
        }

        return transactionList
    }
}