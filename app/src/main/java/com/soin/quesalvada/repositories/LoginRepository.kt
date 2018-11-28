package com.soin.quesalvada.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseUser
import com.soin.quesalvada.common.Resource
import com.soin.quesalvada.services.firebase.FirebaseAuthService
import javax.inject.Inject

class LoginRepository @Inject constructor(private val firebaseAuthService: FirebaseAuthService) {


    fun createUserWithEmailAndPassword(): LiveData<Resource<FirebaseUser>> {

        return firebaseAuthService.createUserWithEmailAndPasswordToFireBase("josean5665@gmail.com", "asp128")
    }

    fun checkIfUserIsLogIn(): LiveData<Resource<Boolean>> {

        return firebaseAuthService.checkIfUserIsLogIn()
    }

    fun logInUserWithEmailAndPassword(email: String, password: String): MutableLiveData<Resource<FirebaseUser>> {
        return firebaseAuthService.logInWithEmailAndPasswordFireBase(email, password)
    }

    fun logInUserWithGoogle(googleSignInAccount: GoogleSignInAccount): MutableLiveData<Resource<FirebaseUser>> {
        return firebaseAuthService.firebaseAuthWithGoogle(googleSignInAccount)
    }

    fun logoutFirebase(): LiveData<Resource<Boolean>> {
        return logoutFirebase()
    }
}