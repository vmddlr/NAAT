package com.example.naat.data.room

import com.example.naat.data.model.entity.CompanyListEntity
import javax.inject.Inject

class RoomRepository @Inject constructor(
private val appDao: AppDao
) {

    suspend fun getAllSelectRepository(): List<CompanyListEntity> {
        return this.appDao.getAllSelect()
    }

    suspend fun insertRepository(list: List<CompanyListEntity>) {
        this.appDao.insert(list)
    }

}