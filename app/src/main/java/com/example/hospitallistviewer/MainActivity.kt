package com.example.hospitallistviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var hospitalViewModel: HospitalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hospitalViewModel = ViewModelProvider(this).get(HospitalViewModel::class.java)
        hospitalViewModel.loadCSV()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = HospitalListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        hospitalViewModel.allHospitals.observe(this, Observer { hospitals ->
            // Update the cached copy of the words in the adapter.
//            val lessHospitals = hospitals.filter {  hospital ->
//                hospital.city == "Beverley"
//            }
            hospitals?.let { adapter.setHospitals(hospitals) }
        })




    }
}