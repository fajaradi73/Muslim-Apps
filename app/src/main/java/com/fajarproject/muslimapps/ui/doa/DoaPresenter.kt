package com.fajarproject.muslimapps.ui.doa

import android.util.Log
import com.fajarproject.muslimapps.models.doa.ModelDoa
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

/**
 * Created by Fajar Adi Prasetyo on 23/05/2022.
 */

class DoaPresenter(var view: DoaView) {
    private val db = Firebase.firestore
    fun getData() {
        view.showLoading()
        db.collection("doa")
            .orderBy("id")
            .get()
            .addOnSuccessListener { result ->
                view.hideLoading()
                val list: MutableList<ModelDoa> = ArrayList()
                for (document in result) {
                    val model = ModelDoa(
                        document.data["id"] as Long,
                        document.data["title"] as String?,
                        document.data["arabic"] as String?,
                        document.data["latin"] as String?,
                        document.data["translation"] as String?
                    )
                    list.add(model)
                }
//                list.sortWith { data1, data2 -> if (data1.id < data2.id) -1 else 0 }
                view.showDataSuccess(list)
            }
            .addOnFailureListener { exception ->
                Log.e("database", "Error getting documents.", exception)
                view.hideLoading()
                view.showDataFailed(exception.message ?: "")
            }
    }
}