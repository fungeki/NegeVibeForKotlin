package com.ranuskin.ranloock.zibro

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.FirebaseApp
import com.ranuskin.ranloock.zibro.DB.Libraries.EventsLibrary
import com.ranuskin.ranloock.zibro.DB.Libraries.SignedInUser
import java.text.SimpleDateFormat
import java.util.*

class LoadingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)



        EventsLibrary.getMyEvents {
            SignedInUser.isUserConnected { didSucceed ->
                if (didSucceed) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }

        }
    }
}
