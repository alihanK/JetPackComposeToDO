package com.package.todoappkotlin.di

import android.content.Context
import com.package.todoappkotlin.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {
    @Provides
    @Singleton
    fun provideContext(): Context = app.applicationContext
}
