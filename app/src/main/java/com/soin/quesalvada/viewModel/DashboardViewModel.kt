package com.soin.quesalvada.viewModel

import androidx.lifecycle.ViewModel
import com.soin.quesalvada.repositories.LoginRepository
import javax.inject.Inject

class DashboardViewModel @Inject constructor(private val loginRepository: LoginRepository) : ViewModel() {


    fun logoutUser() {
        loginRepository.logoutFirebase()
    }


}