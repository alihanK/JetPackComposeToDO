package com.package.todoappkotlin

import android.app.Application
import com.package.todoappkotlin.di.AppComponent
import com.package.todoappkotlin.di.AppModule
import com.package.todoappkotlin.di.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        // Dagger 2: AppComponent üzerinden DaggerAppComponent otomatik oluşturulacak
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}
