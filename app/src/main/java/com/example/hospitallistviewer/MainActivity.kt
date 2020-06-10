package com.example.hospitallistviewer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hospitallistviewer.db.Hospital

class MainActivity : AppCompatActivity() {

    private lateinit var hospitalViewModel: HospitalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hospitalViewModel = ViewModelProvider(this).get(HospitalViewModel::class.java)
        hospitalViewModel.loadCSV()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = HospitalListAdapter(this){ hospital: Hospital, position: Int ->
            hospitalItemClicked(hospital, position)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        hospitalViewModel.allHospitals.observe(this, Observer { hospitals ->
            // Update the cached copy of the words in the adapter.

            hospitals?.let { adapter.setHospitals(hospitals) }
        })

    }

    private fun hospitalItemClicked(hospital: Hospital, position: Int){
        println("XXXXXXXX" + hospital.organisation_name)

    }
}