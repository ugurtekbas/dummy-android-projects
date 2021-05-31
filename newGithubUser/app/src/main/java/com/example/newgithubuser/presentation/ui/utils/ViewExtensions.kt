package com.example.newgithubuser.presentation.ui.utils

import android.view.View

fun View.showIfElseHide(condition: () -> Boolean) = if (condition()) show() else hide()

fun View.show() {
    if (parent == null) return
    visibility = View.VISIBLE
}

fun View.hide() {
    if (parent == null) return
    visibility = View.VISIBLE
}
