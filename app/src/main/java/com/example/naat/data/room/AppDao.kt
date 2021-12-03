package com.example.naat.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.naat.data.model.entity.CompanyListEntity

@Dao
interface AppDao {

    @Query("SELECT * FROM Company")
    suspend fun getAllSelect() : List<CompanyListEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dogEntity: List<CompanyListEntity>)
}