package com.anaad.demo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.core.view.isVisible


class MainActivity : AppCompatActivity() {

    private var view: View? = null
    private var diamond: View? = null
    private var dX = 0f
    private var dY:Float = 0f

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        view = findViewById(R.id.view)
        diamond = findViewById(R.id.circle)
        diamond?.setOnTouchListener { view, motionEvent ->
            onTouch(view, motionEvent)
        }
    }

    private fun onTouch(p0: View?, event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_BUTTON_PRESS -> {
                dX = view?.x!! - event.rawX
                dY = view?.y!!.minus(event.rawY)
            }
            MotionEvent.ACTION_MOVE -> {
                view?.isVisible = true
                view?.animate()
                    ?.x(event.rawX + dX)
                    ?.y(event.rawY + dY)
                    ?.setDuration(5000)
                    ?.start()
            }
            else -> return false
        }
        return true
    }
}