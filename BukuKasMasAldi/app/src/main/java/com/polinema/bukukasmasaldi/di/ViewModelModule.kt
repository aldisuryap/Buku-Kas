package com.polinema.bukukasmasaldi.di

import com.polinema.bukukasmasaldi.ui.addtransaction.AddTransactionViewModel
import com.polinema.bukukasmasaldi.ui.detailcashflow.DetailCashFlowViewModel
import com.polinema.bukukasmasaldi.ui.home.HomeViewModel
import com.polinema.bukukasmasaldi.ui.login.LoginViewModel
import com.polinema.bukukasmasaldi.ui.settings.SettingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get(), get()) }
    viewModel { HomeViewModel(get(), get()) }
    viewModel { AddTransactionViewModel(get()) }
    viewModel { DetailCashFlowViewModel(get()) }
    viewModel { SettingViewModel(get()) }
}