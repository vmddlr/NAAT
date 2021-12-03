package com.example.naat.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Company")
data class CompanyListEntity(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    var title: String,
    var image : Int,
    var description : String,
) : Serializable