package com.polinema.bukukasmasaldi.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.snack(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}