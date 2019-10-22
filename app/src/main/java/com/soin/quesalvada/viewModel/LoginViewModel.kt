package com.soin.quesalvada.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseUser
import com.soin.quesalvada.common.Resource
import com.soin.quesalvada.repositories.LoginRepository
import com.twitter.sdk.android.core.TwitterSession
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) : ViewModel() {


    val checkIfUserIsLogin = loginRepository.checkIfUserIsLogIn()

    private lateinit var user: MutableLiveData<Resource<FirebaseUser>>

    fun logInUserWithEmailAndPassword(email: String, password: String): MutableLiveData<Resource<FirebaseUser>> {
        if (!::user.isInitialized) {
            user = loginRepository.logInUserWithEmailAndPassword(email, password)
        }
        return user
    }

    fun logInUserWithWithGoogle(googleSignInAccount: GoogleSignInAccount): MutableLiveData<Resource<FirebaseUser>> {
        if (!::user.isInitialized) {
            user = loginRepository.logInUserWithGoogle(googleSignInAccount)
        }
        return user
    }


    fun logInUserWithWithFacebook(accessToken: AccessToken): MutableLiveData<Resource<FirebaseUser>> {
        if (!::user.isInitialized) {
            user = loginRepository.logInUserWithFacebook(accessToken)
        }
        return user
    }

    fun logInUserWithWithTwitter(session: TwitterSession): MutableLiveData<Resource<FirebaseUser>> {
        if (!::user.isInitialized) {
            user = loginRepository.logInUserWithTwitter(session)
        }
        return user
    }


}