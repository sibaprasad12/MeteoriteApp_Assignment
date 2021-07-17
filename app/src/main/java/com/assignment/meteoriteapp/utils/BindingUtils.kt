package com.assignment.meteoriteapp.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.assignment.meteoriteapp.ui.adapter.MeteorAdapter

/**
 * Created by Sibaprasad Mohanty on 30/06/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

class BindingUtils {

    companion object {
        @JvmStatic
        @BindingAdapter("adapter")
        fun setRecyclerViewAdapter(recyclerViewCountry: RecyclerView, adapter: MeteorAdapter) {
            recyclerViewCountry.adapter = adapter
            recyclerViewCountry.layoutManager = LinearLayoutManager(recyclerViewCountry.context)
        }
    }
}