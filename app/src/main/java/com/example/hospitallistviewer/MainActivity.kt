package com.example.hospitallistviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var hospitalViewModel: HospitalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hospitalViewModel = ViewModelProvider(this).get(HospitalViewModel::class.java)

        hospitalViewModel.loadCSV()
    }
}