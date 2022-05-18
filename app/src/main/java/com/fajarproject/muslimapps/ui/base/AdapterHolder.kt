package com.fajarproject.muslimapps.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.fajarproject.muslimapps.ui.widget.OnItemClickListener

/**
 * Created by Fajar Adi Prasetyo on 18/05/2022.
 */


class AdapterHolder<B : ViewBinding>(binding: B, onItemClickListener: OnItemClickListener?) :
    RecyclerView.ViewHolder(binding.root),
    View.OnClickListener {
    private var onItemClickListener: OnItemClickListener? = null

    override fun onClick(v: View?) {
        onItemClickListener?.onItemClick(v, adapterPosition)
    }

    init {
        binding.root.setOnClickListener(this)
        this.onItemClickListener = onItemClickListener
    }

}