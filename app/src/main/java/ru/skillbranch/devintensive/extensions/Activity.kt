package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var v = this.currentFocus
    if(v == null) {
        v = View(this)
    }
    imm.hideSoftInputFromWindow(v.windowToken, 0)
}

fun Activity.isKeyboardOpen() : Boolean {
    var rootView = this.window.decorView.rootView
    val r = Rect()
    rootView.getWindowVisibleDisplayFrame(r)
    var heightDiff = rootView.rootView.height - (r.bottom - r.top)
    return heightDiff > 0.25 * rootView.rootView.height
}