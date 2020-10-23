package com.example.cerericobalt.draw

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener


class DrawView(context: Context, attrs: AttributeSet) : View(context, attrs), OnTouchListener {
    var points: MutableList<Point> = ArrayList()
    var paint: Paint = Paint()
    var onMoveEnd: Boolean = false;

    override fun onDraw(canvas: Canvas) {
        for (i in 1 until points.size) {
            if (points[i - 1].x == 16000000000f || points[i].x == 16000000000f) {
                continue;
            }
            canvas.drawLine(points[i - 1].x, points[i - 1].y, points[i].x, points[i].y, paint)
        }
    }

    override fun onTouch(view: View?, event: MotionEvent): Boolean {
        val point = Point()
        point.x = event.x
        point.y = event.y
        points.add(point)
        if (event.action == MotionEvent.ACTION_UP) {
            // why 16000000000f? Because it's 23:08, that's why! ;)
            point.x = 16000000000f
            point.y = 16000000000f
            points.add(point)
            onMoveEnd = true
        }
        invalidate()
        return true
    }

    init {
        isFocusable = true
        isFocusableInTouchMode = true
        this.setOnTouchListener(this)
        paint.color = Color.BLACK
        paint.strokeWidth = 10f
        this.isDrawingCacheEnabled = true;
    }

    fun get(): Bitmap? {
        return this.drawingCache
    }


    fun clear() {
        points.clear()
        invalidate();
    }
}

class Point {
    var x = 0f
    var y = 0f
}