package com.soin.quesalvada.common

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    abstract val layout: Int


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
        initialize()

    }

    private fun initialize() {
        initializeViewModel()
        initializeUI()
        observeLiveData()

    }

    abstract fun initializeViewModel()
    abstract fun initializeUI()
    abstract fun observeLiveData()


}