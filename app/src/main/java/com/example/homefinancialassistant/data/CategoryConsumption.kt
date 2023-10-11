package com.example.homefinancialassistant.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(
    tableName = "spending_by_category"
)
data class CategoryConsumption(
        @PrimaryKey
        val category: String,
        @ColumnInfo(name = "color", defaultValue = "")
        val color: String,
        @ColumnInfo(name = "price")
        val price: Double,
        @ColumnInfo(name = "percent")
        val percent: Double
): Parcelable