package com.example.naat.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.naat.data.model.entity.CompanyListEntity

@Database(
    entities = [CompanyListEntity::class],
    version = 1
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun appDao(): AppDao
}