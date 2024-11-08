package com.example.tmdbclient.presentation

import android.app.Application
import com.example.tmdbclient.BuildConfig
import com.example.tmdbclient.presentation.di.AppComponent
import com.example.tmdbclient.presentation.di.AppModule
import com.example.tmdbclient.presentation.di.DaggerAppComponent
import com.example.tmdbclient.presentation.di.Injector
import com.example.tmdbclient.presentation.di.NetModule
import com.example.tmdbclient.presentation.di.RemoteDataModule
import com.example.tmdbclient.presentation.di.movie.MovieSubComponent

class App: Application(), Injector {
    private lateinit var appComponent: AppComponent

    override fun createMovieSubComponent(): MovieSubComponent {
        return appComponent.movieSubComponent().create()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .netModule(NetModule(BuildConfig.BASE_URL))
            .remoteDataModule(RemoteDataModule(BuildConfig.API_KEY))
            .build()
    }
}