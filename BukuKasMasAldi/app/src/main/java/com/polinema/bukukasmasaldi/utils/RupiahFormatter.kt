package com.polinema.bukukasmasaldi.utils

import java.text.NumberFormat
import java.util.*

fun Int.toRupiah() : String {
    val localeID = Locale("in", "ID")

    val rupiahFormat = NumberFormat.getCurrencyInstance(localeID)
    return rupiahFormat.format(this)
}