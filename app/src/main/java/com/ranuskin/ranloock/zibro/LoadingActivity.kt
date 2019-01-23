package com.ranuskin.ranloock.zibro

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ranuskin.ranloock.zibro.DB.Libraries.EventsLibrary

class LoadingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
        EventsLibrary.getMyEvents {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
