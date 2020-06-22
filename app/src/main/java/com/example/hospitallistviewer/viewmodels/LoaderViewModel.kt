package com.example.hospitallistviewer.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.hospitallistviewer.APIService
import com.example.hospitallistviewer.db.Hospital
import com.example.hospitallistviewer.db.HospitalDatabase
import com.example.hospitallistviewer.db.HospitalRepo
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class LoaderViewModel(application: Application) : AndroidViewModel(application){
    private val repository: HospitalRepo


    var dataIsLoaded: MutableLiveData<Boolean> = MutableLiveData(false)

    init{
        val hospitalDao = HospitalDatabase.getDatabase(application, viewModelScope).hospitalDao()
        repository = HospitalRepo(hospitalDao)

    }


    private fun insertHospital(hospital: Hospital) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(hospital)
    }

    private fun deleteAllExistingHospitals() = viewModelScope.launch(Dispatchers.IO){
        repository.deleteAll()
    }


    fun getDataFromAPI(){
        doAsync {
            APIService()
                .getCSVFile(){ success, data ->
                    if (success == true){
                        uiThread {
                            persistDataToRoom(data!!)
                        }
                    }
                    else {
                        uiThread {
                            println("error happened")
                        }
                    }
                }
        }
    }

    fun persistDataToRoom(theData: String){
        val tsvReader = csvReader {
            charset = "UTF-8"
            quoteChar = '"'
            delimiter = '\t'
            escapeChar = '\\'
        }

        val rows: List<List<String>> = tsvReader.readAll(theData)

        var rowNumber = 0
        for (row in rows) {
            if (rowNumber == 0) {
                rowNumber++
                continue
            }
            val rowData = row.get(0).split('�')

            val organisation_id = rowData.get(0)
            val organisation_code = rowData.get(1)
            val organisation_type = rowData.get(2)
            val sub_type = rowData.get(3)
            val sector = rowData.get(4)
            val organisation_status = rowData.get(5)
            val is_pims_managed = rowData.get(6)
            val organisation_name = rowData.get(7)
            val address1 = rowData.get(8)
            val address2 = rowData.get(9)
            val address3 = rowData.get(10)
            val city = rowData.get(11)
            val county = rowData.get(12)
            val postcode = rowData.get(13)
            val latitude = rowData.get(14)
            val longitude = rowData.get(15)
            val parent_ods_code = rowData.get(16)
            val parent_name = rowData.get(17)
            val phone = rowData.get(18)
            val email = rowData.get(19)
            val website = rowData.get(20)
            val fax = rowData.get(21)

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

        dataIsLoaded.value = true
    }

}