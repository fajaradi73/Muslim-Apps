package com.fajarproject.muslimapps

import android.app.Application
import com.google.firebase.FirebaseApp

/**
 * Created by Fajar Adi Prasetyo on 20/05/2022.
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}