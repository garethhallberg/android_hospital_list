package com.example.hospitallistviewer.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hospitallistviewer.R
import com.example.hospitallistviewer.db.Hospital

class HospitalListAdapter internal constructor(
    context: Context,  val clickListener: (Hospital) -> Unit
) : RecyclerView.Adapter<HospitalListAdapter.HospitalHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var hospitals = emptyList<Hospital>()

    override fun getItemCount() = hospitals.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalHolder {
        val hospitalItemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return HospitalHolder(hospitalItemView)
    }

    override fun onBindViewHolder(holder: HospitalHolder, position: Int) {
        val current = hospitals[position]
        (holder).bind(hospitals[position], position, clickListener)

    }

    internal fun setHospitals(hospitals: List<Hospital>) {
        this.hospitals = hospitals
        notifyDataSetChanged()
    }


    inner class HospitalHolder(hospitalItemView: View) : RecyclerView.ViewHolder(hospitalItemView) {
        val hospitalItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(hospital: Hospital, position: Int, clickListener: (Hospital) -> Unit) {

            hospitalItemView.text = hospital.organisation_name
            itemView.setOnClickListener { clickListener(hospital)}

        }
    }


}