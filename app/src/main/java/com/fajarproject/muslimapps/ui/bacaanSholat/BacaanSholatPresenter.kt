package com.fajarproject.muslimapps.ui.bacaanSholat

import android.util.Log
import com.fajarproject.muslimapps.models.sholat.ModelNiatSholat
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

/**
 * Created by Fajar Adi Prasetyo on 20/05/2022.
 */

class BacaanSholatPresenter(var view: BacaanSholatView) {
    private val db = Firebase.firestore

    fun getData() {
        view.showLoading()
        db.collection("bacaan_sholat")
            .get()
            .addOnSuccessListener { result ->
                view.hideLoading()
                val list: MutableList<ModelNiatSholat> = ArrayList()
                for (document in result) {
                    val model = ModelNiatSholat(
                        document.data["id"] as Long,
                        document.data["name"] as String?,
                        document.data["arabic"] as String?,
                        document.data["latin"] as String?,
                        document.data["terjemahan"] as String?
                    )
                    list.add(model)
                }
                list.sortWith { data1, data2 -> if (data1.id < data2.id) -1 else 0 }
                view.showDataSuccess(list)
            }
            .addOnFailureListener { exception ->
                Log.e("database", "Error getting documents.", exception)
                view.hideLoading()
                view.showDataFailed(exception.message ?: "")
            }
    }
}