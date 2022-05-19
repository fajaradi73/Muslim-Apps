package com.fajarproject.muslimapps.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.format.DateFormat
import androidx.appcompat.app.AppCompatActivity
import com.fajarproject.muslimapps.databinding.ActivityMainBinding
import com.fajarproject.muslimapps.ui.alquran.AlQuranActivity
import com.fajarproject.muslimapps.ui.jadwalsholat.JadwalSholatActivity
import com.fajarproject.muslimapps.ui.masjid.MasjidActivity
import com.fajarproject.muslimapps.ui.widget.CurrentLocation
import im.delight.android.location.SimpleLocation
import java.util.*

class MainActivity : AppCompatActivity(), MainView {

    lateinit var mainBinding: ActivityMainBinding

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

        val dateNow = Calendar.getInstance().time
        strDate = DateFormat.format("EEEE", dateNow) as String
        strDateNow = DateFormat.format("d MMMM yyyy", dateNow) as String

        mainBinding.tvToday.text = "$strDate,"
        mainBinding.tvDate.text = strDateNow
        setAction()
    }

    override fun setAction() {
        mainBinding.cvQuran.setOnClickListener {
            startActivity(Intent(this, AlQuranActivity::class.java))
        }
        mainBinding.cvMasjid.setOnClickListener {
            startActivity(Intent(this, MasjidActivity::class.java))
        }
        mainBinding.cvJadwal.setOnClickListener {
            startActivity(Intent(this, JadwalSholatActivity::class.java))
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
        CurrentLocation(this, strCurrentLatitude, strCurrentLongitude).execute()
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