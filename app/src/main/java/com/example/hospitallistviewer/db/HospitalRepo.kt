package com.example.hospitallistviewer.db

import androidx.lifecycle.LiveData

class HospitalRepo(private val hospitalDao: HospitalDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allHospitals: LiveData<List<Hospital>> = hospitalDao.getHospitals()

    suspend fun insert(hospital: Hospital) {
        hospitalDao.insert(hospital)
    }

    suspend fun deleteAll(){
        hospitalDao.deleteAll()
    }

}