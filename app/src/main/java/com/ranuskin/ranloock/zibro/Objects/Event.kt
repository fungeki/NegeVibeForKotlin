package com.ranuskin.ranloock.zibro.Objects

import com.google.gson.annotations.SerializedName


class ZibroEvent(
    val id: Int,
    val title: String,
    val description: String,
    val organizerid: Int,
    val type: Int,
    val price: Int,
    val date: String
) {

}