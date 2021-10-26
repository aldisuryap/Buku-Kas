package com.polinema.bukukasmasaldi.di

import com.polinema.bukukasmasaldi.data.LocalDataSource
import org.koin.dsl.module

val localDataSourceModule = module {
    single { LocalDataSource(get(), get()) }
}