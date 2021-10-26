package com.polinema.bukukasmasaldi

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import com.polinema.bukukasmasaldi.di.databaseModule
import com.polinema.bukukasmasaldi.di.localDataSourceModule
import com.polinema.bukukasmasaldi.di.preferenceModule
import com.polinema.bukukasmasaldi.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    preferenceModule,
                    localDataSourceModule,
                    viewModelModule
                )
            )
        }

    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this@MyApplication)
            .crossfade(true)
            .build()
    }
}