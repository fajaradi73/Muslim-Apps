package com.fajarproject.muslimapps.ui.alquran

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.fajarproject.muslimapps.databinding.AdapterSurahBinding
import com.fajarproject.muslimapps.models.alquran.ModelSurah
import com.fajarproject.muslimapps.ui.base.AdapterHolder
import com.fajarproject.muslimapps.ui.surah.SurahActivity
import com.fajarproject.muslimapps.ui.widget.OnItemClickListener
import com.fajarproject.muslimapps.util.Constant
import org.parceler.Parcels
import java.util.*

/**
 * Created by Fajar Adi Prasetyo on 18/05/2022.
 */

class AlQuranAdapter(var activity: Activity, private var list: MutableList<ModelSurah>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {
    private var listSurah: List<ModelSurah> = ArrayList(list)

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AdapterHolder(
        AdapterSurahBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ), this.onItemClickListener
    )

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = AdapterSurahBinding.bind(holder.itemView)
        val data = list[position]
        binding.tvNumber.text = data.nomor
        binding.tvName.text = data.asma
        binding.tvAyat.text = data.nama
        binding.tvInfo.text = data.type + " - " + data.ayat + " Ayat "
        binding.cvSurah.setOnClickListener {
            val intent = Intent(activity, SurahActivity::class.java)
            intent.putExtra(Constant.DetailSurah, Parcels.wrap(data))
            activity.startActivity(intent)
        }
    }

    override fun getItemCount() = list.size
    override fun getFilter() = listFilter

    private val listFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList: MutableList<ModelSurah> = ArrayList()
            if (constraint.isEmpty()) {
                filteredList.addAll(listSurah)
            } else {
                val filterPattern = constraint.toString().toLowerCase(Locale.getDefault())
                for (modelDoaFilter in listSurah) {
                    if (modelDoaFilter.nama?.toLowerCase(Locale.getDefault())
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
            list = results.values as MutableList<ModelSurah>
            notifyDataSetChanged()
        }
    }
}