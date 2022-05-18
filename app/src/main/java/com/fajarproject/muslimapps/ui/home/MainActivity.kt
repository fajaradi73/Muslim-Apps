package com.fajarproject.muslimapps.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.text.format.DateFormat
import androidx.appcompat.app.AppCompatActivity
import com.fajarproject.muslimapps.databinding.ActivityMainBinding
import com.fajarproject.muslimapps.ui.alquran.AlQuranActivity
import im.delight.android.location.SimpleLocation
import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var mainBinding: ActivityMainBinding

    private lateinit var presenter: MainPresenter
    private var strCurrentLatitude = 0.0
    private var strCurrentLongitude = 0.0
    private lateinit var strCurrentLatLong: String
    private lateinit var strDate: String
    private lateinit var strDateNow: String
    private lateinit var simpleLocation: SimpleLocation


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        presenter = MainPresenter(this, this)
        setToolbar()
        presenter.getLocationPermission()
    }

    override fun setToolbar() {
        setSupportActionBar(mainBinding.toolbar)
    }

    override fun onResume() {
        super.onResume()
        setUI()
    }

    @SuppressLint("SetTextI18n")
    override fun setUI() {
        setLocation()
        setCurrentLocation()
        val dateNow = Calendar.getInstance().time
        strDate = DateFormat.format("EEEE", dateNow) as String
        strDateNow = DateFormat.format("d MMMM yyyy", dateNow) as String

        mainBinding.tvToday.text = "$strDate,"
        mainBinding.tvDate.text = strDateNow
        setAction()
    }

    override fun setAction() {
        mainBinding.cvQuran.setOnClickListener {
            startActivity(Intent(this,AlQuranActivity::class.java))
        }
        mainBinding.cvMasjid.setOnClickListener {

        }
        mainBinding.cvJadwal.setOnClickListener {

        }
        mainBinding.cvDoa.setOnClickListener {

        }
        mainBinding.cvNiatSholat.setOnClickListener {

        }
        mainBinding.cvBacaanSholat.setOnClickListener {

        }
        mainBinding.cvAyatQursy.setOnClickListener {

        }
    }

    override fun setLocation() {
        simpleLocation = SimpleLocation(this)
        if (!simpleLocation.hasLocationEnabled()) {
            SimpleLocation.openSettings(this)
        }

        //get location
        strCurrentLatitude = simpleLocation.latitude
        strCurrentLongitude = simpleLocation.longitude

        //set location lat long
        strCurrentLatLong = "$strCurrentLatitude,$strCurrentLongitude"
    }

    override fun setCurrentLocation() {
        val geocoder = Geocoder(this, Locale.getDefault())
        try {
            val addressList = geocoder.getFromLocation(strCurrentLatitude, strCurrentLongitude, 1)
            if (addressList != null && addressList.size > 0) {
                val strCurrentLocation = addressList[0].locality
                mainBinding.tvLocation.text = strCurrentLocation
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        for (grantResult in grantResults) {
            if (grantResult == PackageManager.PERMISSION_GRANTED) {
                val intent = intent
                finish()
                startActivity(intent)
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 100) {
            setUI()
        }
    }
}