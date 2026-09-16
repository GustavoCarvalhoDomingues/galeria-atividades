package com.example.appgaleria.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PhotoEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun photoDao(): PhotoDao
    companion object { fun create(context: Context) = Room.databaseBuilder(context, AppDatabase::class.java, "gallery.db").build() }
}
