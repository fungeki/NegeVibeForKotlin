package com.ranuskin.ranloock.zibro.DB.Update

import okhttp3.*
import java.io.IOException


fun incrementLikes(id: Int, isIncremented: Boolean,completion: (Boolean)->Unit){
    if (isIncremented){
        var url = "https://doodahackathon.herokuapp.com/addlike?id=$id"
        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback{
            override fun onFailure(call: Call, e: IOException) {
                completion(false)
            }

            override fun onResponse(call: Call, response: Response) {
                completion(true)
            }
        })
    } else {
        var url = "https://doodahackathon.herokuapp.com/deductlike?id=$id"
        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback{
            override fun onFailure(call: Call, e: IOException) {
                completion(false)
            }

            override fun onResponse(call: Call, response: Response) {
                completion(true)
            }
        })
    }
}