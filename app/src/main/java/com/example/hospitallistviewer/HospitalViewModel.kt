package com.example.hospitallistviewer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.hospitallistviewer.db.Hospital
import com.example.hospitallistviewer.db.HospitalDatabase
import com.example.hospitallistviewer.db.HospitalRepo
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStream

class HospitalViewModel(application: Application) : AndroidViewModel(application){

    private val repository: HospitalRepo

    var allHospitals: LiveData<List<Hospital>>


    init{
        val hospitalDao = HospitalDatabase.getDatabase(application, viewModelScope).hospitalDao()
        repository = HospitalRepo(hospitalDao)
        allHospitals = repository.allHospitals

    }




    fun insertHospital(hospital: Hospital) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(hospital)
    }

    private fun deleteAllExistingHospitals() = viewModelScope.launch(Dispatchers.IO){
        repository.deleteAll()
    }



    fun loadCSV(){
        deleteAllExistingHospitals()

        val ctx = HospitalApplication.appContext
        val myInputStream: InputStream = ctx.assets.open("hospital.csv")

        val tsvReader = csvReader {
            charset = "UTF-8"
            quoteChar = '"'
            delimiter = '\t'
            escapeChar = '\\'
        }

        val rows: List<List<String>> = tsvReader.readAll(myInputStream)
        var rowNumber = 0
        for (row in rows){
            if (rowNumber == 0){
                rowNumber++
                continue
            }

            println(row)
            println(row.get(0))
            val organisation_id = row.get(0)
            val organisation_code = row.get(1)
            val organisation_type = row.get(2)
            val sub_type = row.get(3)
            val sector = row.get(4)
            val organisation_status = row.get(5)
            val is_pims_managed = row.get(6)
            val organisation_name = row.get(7)
            val address1 = row.get(8)
            val address2 = row.get(9)
            val address3 = row.get(10)
            val city = row.get(11)
            val county = row.get(12)
            val postcode = row.get(13)
            val latitude = row.get(14)
            val longitude = row.get(15)
            val parent_ods_code = row.get(16)
            val parent_name = row.get(17)
            val phone = row.get(18)
            val email = row.get(19)
            val website = row.get(20)
            val fax = row.get(21)

            val hospital = Hospital(organisation_id,
                organisation_code,
                organisation_type,
                sub_type,
                sector,
                organisation_status,
                is_pims_managed,
                organisation_name,
                address1,
                address2,
                address3,
                city,
                county,
                postcode,
                latitude,
                longitude,
                parent_ods_code,
                parent_name,
                phone,
                email, website, fax
            )

            insertHospital(hospital)

        }

    }


}