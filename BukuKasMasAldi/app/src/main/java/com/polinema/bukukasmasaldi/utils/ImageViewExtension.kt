package com.polinema.bukukasmasaldi.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import coil.imageLoader
import coil.load
import coil.transform.CircleCropTransformation

fun ImageView.loadAsCircleFromDrawable(drawableId: Int) {
    val imageLoader = context.imageLoader

    this.load(
        drawableResId = drawableId,
        imageLoader = imageLoader
    ) {
        transformations(CircleCropTransformation())
    }
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}