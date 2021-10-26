package com.polinema.bukukasmasaldi.ui.detailcashflow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.polinema.bukukasmasaldi.data.LocalDataSource
import com.polinema.bukukasmasaldi.data.model.Transaction
import com.polinema.bukukasmasaldi.utils.MappingHelper

class DetailCashFlowViewModel(private val localDataSource: LocalDataSource) : ViewModel() {

    private val _allTransaction = MutableLiveData<List<Transaction>>()
    val allTransaction: LiveData<List<Transaction>> = _allTransaction

    fun getAllTransaction() {
        val transactionCursor = localDataSource.getAllTransaction()
        val listTransaction = MappingHelper.mapCursorToArrayListTransaction(transactionCursor)

        _allTransaction.postValue(listTransaction)
    }

}