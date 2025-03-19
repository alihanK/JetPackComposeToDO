package com.package.todoappkotlin.di

import android.content.Context
import androidx.room.Room
import com.package.todoappkotlin.data.local.TodoDao
import com.package.todoappkotlin.data.local.TodoDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): TodoDatabase {
        return Room.databaseBuilder(context, TodoDatabase::class.java, "todo_db").build()
    }

    @Provides
    @Singleton
    fun provideTodoDao(database: TodoDatabase): TodoDao = database.todoDao()
}
