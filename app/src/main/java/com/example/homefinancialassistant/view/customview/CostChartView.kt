package com.example.homefinancialassistant.view.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.example.homefinancialassistant.R
import com.example.homefinancialassistant.data.Category
import kotlin.math.min

class CostChartView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : View(context, attributeSet) {
    private val oval = RectF()
    private var radius: Float = 0f
    private var centerX: Float = 0f
    private var centerY: Float = 0f
    private var percent: Int = 10
    private var listOfPaints: List<Paint>
    private val listOfColors = listOf(
        "#cc0605",
        "#31f90c",
        "#0c0ef9",
        "#f9f824",
        "#7b0368",
        "#00FFFF",
        "#DCDCDC",
        "#C71585",
        "#FF4500",
        "#FF00FF",
        "#D2691E",
        "#800000",
        "#C0C0C0",
        "#ADFF2F",
        "#66CDAA",
        "#A9A9A9",
        "#fbb56e",
        "#6b0319",
        "#a5a9fd",
        "#f9a40f",
        "#f81cf9",
        "#8B4513",
        "#6A5ACD",
        "#708090",
        "#7B68EE"
    )

    private var map = mapOf<String, Float>()
    private var list = emptyList<String>()
    private var categoryAndColor = mutableListOf<Category>()

    init {
        val a = context.theme.obtainStyledAttributes(attributeSet, R.styleable.CostChartView, 0, 0)
        try {
            percent = a.getInt(R.styleable.CostChartView_percent, percent)
        } finally {
            a.recycle()
        }
        listOfPaints = initListOfPaints(listOfColors)
    }


    private fun initListOfPaints(listOfColors: List<String>): List<Paint> {
        val list = mutableListOf<Paint>()
        var i = 0
        listOfColors.forEach {
            val paint = Paint().apply {
                style = Paint.Style.FILL
                color = Color.parseColor(it)
            }
            list.add(i, paint)
            i++
        }
        return list
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        radius = if (width > height) {
            height.div(2f)
        } else {
            width.div(2f)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)

        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        val chosenWidth = chooseDimension(widthMode, widthSize)
        val chosenHeight = chooseDimension(heightMode, heightSize)

        val minSide = min(chosenWidth, chosenHeight)
        centerX = minSide.div(2f)
        centerY = minSide.div(2f)

        setMeasuredDimension(minSide, minSide)
    }

    private fun chooseDimension(mode: Int, size: Int) =
        when (mode) {
            MeasureSpec.AT_MOST, MeasureSpec.EXACTLY -> size
            else -> 300
        }

    private fun drawCost(canvas: Canvas, mapConsumption: Map<String, Float>) {
        val scale = radius * 0.8f
        var startAngle = 0f
        var i = 0
        val listCategoryFromMap = mapConsumption.keys
        canvas.save()
        canvas.translate(centerX, centerY)
        oval.set(0f - scale, 0f - scale, scale, scale)
        listCategoryFromMap.forEach { listKey ->
            canvas.drawArc(oval, startAngle, mapConsumption[listKey] ?: 0f, true, listOfPaints[i])
            startAngle += mapConsumption[listKey] ?: 0f
            i++
        }
        canvas.translate(centerX, centerY)
        canvas.restore()
    }

    override fun onDraw(canvas: Canvas) {
        drawCost(canvas, map)
    }

    fun setCost(mapFrom: Map<String, Float>) {
        list = mapFrom.keys.toList()
        map = mapFrom
        var i = 0
        list.forEach {
            categoryAndColor.add(i, Category(it, listOfColors[i], 0.0, angleToPercent(map[it] ?: 0f)))
            i++
        }
        invalidate()
    }

    fun getCategoryAndColors(): List<Category> {
        return categoryAndColor
    }

    private fun angleToPercent(angle: Float) : Double {
        return (angle / 360 * 100).toDouble()
    }


}