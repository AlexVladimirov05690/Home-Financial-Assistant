package com.example.homefinancialassistant.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.homefinancialassistant.data.Category


class DiffUtilHomeCategoryItem : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.categoryName == newItem.categoryName
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.categoryColor == newItem.categoryColor &&
                oldItem.categoryPrice == newItem.categoryPrice &&
                oldItem.categoryPercent == newItem.categoryPercent
    }

}