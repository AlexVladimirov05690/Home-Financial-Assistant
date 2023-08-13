package com.example.homefinancialassistant.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.Calendar

@Parcelize
@Entity(tableName = "income_journal")
data class Income(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "date")
    val date: Calendar,
    @ColumnInfo(name = "category")
    var category: String,
    @ColumnInfo(name = "sum")
    val sum: Double,
    @ColumnInfo(name = "description")
    val description: String
) : Parcelable