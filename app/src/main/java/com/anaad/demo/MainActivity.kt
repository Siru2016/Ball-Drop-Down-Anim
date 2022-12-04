package com.anaad.demo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration.get
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.TranslateAnimation
import android.widget.EditText
import androidx.core.view.isVisible
import com.google.android.material.textfield.TextInputEditText


class MainActivity : AppCompatActivity() {

    private var view: View? = null
    private var editText: TextInputEditText? = null
    private var diamond: View? = null
    private var dX = 0f
    private var dY:Float = 0f

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
        view = findViewById(R.id.view)
        diamond = findViewById(R.id.circle)
//        diamond?.setOnTouchListener { view, motionEvent ->
//            onTouch(view, motionEvent)
//        }

        editText?.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                editText?.setLineSpacing(2f,2f)
            }

        })

        diamond?.setOnClickListener {
            view?.clearAnimation()
            view?.isVisible = true

            val array = arrayOf(78,156,234,312,390,468,546,624)
            val transAnim = TranslateAnimation(
                0f,0f,0f, (getTextLineHeight()).toFloat()
            )
            transAnim.startOffset = 500
            transAnim.duration = 3000
            transAnim.fillAfter = true
            transAnim.interpolator = BounceInterpolator()
            transAnim.setAnimationListener(object : Animation.AnimationListener{
                override fun onAnimationStart(p0: Animation?) {}

                override fun onAnimationEnd(p0: Animation?) {
                    view?.clearAnimation()
                    val left = view?.left
                    val top = view?.top
                    val right = view?.right
                    val bottom = view?.bottom
                    view?.layout(left!!, top!!, right!!,bottom!!)
                }
                override fun onAnimationRepeat(p0: Animation?) {} })

            view?.startAnimation(transAnim)
        }
    }

    private fun displayHeight(): Int {
        val displayMetrics = DisplayMetrics()
        val windowManager = windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

    private fun getTextLineHeight(): Int {
        editText?.setLineSpacing(2f,2f)
        val lineCount = editText?.lineCount
        val lineHeight = editText?.lineHeight
        val height = editText?.measuredHeight
        val totalLineHeight = lineCount!! * lineHeight!!
        var result = 0

        return totalLineHeight
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