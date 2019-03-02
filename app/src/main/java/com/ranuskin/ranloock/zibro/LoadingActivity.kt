package com.ranuskin.ranloock.zibro

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ranuskin.ranloock.zibro.DB.Libraries.EventsLibrary
import com.ranuskin.ranloock.zibro.DB.Libraries.SignedInUser
import android.content.pm.PackageManager
import android.content.pm.PackageInfo
import android.util.Base64
import android.util.Log
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class LoadingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)



        this.runOnUiThread { EventsLibrary.getAllEvents {
            SignedInUser.isUserConnected { didSucceed ->
                if (didSucceed) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }

        }
        }

    }


}
