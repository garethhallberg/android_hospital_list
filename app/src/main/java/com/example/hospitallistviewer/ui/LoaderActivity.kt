package com.example.hospitallistviewer.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hospitallistviewer.R
import com.example.hospitallistviewer.viewmodels.LoaderViewModel

class LoaderActivity : AppCompatActivity() {

    private lateinit var loaderViewModel: LoaderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loader)


        loaderViewModel = ViewModelProvider(this).get(LoaderViewModel::class.java)
        loaderViewModel.getDataFromAPI()

        loaderViewModel.dataIsLoaded.observe(this , Observer {
            if (it == true){
                loadHospitalList()
            }
        })
    }


    fun loadHospitalList(){
        val intent = Intent(this, HospitalListActivity::class.java)

        startActivity(intent)
    }

}