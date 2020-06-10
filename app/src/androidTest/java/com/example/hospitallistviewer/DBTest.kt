package com.example.hospitallistviewer

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import junit.framework.Assert.assertEquals
import com.example.hospitallistviewer.db.Hospital
import com.example.hospitallistviewer.db.HospitalDao
import com.example.hospitallistviewer.db.HospitalDatabase


@RunWith(AndroidJUnit4ClassRunner::class)
public class DBTest {

    @Rule
    @JvmField
    public var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var mHospitalDao: HospitalDao
    private lateinit var mDb: HospitalDatabase


    @Before
    fun createDb() {
        val context =
            ApplicationProvider.getApplicationContext<Context>()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        mDb = Room.inMemoryDatabaseBuilder(
            context,
            HospitalDatabase::class.java
        ) // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        mHospitalDao = mDb!!.hospitalDao()
    }

    @After
    fun closeDb() {
        mDb!!.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetHospital() = runBlocking{
        val hospital = Hospital(
            "1421", "RV9HE",
            "Hospital", "Mental Health Hospital", "NHS Sector",
            "Visible", "TRUE",
            "East Riding Community Hospital", "Swinemoor Lane",
            "", "", "Beverley", "East Yorkshire",
            "HU17 0FA", "53.853134155273400",
            "-0.41147232055664100", "RV9",
            "Humber NHS Foundation Trust", "01482 886600",
            "newhospital@nhs.net", "http://www.humber.nhs.uk", ""
        )
        mHospitalDao!!.insert(hospital)
        val allHospitals =
            LiveDataTestUtil.getValue(mHospitalDao!!.getHospitals())
        assertEquals(allHospitals[0].organisation_code, hospital.organisation_code)
    }


}