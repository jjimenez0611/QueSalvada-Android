package com.soin.quesalvada.ui.activities

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.ViewModelProviders
import com.soin.quesalvada.R
import com.soin.quesalvada.common.BaseActivity
import com.soin.quesalvada.viewModel.DashboardViewModel
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_login.*


class DashboardActivity : BaseActivity() {

    override val layout: Int = R.layout.activity_dashboard

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun initializeViewModel() {
        dashboardViewModel = ViewModelProviders.of(this, viewModelFactory).get(DashboardViewModel::class.java)

    }

    override fun initializeUI() {
    }

    override fun observeLiveData() {

        observeButtons()
    }

    /**
     * Method to start or end the loading, this method call the showOrHideProgress and enableButtons
     */
    /*
    private fun startOrEndLoading(action: Boolean) = if (action) {
        hideOrShowProgress(VISIBLE)
    } else {
        hideOrShowProgress(GONE)
    }*/


    /**
     * Hide or Show the progress bar
     */
  /*  private fun hideOrShowProgress(action: Int) {
        progressCircular.visibility = action
    }*/


    private fun observeButtons(){
        btnLogout.setOnClickListener{
            dashboardViewModel.logoutUser()
            finish()
        }

    }

}