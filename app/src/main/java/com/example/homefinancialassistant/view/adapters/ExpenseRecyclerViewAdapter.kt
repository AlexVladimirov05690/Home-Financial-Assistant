package com.example.homefinancialassistant.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homefinancialassistant.R
import com.example.homefinancialassistant.data.Consumption
import com.example.homefinancialassistant.view.holders.ExpenseViewHolder

class ExpenseRecyclerViewAdapter(private val onOpenConsumption: OnOpenConsumption) :
    RecyclerView.Adapter<ExpenseViewHolder>() {
    var items = mutableListOf<Consumption>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        return ExpenseViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.expense_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            onOpenConsumption.openConsumption(items[position])
        }
    }

    fun addItems(list: List<Consumption>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    interface OnOpenConsumption {
        fun openConsumption(consumption: Consumption)
    }
}