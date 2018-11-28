package com.soin.quesalvada.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseUser
import com.soin.quesalvada.common.Resource
import com.soin.quesalvada.repositories.LoginRepository
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) : ViewModel() {


    val checkIfUserIsLogin = loginRepository.checkIfUserIsLogIn()

    private lateinit var user: MutableLiveData<Resource<FirebaseUser>>

    fun logInUserWithEmailAndPassword(email:String, password:String): LiveData<Resource<FirebaseUser>> {
        if (!::user.isInitialized) {
            user = loginRepository.logInUserWithEmailAndPassword(email,password)
        }
        return user
    }

    fun logoutUser(){loginRepository.logoutFirebase()}


    fun logInUserWithWithGoogle(googleSignInAccount: GoogleSignInAccount): LiveData<Resource<FirebaseUser>>{
        if (!::user.isInitialized) {
            user = loginRepository.logInUserWithGoogle(googleSignInAccount)
        }
        return user
    }


}