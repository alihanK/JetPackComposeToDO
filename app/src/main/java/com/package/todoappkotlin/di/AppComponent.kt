package com.package.todoappkotlin.di

import com.package.todoappkotlin.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DatabaseModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}
