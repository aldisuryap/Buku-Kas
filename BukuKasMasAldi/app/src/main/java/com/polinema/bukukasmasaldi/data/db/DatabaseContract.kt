package com.polinema.bukukasmasaldi.data.db

import android.provider.BaseColumns

internal class DatabaseContract {

    // contract for table transaction
    internal class TransactionColumns : BaseColumns {

        companion object {

            const val TABLE_NAME = "transaction_table"
            const val _ID = "_id"
            const val DATE = "date"
            const val AMOUNT = "amount"
            const val DESCRIPTION = "description"
            const val IS_EXPANSE = "isExpanse"

        }

    }

    // contract for table user
    internal class UserColumns : BaseColumns {

        companion object {

            const val TABLE_NAME = "user_table"
            const val _ID = "_id"
            const val USERNAME = "username"
            const val PASSWORD = "password"

        }

    }

}