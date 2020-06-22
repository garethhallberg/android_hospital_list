package com.example.hospitallistviewer.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hospitallistviewer.R
import com.example.hospitallistviewer.viewmodels.LoaderViewModel
import kotlinx.android.synthetic.main.activity_loader.*

class LoaderActivity : AppCompatActivity() {

    private lateinit var loaderViewModel: LoaderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loader)
        loaderViewModel = ViewModelProvider(this).get(LoaderViewModel::class.java)
        setUpScreen()
    }


    fun setUpScreen(){
        setupLiveDataObserver()
        loader_btn.visibility = View.INVISIBLE
        loader_btn.setOnClickListener {
            didTapShowList()
        }
    }

    fun setupLiveDataObserver(){
        loaderViewModel.dataIsLoaded.observe(this , Observer {
            if (it == true){
                loader_btn.visibility = View.VISIBLE
                loader_text.visibility = View.INVISIBLE
            }
        })
    }



    fun didTapShowList(){
        val intent = Intent(this, HospitalListActivity::class.java)
        startActivity(intent)
    }




}