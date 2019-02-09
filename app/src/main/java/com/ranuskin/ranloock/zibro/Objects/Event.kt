package com.ranuskin.ranloock.zibro.Objects

import java.io.Serializable

class ZibroEvent(
    val id: Int,
    val title: String,
    val description: String,
    val organizerid: Int,
    val type: Int,
    val price: Int,
    val date: String,
    val datedescription: String,
    val locationname: String,
    val status: Int,
    val images: List<EventImage>,
    var likes: Int
): Serializable {

}