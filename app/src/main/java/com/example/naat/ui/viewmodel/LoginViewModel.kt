package com.example.naat.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.naat.data.model.Response.UserResponse
import com.example.naat.data.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
): ViewModel() {

    var mldUser = MutableLiveData<UserResponse>()

    fun getUserViewModel(userName: String, password: String){
        viewModelScope.launch {
            val response = loginRepository.getUserRepository(
                userName,
                password
            )
            mldUser.postValue(response)
        }
    }
}