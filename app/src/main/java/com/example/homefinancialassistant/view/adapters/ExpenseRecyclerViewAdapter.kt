package com.example.homefinancialassistant.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.homefinancialassistant.R
import com.example.homefinancialassistant.data.Consumption
import com.example.homefinancialassistant.utils.DiffUtilConsumption
import com.example.homefinancialassistant.view.holders.ExpenseViewHolder

class ExpenseRecyclerViewAdapter(private val onOpenConsumption: OnOpenConsumption) :
    ListAdapter<Consumption, ExpenseViewHolder>(DiffUtilConsumption()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        return ExpenseViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.expense_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        holder.bind(currentList[position])
        holder.itemView.setOnClickListener {
            onOpenConsumption.openConsumption(currentList[position])
        }
    }

    interface OnOpenConsumption {
        fun openConsumption(consumption: Consumption)
    }
}