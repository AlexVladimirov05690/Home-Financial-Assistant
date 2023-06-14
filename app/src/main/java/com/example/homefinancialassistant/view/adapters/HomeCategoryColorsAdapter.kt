package com.example.homefinancialassistant.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.homefinancialassistant.R
import com.example.homefinancialassistant.data.Category
import com.example.homefinancialassistant.utils.DiffUtilHomeCategoryItem
import com.example.homefinancialassistant.view.holders.HomeCategoryColorItem

class HomeCategoryColorsAdapter :
    ListAdapter<Category, HomeCategoryColorItem>(DiffUtilHomeCategoryItem()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCategoryColorItem {
        return HomeCategoryColorItem(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.category_colors_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HomeCategoryColorItem, position: Int) {
        holder.bind(currentList[position])
    }
}