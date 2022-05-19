package com.fajarproject.muslimapps.ui.jadwalsholat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.fajarproject.muslimapps.models.sholat.DaftarKota

/**
 * Created by Fajar Adi Prasetyo on 19/05/2022.
 */

class KotaAdapter(
    context: Context,
    @LayoutRes private val layoutResource: Int,
    var list: List<DaftarKota>
) :
    ArrayAdapter<DaftarKota>(context, layoutResource, list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createViewFromResource(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createViewFromResource(position, convertView, parent)
    }

    private fun createViewFromResource(
        position: Int,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val view: TextView = convertView as TextView? ?: LayoutInflater.from(context)
            .inflate(layoutResource, parent, false) as TextView
        view.text = list[position].name
        return view
    }
}