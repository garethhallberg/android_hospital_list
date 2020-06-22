package com.example.hospitallistviewer.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hospitallistviewer.viewmodels.HospitalViewModel
import com.example.hospitallistviewer.R
import com.example.hospitallistviewer.db.Hospital
import com.example.hospitallistviewer.ui.adapters.HospitalListAdapter
import kotlinx.android.synthetic.main.activity_main.*

class HospitalListActivity : AppCompatActivity() {

    private lateinit var hospitalViewModel: HospitalViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hospitalViewModel = ViewModelProvider(this).get(HospitalViewModel::class.java)
        val adapter = HospitalListAdapter(this) { hospital: Hospital ->
                hospitalItemClicked(hospital)
            }

        setupRecyclerView(adapter)
        showAll(adapter)

       setupButtons(adapter)
    }

    fun setupButtons(adapter: HospitalListAdapter){
        btn_show_all.setOnClickListener {
            showAll(adapter)
        }

        btn_show_cornwall.setOnClickListener {
            showCornwall(adapter)
        }
    }

    fun setupRecyclerView(adapter: HospitalListAdapter){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun showAll(adapter: HospitalListAdapter)
    {
        hospitalViewModel.allHospitals.observe(this, Observer { hospitals ->
            hospitals?.let { adapter.setHospitals(hospitals) }
        })
    }


    fun showCornwall(adapter: HospitalListAdapter){
        hospitalViewModel.allHospitals.observe(this, Observer { hospitals ->
            // Update the cached copy of the words in the adapter.
            val lessHospitals = hospitals.filter {  hospital ->
                hospital.county == "Cornwall"
            }
            hospitals?.let { adapter.setHospitals(lessHospitals) }
        })
    }

    private fun hospitalItemClicked(hospital: Hospital){
        val intent = Intent(this, HospitalDetailActivity::class.java)
        intent.putExtra("organisation_code", hospital.organisation_code)


        startActivity(intent)

    }
}