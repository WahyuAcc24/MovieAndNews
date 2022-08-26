package com.wr15.mytestproject.adapter

import android.view.View

interface ItemClickListener<M> {
    fun onClicked(flm: M, position: Int, view: View?)
}