package com.polinema.bukukasmasaldi.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polinema.bukukasmasaldi.data.LocalDataSource
import com.polinema.bukukasmasaldi.data.model.Transaction
import com.polinema.bukukasmasaldi.preferences.UserPreference
import com.polinema.bukukasmasaldi.utils.MappingHelper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(
    private val pref: UserPreference,
    private val localDataSource: LocalDataSource
) : ViewModel() {

    private val _currentUsername = MutableLiveData<String?>()
    val currentUsername: LiveData<String?> = _currentUsername

    fun getCurrentUsername() {
        viewModelScope.launch {
            pref.getUsername().collect {
                if(it != null) {
                    _currentUsername.postValue(it)
                }
            }
        }
    }

    private val _allTransaction = MutableLiveData<List<Transaction>>()
    val allTransaction: LiveData<List<Transaction>> = _allTransaction

    fun getAllTransaction() {
        val transactionCursor = localDataSource.getAllTransaction()
        val listTransaction = MappingHelper.mapCursorToArrayListTransaction(transactionCursor)

        _allTransaction.postValue(listTransaction)
    }

}