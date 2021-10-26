package com.polinema.bukukasmasaldi.di

import com.polinema.bukukasmasaldi.data.db.TransactionHelper
import com.polinema.bukukasmasaldi.data.db.UserHelper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        TransactionHelper(androidContext())
    }

    single {
        UserHelper(androidContext())
    }
}