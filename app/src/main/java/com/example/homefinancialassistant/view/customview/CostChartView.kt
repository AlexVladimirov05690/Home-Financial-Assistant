package com.example.homefinancialassistant.view.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.example.homefinancialassistant.R

class CostChartView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : View(context, attributeSet) {
    private val oval = RectF()
    private var radius: Float = 0f
    private var centerX: Float = 0f
    private var centerY: Float = 0f
    private var percent: Int = 10
    private lateinit var circlePaint1: Paint
    private lateinit var circlePaint2: Paint
    private lateinit var circlePaint3: Paint

    private var map = mapOf("Еда" to 52f, "Квартплата" to 100f, "Прочее" to 108f)

    init {
        val a = context.theme.obtainStyledAttributes(attributeSet, R.styleable.CostChartView, 0, 0)
        try {
            percent = a.getInt(R.styleable.CostChartView_percent, percent)
        } finally {
            a.recycle()
        }
        initPaint()
    }

    private fun initPaint() {
        circlePaint1 = Paint().apply {
            style = Paint.Style.FILL
            color = Color.GREEN
        }

        circlePaint2 = Paint().apply {
            style = Paint.Style.FILL
            color = Color.RED
        }

        circlePaint3 = Paint().apply {
            style = Paint.Style.FILL
            color = Color.BLUE
        }
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

        val minSide = Math.min(chosenWidth, chosenHeight)
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
        val list = listOf("Еда", "Квартплата", "Прочее")
        var startAngle = 0f
        var i = 0
        canvas.save()
        canvas.translate(centerX, centerY)
        oval.set(0f - scale, 0f - scale, scale, scale)
        val listPaint = listOf(circlePaint1, circlePaint2, circlePaint3)
        list.forEach {listKey ->
            canvas.drawArc(oval, startAngle, mapConsumption[listKey] ?: 0f, true, listPaint[i])
            startAngle += mapConsumption[listKey] ?: 0f
            i++
        }
        canvas.restore()
    }

    override fun onDraw(canvas: Canvas) {
        drawCost(canvas, map)
    }

    fun setCost(mapFrom: Map<String, Float>) {
        map = mapFrom
        invalidate()
    }


}