package com.example.homefinancialassistant.view.holders

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.homefinancialassistant.data.Category
import com.example.homefinancialassistant.databinding.CategoryColorsItemBinding

class HomeCategoryColorItem(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val binding = CategoryColorsItemBinding.bind(itemView)
    private val colorCategory = binding.colorCategory
    private val categoryName = binding.category
    private val price = binding.totalPrice
    private val percent = binding.percentPrice

    fun bind(category: Category) {
        colorCategory.setBackgroundColor(Color.parseColor(category.categoryColor))
        categoryName.text = category.categoryName
        price.text = category.categoryPrice.toString() + " руб."
        percent.text = category.categoryPercent.toString() + " %"
    }
}