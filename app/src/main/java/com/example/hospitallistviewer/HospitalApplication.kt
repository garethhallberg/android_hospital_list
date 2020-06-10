package com.example.hospitallistviewer

import android.app.Application
import android.content.Context

class HospitalApplication : Application() {

    init {
        instance = this
    }


    companion object {
        private var instance: HospitalApplication? = null

        fun app() : HospitalApplication {
            return instance!!
        }

        lateinit  var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()

        HospitalApplication.appContext = applicationContext


    }




}