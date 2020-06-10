package com.example.hospitallistviewer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hospitallistviewer.db.Hospital
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var hospitalViewModel: HospitalViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hospitalViewModel = ViewModelProvider(this).get(HospitalViewModel::class.java)
        hospitalViewModel.loadCSV()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)

        val adapter = HospitalListAdapter(this){ hospital: Hospital ->
            hospitalItemClicked(hospital)
        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        hospitalViewModel.allHospitals.observe(this, Observer { hospitals ->
            // Update the cached copy of the words in the adapter.

            hospitals?.let { adapter.setHospitals(hospitals) }
        })

        btn_show_all.setOnClickListener {
            hospitalViewModel.allHospitals.observe(this, Observer { hospitals ->
                hospitals?.let { adapter.setHospitals(hospitals) }
            })
        }

        btn_show_cornwall.setOnClickListener {


            hospitalViewModel.allHospitals.observe(this, Observer { hospitals ->
                // Update the cached copy of the words in the adapter.
                val lessHospitals = hospitals.filter {  hospital ->
                    hospital.county == "Cornwall"
                }
                hospitals?.let { adapter.setHospitals(lessHospitals) }
            })
        }

    }

    private fun hospitalItemClicked(hospital: Hospital){
        val intent = Intent(this, HospitalDetailActivity::class.java)
        intent.putExtra("organisation_code", hospital.organisation_code)


        startActivity(intent)

    }
}