package com.soin.quesalvada.services.firebase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.soin.quesalvada.common.Resource
import javax.inject.Inject
import android.widget.Toast
import androidx.annotation.NonNull
import com.facebook.AccessToken
import com.google.firebase.auth.*
import com.twitter.sdk.android.core.TwitterSession


class FirebaseAuthService @Inject constructor() {

    private val firebaseAuthentication: FirebaseAuth = FirebaseAuth.getInstance()

    fun createUserWithEmailAndPasswordToFireBase(email: String, password: String): MutableLiveData<Resource<FirebaseUser>> {

        val data = MutableLiveData<Resource<FirebaseUser>>()
        firebaseAuthentication.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task: Task<AuthResult> ->
            if (task.isSuccessful) {
                //Registration OK
                data.value = Resource.success(firebaseAuthentication.currentUser!!)
            } else {
                //Registration error
                data.value = Resource.error(0, "Sucedio un error", null)
            }
        }
        return data
    }

    fun logInWithEmailAndPasswordFireBase(email: String, password: String): MutableLiveData<Resource<FirebaseUser>> {

        val data = MutableLiveData<Resource<FirebaseUser>>()
        data.value = Resource.loading()
        firebaseAuthentication.signInWithEmailAndPassword(email, password).addOnCompleteListener { task: Task<AuthResult> ->
            if (task.isSuccessful) {
                data.value = Resource.success(firebaseAuthentication.currentUser!!)
            } else {
                data.value = Resource.error(0, "sucedio un error", null)
            }
        }

        return data
    }

    fun firebaseAuthWithGoogle(acct: GoogleSignInAccount): MutableLiveData<Resource<FirebaseUser>> {
        val data = MutableLiveData<Resource<FirebaseUser>>()
        data.value = Resource.loading()

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        firebaseAuthentication.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        data.value = Resource.success(firebaseAuthentication.currentUser!!)
                    } else {
                        data.value = Resource.error(0, "sucedio un error", null)
                    }
                }
        return data
    }

    fun firebaseAuthWithFacebook(token: AccessToken): MutableLiveData<Resource<FirebaseUser>> {
        val data = MutableLiveData<Resource<FirebaseUser>>()
        data.value = Resource.loading()
        val credential = FacebookAuthProvider.getCredential(token.token)
        firebaseAuthentication.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        data.value = Resource.success(firebaseAuthentication.currentUser!!)
                    } else {
                        data.value = Resource.error(0, "sucedio un error", null)
                    }
                }

        return data
    }

    fun firebaseAuthWithTwitter(session: TwitterSession): MutableLiveData<Resource<FirebaseUser>> {
        val data = MutableLiveData<Resource<FirebaseUser>>()
        data.value = Resource.loading()
        val credential = TwitterAuthProvider.getCredential(
                session.authToken.token,
                session.authToken.secret)
        firebaseAuthentication.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        data.value = Resource.success(firebaseAuthentication.currentUser!!)
                    } else {
                        data.value = Resource.error(0, "sucedio un error", null)
                    }
                }

        return data
    }

    fun checkIfUserIsLogIn(): MutableLiveData<Resource<Boolean>> {

        val data = MutableLiveData<Resource<Boolean>>()
        firebaseAuthentication.addAuthStateListener {
            if (firebaseAuthentication.currentUser != null) {
                data.value = Resource.success(true)
            } else {
                data.value = Resource.error(0, "Sucedio un error", false)
            }
        }
        return data
    }

    fun logoutFirebase(): MutableLiveData<Resource<Boolean>> {
        val data = MutableLiveData<Resource<Boolean>>()
        firebaseAuthentication.signOut()
        data.value = Resource.success(true)
        return data
    }

}