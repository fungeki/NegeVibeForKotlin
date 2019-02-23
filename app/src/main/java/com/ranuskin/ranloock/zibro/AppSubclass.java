package com.ranuskin.ranloock.zibro;

import android.app.Application;
import com.google.firebase.FirebaseApp;

public class AppSubclass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        //asdasd
    }
}
