package com.polinema.bukukasmasaldi.ui.settings

import android.content.ContentValues
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.polinema.bukukasmasaldi.data.LocalDataSource
import com.polinema.bukukasmasaldi.data.model.User
import com.polinema.bukukasmasaldi.utils.MappingHelper

class SettingViewModel(private val localDataSource: LocalDataSource) : ViewModel() {
    private val _currentUser = MutableLiveData<User>()
    val currentUser: LiveData<User> = _currentUser

    fun getCurrentUser() {
        val userCursor = localDataSource.getValidUser()
        val user = MappingHelper.mapCursorToUser(userCursor)

        _currentUser.postValue(user)
    }

    private val _editSuccess = MutableLiveData<Boolean>()
    val editSuccess: LiveData<Boolean> = _editSuccess

    fun editPassword(contentValues: ContentValues) {
        val result = localDataSource.updateUser(contentValues)

        if(result >= 1) {
            _editSuccess.postValue(true)
        }else {
            _editSuccess.postValue(false)
        }
    }
}