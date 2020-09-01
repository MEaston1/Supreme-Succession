package com.northkorea.supremesuccession

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //declaring the animation
        val ttb = AnimationUtils.loadAnimation(this, R.anim.ttb)
        val stb = AnimationUtils.loadAnimation(this, R.anim.stb)

        val headertitle = findViewById(R.id.headertitle) as TextView
        val subtitle = findViewById(R.id.subtitle) as TextView
        val ic_nk_icon = findViewById(R.id.ic_cards) as ImageView

        // setting the animation
        headertitle.startAnimation(ttb)
        subtitle.startAnimation(ttb)

        ic_nk_icon.startAnimation(stb)

        //when the spin button is pressed change to the WheelActivity screen
        spin_button.setOnClickListener {
        val intent = Intent(this, WheelActivity::class.java)
            startActivity(intent)
        }
        contenders_button.setOnClickListener{
            val intent = Intent(this, ContendersActivity::class.java)
            startActivity(intent)
        }
    }




}
