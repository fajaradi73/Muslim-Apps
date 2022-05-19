package com.fajarproject.muslimapps.ui.masjid

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fajarproject.muslimapps.R
import com.fajarproject.muslimapps.databinding.ActivityMasjidBinding
import com.fajarproject.muslimapps.models.place.ModelResultNearby
import com.fajarproject.muslimapps.util.Util
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import im.delight.android.location.SimpleLocation

/**
 * Created by Fajar Adi Prasetyo on 18/05/2022.
 */

class MasjidActivity : AppCompatActivity(), OnMapReadyCallback, MasjidView {

    private lateinit var binding: ActivityMasjidBinding
    private var strCurrentLatitude = 0.0
    private var strCurrentLongitude = 0.0
    private lateinit var strCurrentLocation: String
    private lateinit var simpleLocation: SimpleLocation
    private lateinit var presenter: MasjidPresenter
    private lateinit var mapsView: GoogleMap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMasjidBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = MasjidPresenter(this, this)
        setUI()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mapsView = googleMap
        mapsView.addMarker(
            MarkerOptions()
                .position(LatLng(strCurrentLatitude, strCurrentLongitude))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .title("Lokasi Anda")
        )
        mapsView.uiSettings.setAllGesturesEnabled(true)
        mapsView.uiSettings.isZoomGesturesEnabled = true
        mapsView.moveCamera(
            CameraUpdateFactory.newLatLng(
                LatLng(
                    strCurrentLatitude,
                    strCurrentLongitude
                )
            )
        )
        mapsView.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(
                    strCurrentLatitude,
                    strCurrentLongitude
                ), 16f
            )
        )
        mapsView.uiSettings.isMyLocationButtonEnabled = true
        mapsView.uiSettings.isZoomControlsEnabled = true
        presenter.getMasjid(strCurrentLocation)
    }

    override fun setUI() {
        simpleLocation = SimpleLocation(this)
        if (!simpleLocation.hasLocationEnabled()) {
            SimpleLocation.openSettings(this)
        }

        //get location
        strCurrentLatitude = simpleLocation.latitude
        strCurrentLongitude = simpleLocation.longitude

        //set location lat long
        strCurrentLocation = "$strCurrentLatitude,$strCurrentLongitude"


        binding.cvBack.setOnClickListener {
            finish()
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun showDataSuccess(data: ModelResultNearby) {
        val list = data.modelResults
        for (i in list.indices) {
            //set LatLong from API
            val latLngMarker = LatLng(
                list[i]
                    .modelGeometry
                    .modelLocation
                    .lat, list[i]
                    .modelGeometry
                    .modelLocation
                    .lng
            )

            //get LatLong to Marker
            mapsView.addMarker(
                MarkerOptions()
                    .position(latLngMarker)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    .title(list[i].name)
            )
        }

    }

    override fun showDataFailed(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        Util.showLoading(this)
    }

    override fun hideLoading() {
        Util.hideLoading()
    }
}