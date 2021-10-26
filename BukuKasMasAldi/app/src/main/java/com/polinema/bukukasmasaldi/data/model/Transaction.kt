package com.polinema.bukukasmasaldi.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Transaction(
    var id: Int = 0,
    var date: String? = null,
    var amount: Int? = null,
    var description: String? = null,
    var isExpanse: Int? = null
): Parcelable
