package com.polinema.bukukasmasaldi.di

import androidx.datastore.preferences.preferencesDataStore
import com.polinema.bukukasmasaldi.utils.PreferenceConstants
import com.polinema.bukukasmasaldi.preferences.UserPreference
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val preferenceModule = module {
    single {
        preferencesDataStore(name = PreferenceConstants.DATASTORE_NAME).getValue(
            androidContext(),
            UserPreference::javaClass
        )
    }

    single { UserPreference(get()) }
}