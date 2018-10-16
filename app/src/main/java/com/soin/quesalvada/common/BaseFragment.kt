package com.soin.quesalvada.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.lifecycle.Lifecycle.Event

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import dagger.android.DaggerFragment

abstract class BaseFragment : DaggerFragment() {


    internal class ViewLifecycleOwner : LifecycleOwner {
        private val lifecycleRegistry = LifecycleRegistry(this)

        override fun getLifecycle(): LifecycleRegistry {
            return lifecycleRegistry
        }
    }

    @Nullable
    private var lifeCycleOwner: ViewLifecycleOwner? = null

    /**
     * @return the Lifecycle owner of the current view hierarchy,
     * or null if there is no current view hierarchy.
     */
    fun getViewLifecycleOwner(): LifecycleOwner? {
        return lifeCycleOwner
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifeCycleOwner = ViewLifecycleOwner()
        lifeCycleOwner!!.lifecycle.handleLifecycleEvent(Event.ON_CREATE)
        initializeUI()
    }

    override fun onStart() {
        super.onStart()
        lifeCycleOwner?.lifecycle?.handleLifecycleEvent(Event.ON_START)
    }

    override fun onResume() {
        super.onResume()
        lifeCycleOwner?.lifecycle?.handleLifecycleEvent(Event.ON_RESUME)
    }

    override fun onPause() {
        lifeCycleOwner?.lifecycle?.handleLifecycleEvent(Event.ON_PAUSE)
        super.onPause()
    }

    override fun onStop() {
        lifeCycleOwner?.lifecycle?.handleLifecycleEvent(Event.ON_STOP)
        super.onStop()
    }

    override fun onDestroyView() {
        if (lifeCycleOwner != null) {
            lifeCycleOwner!!.lifecycle.handleLifecycleEvent(Event.ON_DESTROY)
            lifeCycleOwner = null
        }
        super.onDestroyView()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initializeViewModels()
        observeLiveData(savedInstanceState == null)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(layout, container, false)
    }

    abstract val layout: Int

    abstract fun initializeViewModels()
    abstract fun initializeUI()
    abstract fun observeLiveData(isNewActivity: Boolean)
}