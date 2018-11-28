package com.soin.quesalvada.ui.activities

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.animation.*
import androidx.appcompat.app.AppCompatActivity
import com.soin.quesalvada.R
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity() {

    private lateinit var fadeIn: AlphaAnimation

    private lateinit var rotationWithZoomAnimation: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        init()
    }

    private fun init() {
        initObs()
    }

    private fun initObs() {
        setupAnimation()
        initUI()
    }

    private fun initUI() {

    }

    override fun onResume() {
        super.onResume()
        executeAnimation()
    }

    private fun setupAnimation() {
        rotationWithZoomAnimation = AnimationUtils.loadAnimation(this,R.anim.rotation_with_zoom_anim)
    }

    private fun executeAnimation() {

        rotationWithZoomAnimation.setAnimationListener( object : Animation.AnimationListener{
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                // create the transition animation - the images in the layouts
                // of both activities are defined with android:transitionName="robot"
                val options = ActivityOptions.makeSceneTransitionAnimation(this@SplashActivity, imgAppLogo, "app_logo_transition")
                // start the new activity
                startActivity(intent, options.toBundle())
            }
            override fun onAnimationStart(animation: Animation?) {
            }
        })
        imgAppLogo.startAnimation(rotationWithZoomAnimation)
    }
}