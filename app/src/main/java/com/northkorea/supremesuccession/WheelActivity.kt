package com.northkorea.supremesuccession

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_wheel.*
import java.util.*

class WheelActivity : AppCompatActivity() {

    private var PRIVATE_MODE = 0
    private val PREF_INDEX = "index"
    private val PREF_FIRST_RUN = "first_run"
    private val PREF_CONTENDER = "contender"
    private lateinit var mRandom: Random
    private var mDegree: Float = 0f
    private var mDegreeOld: Float = 0f
    private var caseOfContender = ""
    private var tempDegree: Float = 0f
    private var index = 0
    private var initDegree: Float = 18f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wheel)
        val sharedPref: SharedPreferences = getSharedPreferences(PREF_INDEX, PRIVATE_MODE)
        index = sharedPref.getInt(PREF_INDEX, 0)
        //checking initial mode
        val isFirstRun = sharedPref.getBoolean(PREF_FIRST_RUN, true)
        if(isFirstRun){
            val editor = sharedPref.edit()
            editor.putString(PREF_CONTENDER, generateArray())
            editor.putBoolean(PREF_FIRST_RUN, false)
            editor.apply()
        }
        caseOfContender = sharedPref.getString(PREF_CONTENDER, "").toString()
        mRandom = Random()
        wheel_spin_button.setOnClickListener {
            if (index >= caseOfContender.length) {
                Toast.makeText(
                    this,
                    "Out of spins",
                    Toast.LENGTH_LONG
                ).show()
            } else{
                wheel_spin_button.isClickable = false
                tv_ContenderName.text = ""
                mDegreeOld = mDegree % 360
                // speed of rotation
                when (caseOfContender[index]) {
                    '0' -> tempDegree = 18f
                    '1' -> tempDegree = 342f
                    '2' -> tempDegree = 306f
                    '3' -> tempDegree = 270f
                    '4' -> tempDegree = 198f
                    '5' -> tempDegree = 126f
                    '6' -> tempDegree = 54f
                }
                mDegree = (360 - (tempDegree - initDegree) + 2160)
                val mRotate = RotateAnimation(
                    mDegreeOld, mDegree,
                    RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                    RotateAnimation.RELATIVE_TO_SELF, 0.5f
                )
                // time taken to rotate
                mRotate.duration = 3600
                mRotate.fillAfter = true
                mRotate.interpolator= DecelerateInterpolator()
                mRotate.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation) {
                        tv_ContenderName.text = ""
                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        wheel_spin_button.isClickable = true
                        val editor = sharedPref.edit()
                        tv_ContenderName.text = getContender(360 - mDegree % 360)
                        index = sharedPref.getInt(PREF_INDEX, 0) + 1
                        editor.putInt(PREF_INDEX, index)
                        editor.apply()
                    }
                    override fun onAnimationRepeat(animation: Animation?) {}
                })
                rol_body.startAnimation(mRotate)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val sharedPref: SharedPreferences = getSharedPreferences(PREF_INDEX, PRIVATE_MODE)
        caseOfContender = sharedPref.getString(PREF_CONTENDER, "").toString()
    }

    private fun generateArray(): String {
        val arr = mutableListOf<Int>()
        for (i in 1..1) {
            arr.add(0)
        }
        for (i in 1..8)
            arr.add(1)
        for (i in 1..4)
            arr.add(2)
        for (i in 1..8)
            arr.add(3)
        for (i in 1..8)
            arr.add(4)
        for (i in 1..4)
            arr.add(5)
        for (i in 1..2)
            arr.add(6)
        arr.shuffle()
        var result = ""
        for (i in 0 until arr.size)
            result += arr[i]
        return result
    }
    fun getContender(degrees: Float): String {
        Log.d("degrees", degrees.toString())
        var text = ""
        if (degrees > 0f && degrees <= 45f) {
            text = "Pak Pong Ju"
            tv_ContenderName.text = "Pak Pong Ju"
            Log.d("uytai", "1BT")
        }
        if (degrees > 45f && degrees <= 90f) {
            text = "Kim Tok Hun"
            tv_ContenderName.text = "Kim Tok Hun"
            Log.d("uytai", "1BT")
        }
        if (degrees > 90f && degrees <= 135f) {
            text = "China"
            tv_ContenderName.text = "China"
            Log.d("uytai", "1BT")
        }
        if (degrees > 135f && degrees <= 180f) {
            text = "Kim Kong Chol"
            tv_ContenderName.text = "Kim Kong Chol"
            Log.d("uytai", "1BT")
        }
        if (degrees > 180f && degrees <= 225f) {
            text = "Kim Ju Ae"
            tv_ContenderName.text = "Kim Ju Ae"
            Log.d("uytai", "1BT")
        }
        if (degrees > 225f && degrees <= 270f) {
            text = "USA & South Korea"
            tv_ContenderName.text = "USA & South Korea"
            Log.d("uytai", "1BT")
        }
        if (degrees > 270f && degrees <= 315f) {
            text = "Choe Ryong Hae"
            tv_ContenderName.text = "Choe Ryong Hae"
            Log.d("uytai", "1BT")
        }
        if (degrees > 315f && degrees <= 360f) {
            text = "Kim Yo Jong"
            tv_ContenderName.text = "Kim Yo Jong"
            Log.d("uytai", "1BT")
        }

        return text
    }
}