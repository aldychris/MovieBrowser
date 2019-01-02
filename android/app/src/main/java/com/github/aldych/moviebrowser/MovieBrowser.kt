package com.github.aldych.moviebrowser

import android.content.Context
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import data.api.MovieApiService
import data.repository.MovieBrowserRepositoryImpl
import data.repository.MovieBrowserRepository
import org.jetbrains.anko.AnkoLogger

class MovieBrowser: MultiDexApplication(), AnkoLogger {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    private val api = MovieApiService("https://www.omdbapi.com", YourApiKey.API_KEY)

    val dataRepository: MovieBrowserRepository by lazy {
        MovieBrowserRepositoryImpl(api)
    }

}