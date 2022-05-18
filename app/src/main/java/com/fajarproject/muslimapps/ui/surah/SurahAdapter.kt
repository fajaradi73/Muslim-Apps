package com.fajarproject.muslimapps.ui.surah

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fajarproject.muslimapps.databinding.AdapterAyatBinding
import com.fajarproject.muslimapps.models.alquran.ModelAyat
import com.fajarproject.muslimapps.ui.base.AdapterHolder
import com.fajarproject.muslimapps.ui.widget.OnItemClickListener

/**
 * Created by Fajar Adi Prasetyo on 18/05/2022.
 */

class SurahAdapter(var list: MutableList<ModelAyat>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AdapterHolder(
        AdapterAyatBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ), this.onItemClickListener
    )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = AdapterAyatBinding.bind(holder.itemView)
        val data = list[position]
        binding.tvNomorAyat.text = data.nomor
        binding.tvArabic.text = data.arab
        binding.tvTerjemahan.text = data.indo
    }

    override fun getItemCount() = list.size
}