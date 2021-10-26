package com.polinema.bukukasmasaldi.ui.login

import android.content.ContentValues
import androidx.lifecycle.*
import com.polinema.bukukasmasaldi.data.LocalDataSource
import com.polinema.bukukasmasaldi.preferences.UserPreference
import com.polinema.bukukasmasaldi.utils.MappingHelper
import kotlinx.coroutines.launch

class LoginViewModel(
    private val pref: UserPreference,
    private val localDataSource: LocalDataSource
) : ViewModel() {

    val isFirstTime = pref.getIsFirstTime().asLiveData()
    fun setIsFirstTime(isFirstTime: Boolean) {
        viewModelScope.launch {
            pref.saveIsFirstTime(isFirstTime)
        }
    }

    fun saveUser(userValues: ContentValues) {
        viewModelScope.launch {
            localDataSource.insertUser(userValues)
        }
    }

    private val _isLoginSuccess = MutableLiveData<Boolean>()
    val isLoginSuccess: LiveData<Boolean> = _isLoginSuccess

    fun doLogin(username: String, password: String) {
        val userCursor = localDataSource.getValidUser()
        val user = MappingHelper.mapCursorToUser(userCursor)

        if (user.username?.equals(username) == true && user.password?.equals(password) == true) {

            viewModelScope.launch {
                pref.saveUsername(username)
            }

            _isLoginSuccess.postValue(true)
        } else {
            _isLoginSuccess.postValue(false)
        }

    }
}