package com.example.homefinancialassistant.utils

import java.math.BigDecimal
import java.math.RoundingMode

class MathHelper {
    fun rounding(number: Double): Double {
        return BigDecimal(number).setScale(2, RoundingMode.HALF_UP).toDouble()

    }
    fun rounding(number: Float): Float {
        return BigDecimal(number.toDouble()).setScale(2, RoundingMode.HALF_UP).toFloat()
    }
    fun toPercent(price: Double, totalPrice: Double): Float {
        return rounding((price / totalPrice * 100).toFloat())
    }
}