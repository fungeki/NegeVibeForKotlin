package com.ranuskin.ranloock.zibro.DB.Post

import android.util.Log
import com.google.gson.GsonBuilder
import com.ranuskin.ranloock.zibro.Objects.ZibroEvent
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import android.R.string
import com.ranuskin.ranloock.zibro.DB.Get.getEvents

val JSON = MediaType.get("application/json; charset=utf-8")

fun postEvent(json: JSONObject) {

    val url = "https://zibro.herokuapp.com/api/events/"

    var client = OkHttpClient()

    val body = RequestBody.create(JSON,json.toString())
    val request = Request.Builder()
        .url(url)
        .post(body)
        .build()

    val call = client.newCall(request)
    call.enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            Log.d("Zibro", "failed to execute request $e")
        }

        override fun onResponse(call: Call, response: Response) {
            if (response.isSuccessful) {
                val body = response.body()?.string()
                Log.d("Zibro",body)

 //               val gson = GsonBuilder().create()
//                val eventList: List<ZibroEvent> = gson.fromJson(body, Array<ZibroEvent>::class.java).toList()
               // getEvents { eventList }
            } else {
                Log.d("Zibro", "failed response")

            }

        }

    })


}



