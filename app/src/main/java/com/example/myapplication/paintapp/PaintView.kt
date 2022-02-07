package com.example.myapplication.paintapp

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup


class PaintView : View {
    private var params: ViewGroup.LayoutParams? = null


    private var startX = 0f
    private var startY = 0f
    private var endX = 0f
    private var endY = 0f
    private var startRecX = 0f
    private var startRecY = 0f
    private var endRecX = 0f
    private var endRecY = 0f
    var points: List<Point> = ArrayList<Point>()




    companion object {
        var pathList = ArrayList<Path>()
        var colorList = ArrayList<Int>()
        var currentBaruch = Color.BLACK
        var path = Path()
        var canvass = Canvas()
        var paintBaruch = Paint()
    }

    constructor(context: Context) : this(context, null) {

        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        paintBaruch.isAntiAlias = true
        paintBaruch.color = currentBaruch
        paintBaruch.style = Paint.Style.STROKE
        paintBaruch.strokeJoin = Paint.Join.ROUND
        paintBaruch.strokeWidth = 8f
        params = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event!!.x
        val y = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                if (MainActivity.statue==1){
                    path.moveTo(x, y)
                }
                if (MainActivity.statue==2){
                    startX = event.x
                    startY = event.y
                    endX = event.x
                    endY = event.y


                }
                if (MainActivity.statue==3){
                    startRecX = event.x
                    startRecY=event.y
                    endRecX = event.x
                    endRecY=event.y
                }
                  invalidate()
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                if (MainActivity.statue == 1) {
                    path.lineTo(x, y)
                    pathList.add(path)
                    colorList.add(currentBaruch)
                }
                if (MainActivity.statue==2){

                    endX = event.x
                    endY = event.y
                }
                if (MainActivity.statue==3){
                    endRecX=event.x
                    endRecY=event.y
                }
                invalidate()
                return true
            }
            else -> return false
        }
        postInvalidate()
    }
    override fun onDraw(canvas: Canvas?) {
            if (canvas != null) {
                canvas.drawLine(startX,startY,endX,endY, paintBaruch)
                invalidate()
            }
            if (canvas != null) {
                canvas.drawRect(startRecX, startRecY, endRecX, endRecY, paintBaruch)
                invalidate()
        }

            for (i in pathList.indices) {
                paintBaruch.color = colorList[i]
                if (canvas != null) {
                    canvas.drawPath(pathList[i], paintBaruch)
                    invalidate()
            }
        }
    }
}


