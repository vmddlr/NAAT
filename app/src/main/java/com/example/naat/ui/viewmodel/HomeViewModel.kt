package com.example.naat.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.naat.R
import com.example.naat.data.model.entity.CompanyListEntity
import com.example.naat.data.room.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val room: RoomRepository
) : ViewModel() {

    var mldListCompany = MutableLiveData<List<CompanyListEntity>>()

    fun onCreate() {
        viewModelScope.launch {
            var dataBase = room.getAllSelectRepository()

            if (dataBase.isEmpty()) {
                room.insertRepository(insertData())
                dataBase = room.getAllSelectRepository()
            }

            mldListCompany.postValue(dataBase)
        }
    }

    private fun insertData(): List<CompanyListEntity> {
        return listOf(
            CompanyListEntity(
                title = "CLARO",
                image = R.drawable.ic_claro,
                description =  "Megas"
            ),
            CompanyListEntity(
                title = "CLARO",
                image = R.drawable.ic_claro,
                description =  "Megas"
            ),
            CompanyListEntity(
                title = "CLARO",
                image = R.drawable.ic_claro,
                description =  "Tiempo aire"
            ),
            CompanyListEntity(
                title = "TUENTI",
                image = R.drawable.ic_tuenti,
                description =  "Tiempo aire"
            ),
            CompanyListEntity(
                title = "TUENTI",
                image = R.drawable.ic_tuenti,
                description =  "Tiempo aire"
            ),
            CompanyListEntity(
                title = "TUENTI",
                image = R.drawable.ic_tuenti,
                description =  "Tiempo aire"
            ),
            CompanyListEntity(
                title = "ENTEL",
                image = R.drawable.ic_entel,
                description =  "Tiempo aire"
            ),
            CompanyListEntity(
                title = "ENTEL",
                image = R.drawable.ic_entel,
                description =  "Tiempo aire"
            ),
            CompanyListEntity(
                title = "ENTEL",
                image = R.drawable.ic_entel,
                description =  "Tiempo aire"
            ),
        )
    }
}