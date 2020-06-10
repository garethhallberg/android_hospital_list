package com.example.hospitallistviewer.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hospital_table")
class Hospital(@PrimaryKey @ColumnInfo(name = "hospital")
               val organisation_id: String,
               val organisation_code: String,
               val organisation_type: String,
               val sub_type: String,
               val sector: String,
               val organisation_status: String,
               val is_pims_managed: String,
               val organisation_name: String,
               val address1: String,
               val address2: String?,
               val address3: String?,
               val city: String?,
               val county: String?,
               val postcode: String?,
               val latitude: String?,
               val longitude: String?,
               val parent_ods_code: String?,
               val parent_name: String?,
               val phone: String?,
               val email: String?,
               val website: String?,
               val fax: String?
)