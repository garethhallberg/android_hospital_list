package com.example.hospitallistviewer.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HospitalDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(hospital: Hospital)

    @Query("DELETE FROM hospital_table")
    suspend fun deleteAll()

    @Insert
    suspend fun insertAll(hospitals: List<Hospital>)


    @Query("SELECT * from hospital_table ORDER BY organisation_name ASC")
    fun getHospitals(): LiveData<List<Hospital>>

    @Query("SELECT * FROM hospital_table WHERE organisation_code = :organisation_code ")
    fun getHospitalsByOrganisationCode(organisation_code: String): Hospital

    @Query("SELECT * FROM hospital_table WHERE county = :county ")
    fun getHospitalsByCounty(county: String): LiveData<List<Hospital>>
}