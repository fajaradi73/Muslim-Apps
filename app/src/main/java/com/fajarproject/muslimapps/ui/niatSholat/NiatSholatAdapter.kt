package com.fajarproject.muslimapps.ui.niatSholat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fajarproject.muslimapps.databinding.AdapterNiatSholatBinding
import com.fajarproject.muslimapps.models.sholat.ModelNiatSholat
import com.fajarproject.muslimapps.ui.base.AdapterHolder
import com.fajarproject.muslimapps.ui.widget.OnItemClickListener

/**
 * Created by Fajar Adi Prasetyo on 20/05/2022.
 */

class NiatSholatAdapter(var list: MutableList<ModelNiatSholat>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AdapterHolder(
        AdapterNiatSholatBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ), this.onItemClickListener
    )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = AdapterNiatSholatBinding.bind(holder.itemView)
        val data = list[position]
        binding.txtId.text = data.id.toString()
        binding.txtArabic.text = data.arabic
        binding.txtLatin.text = data.latin
        binding.txtName.text = data.name
        binding.txtTerjemahan.text = data.terjemahan
    }

    override fun getItemCount() = list.size
}