package com.fajarproject.muslimapps.ui.widget

import android.annotation.SuppressLint
import android.location.Geocoder
import android.os.AsyncTask
import android.util.Log
import com.fajarproject.muslimapps.ui.home.MainActivity
import java.io.IOException
import java.util.*

/**
 * Created by Fajar Adi Prasetyo on 19/05/2022.
 */

@SuppressLint("StaticFieldLeak")
class CurrentLocation(
    var activity: MainActivity,
    var latitude: Double,
    var longitude: Double
) : AsyncTask<Double, Void, String>() {
    private val geocoder = Geocoder(activity, Locale.getDefault())

    @Deprecated("Deprecated in Java")
    override fun doInBackground(vararg params: Double?): String? {
        return try {
            val addressList = geocoder.getFromLocation(latitude, longitude, 1)
//            Log.v("DataKota","$addressList ")
            if (addressList != null && addressList.size > 0) {
                addressList[0].locality
            } else {
                null
            }
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        if (result != null) {
            activity.mainBinding.tvLocation.text = result
        }
    }
}