package com.example.hospitallistviewer.db

import androidx.lifecycle.LiveData

class HospitalRepo(private val hospitalDao: HospitalDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    var allHospitals: LiveData<List<Hospital>> = hospitalDao.getHospitals()

    fun getHospitalsByOrganisationCode(organisation_code: String): Hospital{
        return hospitalDao.getHospitalsByOrganisationCode(organisation_code)
    }


    suspend fun insert(hospital: Hospital) {
        hospitalDao.insert(hospital)
    }

    suspend fun deleteAll(){
        hospitalDao.deleteAll()
    }

}