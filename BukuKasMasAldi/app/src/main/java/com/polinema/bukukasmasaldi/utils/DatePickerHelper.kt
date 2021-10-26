package com.polinema.bukukasmasaldi.utils

import com.google.android.material.datepicker.MaterialDatePicker

class DatePickerHelper {
    companion object {
        fun getDatePicker() = MaterialDatePicker.Builder.datePicker().apply {
            setTitleText("Pilih Tanggal")
        }.build()
    }
}