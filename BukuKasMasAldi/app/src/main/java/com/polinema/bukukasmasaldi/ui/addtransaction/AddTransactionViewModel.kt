package com.polinema.bukukasmasaldi.ui.addtransaction

import android.content.ContentValues
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.polinema.bukukasmasaldi.data.LocalDataSource

class AddTransactionViewModel(private val localDataSource: LocalDataSource) : ViewModel() {

    private var _insertSuccess = MutableLiveData<Boolean>()
    val insertSuccess: LiveData<Boolean> = _insertSuccess

    fun insert(values: ContentValues) {
        val result = localDataSource.insertTransaction(values)

        if(result > 0) {
            _insertSuccess.postValue(true)
        }else {
            _insertSuccess.postValue(false)
        }
    }

}