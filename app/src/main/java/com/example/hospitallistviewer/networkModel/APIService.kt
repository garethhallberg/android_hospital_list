package com.example.hospitallistviewer.networkModel

import com.github.kittinunf.fuel.Fuel

class APIService {

    fun getCSVFile( completionHandler: (success: Boolean, data: String?) -> Unit)
    {
        val url = "http://media.nhschoices.nhs.uk/data/foi/Hospital.csv"
        val (_, _, result) =
            Fuel.get(url)
                .responseString()

        result.fold(success = {
            completionHandler(true, it)
            return@fold


        }, failure = {
            println(String(it.errorData))
            completionHandler(false, null)
        })
    }
}