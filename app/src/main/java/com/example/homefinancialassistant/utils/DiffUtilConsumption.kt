package com.example.homefinancialassistant.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.homefinancialassistant.data.Consumption

class DiffUtilConsumption: DiffUtil.ItemCallback<Consumption>() {
    override fun areItemsTheSame(oldItem: Consumption, newItem: Consumption): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Consumption, newItem: Consumption): Boolean {
        return oldItem.category == newItem.category &&
                oldItem.date == newItem.date &&
                oldItem.description == newItem.description
    }
}