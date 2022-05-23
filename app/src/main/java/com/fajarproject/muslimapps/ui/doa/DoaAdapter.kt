package com.fajarproject.muslimapps.ui.doa

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.fajarproject.muslimapps.databinding.AdapterNiatSholatBinding
import com.fajarproject.muslimapps.models.doa.ModelDoa
import com.fajarproject.muslimapps.ui.base.AdapterHolder
import com.fajarproject.muslimapps.ui.widget.OnItemClickListener
import java.util.*

/**
 * Created by Fajar Adi Prasetyo on 23/05/2022.
 */

class DoaAdapter(var list: MutableList<ModelDoa>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {
    private var modelBacaanListFull: List<ModelDoa> = ArrayList(list)

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
        binding.txtName.text = data.title
        binding.txtTerjemahan.text = data.translation
    }

    private val modelBacaanFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList: MutableList<ModelDoa> = ArrayList()
            if (constraint.isEmpty()) {
                filteredList.addAll(modelBacaanListFull)
            } else {
                val filterPattern = constraint.toString().toLowerCase(Locale.getDefault())
                for (modelDoaFilter in modelBacaanListFull) {
                    if (modelDoaFilter.title?.toLowerCase(Locale.getDefault())
                            ?.contains(filterPattern) == true
                    ) {
                        filteredList.add(modelDoaFilter)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            list.clear()
            list = results.values as MutableList<ModelDoa>
            notifyDataSetChanged()
        }
    }

    override fun getItemCount() = list.size

    override fun getFilter() = modelBacaanFilter
}