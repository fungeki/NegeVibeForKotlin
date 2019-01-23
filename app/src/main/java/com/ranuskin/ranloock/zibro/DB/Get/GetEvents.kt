package com.ranuskin.ranloock.zibro.DB.Get

import android.util.EventLog
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.ranuskin.ranloock.zibro.Objects.ZibroEvent
import okhttp3.*
import org.json.JSONArray
import java.io.IOException

fun getEvents(completion: (List<ZibroEvent>) -> Unit){
    println("executing get events")
    val url = "https://doodahackathon.herokuapp.com/eventsdb"

    val request = Request.Builder().url(url).build()

    val client = OkHttpClient()
    client.newCall(request).enqueue(object: Callback{
        override fun onFailure(call: Call, e: IOException) {
            println("failed to execute request $e")
        }

        override fun onResponse(call: Call, response: Response) {
            val body = response.body()?.string()
            val gson = GsonBuilder().create()
            //val eventList = listOf(gson.fromJson(body, ZibroEvent::class.java))
            val eventList: List<ZibroEvent> = gson.fromJson(body, Array<ZibroEvent>::class.java).toList()
            completion(eventList)

        }

    })

}