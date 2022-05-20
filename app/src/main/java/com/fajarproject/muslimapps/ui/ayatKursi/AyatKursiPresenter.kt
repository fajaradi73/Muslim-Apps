package com.fajarproject.muslimapps.ui.ayatKursi

import android.util.Log
import com.fajarproject.muslimapps.models.sholat.ModelNiatSholat
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

/**
 * Created by Fajar Adi Prasetyo on 20/05/2022.
 */

class AyatKursiPresenter(var  view: AyatKursiView) {
    private val db = Firebase.firestore

    fun getData() {
        view.showLoading()
        db.collection("ayat_kursi")
            .get()
            .addOnSuccessListener { result ->
                view.hideLoading()
                val list : MutableList<HashMap<String, String>> = ArrayList()
                for (document in result) {
                   val data : HashMap<String,String> = document.data["data"] as HashMap<String, String>
                    list.add(data)
                }
                list[0].let {
                    it.let { it1 -> view.showDataSuccess(it1) }
                }
            }
            .addOnFailureListener { exception ->
                Log.e("database", "Error getting documents.", exception)
                view.hideLoading()
                view.showDataFailed(exception.message ?: "")
            }
    }
}