package com.example.hospitallistviewer.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.hospitallistviewer.R
import com.example.hospitallistviewer.db.Hospital
import com.example.hospitallistviewer.viewmodels.HospitalViewModel
import kotlinx.android.synthetic.main.activity_hospital_detail.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class HospitalDetailActivity : AppCompatActivity() {

    var organisation_code: String? = null
    private lateinit var hospitalViewModel: HospitalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hospital_detail)

        hospitalViewModel = ViewModelProvider(this).get(HospitalViewModel::class.java)
        organisation_code = getIntent().getStringExtra("organisation_code")
        setupScreen(organisation_code)
    }


    private fun setupScreen(organisationCode: String?){
        if (organisationCode != null){
            doAsync {
                val hospital = hospitalViewModel.getHospitalsByOrganisationCode(organisationCode!!)
                uiThread {
                    tv_organisation_name.text = hospital.organisation_name
                    tv_hospital_details.text = formatHospitalDetails(hospital)
                }
            }
        }
    }


    private fun formatHospitalDetails(hospital: Hospital) : String{
        var detailsString = ""

        if (hospital.city != null){
            if (hospital.city!! !=  ""){
                detailsString += hospital.city
            }
        }

        if (hospital.address1 != null){
            if (hospital.address1!! !=  ""){
                detailsString += "\n" + hospital.address1
            }
        }

        if (hospital.address2 != null){
            if (hospital.address2!! !=  ""){
                detailsString += "\n" + hospital.address2
            }
        }

        if (hospital.address3 != null){
            if (hospital.address3!! !=  ""){
                detailsString += "\n" + hospital.address3
            }
        }

        if (hospital.postcode != null){
            if (hospital.postcode!! !=  ""){
                detailsString += "\n" + hospital.postcode
            }
        }

        if (hospital.parent_name != null){
            if (hospital.parent_name!! !=  ""){
                detailsString += "\n" + hospital.parent_name
            }
        }

        if (hospital.sub_type != ""){
            detailsString += "\n" + hospital.sub_type
        }


        if (hospital.sector!! !=  ""){
            detailsString += "\n" + hospital.sector
        }

        if (hospital.phone != null){
            if (hospital.phone!! !=  ""){
                detailsString += "\n" + hospital.phone
            }
        }




        return detailsString
    }

}