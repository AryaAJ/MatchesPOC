package com.example.shadipoc.ui

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.airbnb.lottie.LottieAnimationView
import com.example.shadipoc.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LOW_PROFILE or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

        setStatusBarColor(R.color.white)

        setContentView(R.layout.activity_splash)

        val mAddedToCartAnimation: LottieAnimationView = findViewById(R.id.animation_view)
        mAddedToCartAnimation.playAnimation()

        mAddedToCartAnimation.addAnimatorListener(object :
            Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                //Log.e("Animation:", "start")
            }

            override fun onAnimationEnd(animation: Animator) {
                //      Log.e("Animation:", "end")
                //Your code for remove the fragment
                try {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                } catch (ex: Exception) {
                    ex.toString()
                }
            }

            override fun onAnimationCancel(animation: Animator) {
                //     Log.e("Animation:", "cancel")
            }

            override fun onAnimationRepeat(animation: Animator) {
                //   Log.e("Animation:", "repeat")
            }
        })
    }

    private fun setStatusBarColor(color: Int) {
        val window = window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, color)
    }

}
