package com.example.homefinancialassistant.view.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.homefinancialassistant.data.Consumption
import com.example.homefinancialassistant.data.db.converters.Converters
import com.example.homefinancialassistant.databinding.ExpenseItemBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ExpenseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val expenseItemBinding = ExpenseItemBinding.bind(itemView)
    private val expenseCategory = expenseItemBinding.itemCategory
    private val expenseData = expenseItemBinding.expenseData
    private val expensePrice = expenseItemBinding.expensePrice
    private val expenseDescription = expenseItemBinding.description

    fun bind(consumption: Consumption) {
        expenseCategory.text = consumption.category
        expenseData.text = calendarToString(consumption.date) // Добавить представление даты в строку
        expensePrice.text = consumption.price.toString()
        expenseDescription.text = consumption.description

    }

    private fun calendarToString(calendar: Calendar): String {
        val sdf = SimpleDateFormat(Converters.FORMAT_DATE, Locale.ROOT)
        val date = calendar.time
        return sdf.format(date)

    }
}