package com.example.hospitallistviewer.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.hospitallistviewer.db.Hospital
import com.example.hospitallistviewer.db.HospitalDatabase
import com.example.hospitallistviewer.db.HospitalRepo

class HospitalViewModel(application: Application) : AndroidViewModel(application){

    private val repository: HospitalRepo
    var allHospitals: LiveData<List<Hospital>>

    init{
        val hospitalDao = HospitalDatabase.getDatabase(application, viewModelScope).hospitalDao()
        repository = HospitalRepo(hospitalDao)
        allHospitals = repository.allHospitals
    }


    fun getHospitalsByOrganisationCode(organisationCode: String) : Hospital {
        return repository.getHospitalsByOrganisationCode(organisationCode)
    }



}