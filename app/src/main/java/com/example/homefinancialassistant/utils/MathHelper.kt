package com.example.homefinancialassistant.utils

import java.math.BigDecimal
import java.math.RoundingMode

class MathHelper {
    fun rounding(number: Double): Double {
        return BigDecimal(number).setScale(2, RoundingMode.HALF_UP).toDouble()
    }
}