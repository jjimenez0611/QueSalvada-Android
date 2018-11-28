package com.soin.quesalvada.services.firebase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.soin.quesalvada.common.Resource
import javax.inject.Inject


class FirebaseAuthService @Inject constructor() {

    private val firebaseAuthentication: FirebaseAuth = FirebaseAuth.getInstance()

    fun createUserWithEmailAndPasswordToFireBase(email: String, password: String): LiveData<Resource<FirebaseUser>> {

        val data = MutableLiveData<Resource<FirebaseUser>>()


        firebaseAuthentication.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task: Task<AuthResult> ->
            if (task.isSuccessful) {
                //Registration OK
                //val firebaseUser = this.firebaseAuthentication.currentUser!!
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
        // Log.d(TAG, "firebaseAuthWithGoogle:" + acct.id!!)
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

    fun checkIfUserIsLogIn(): LiveData<Resource<Boolean>> {

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

    fun logoutFirebase(): LiveData<Resource<Boolean>> {
        val data = MutableLiveData<Resource<Boolean>>()
        firebaseAuthentication.signOut()
        data.value = Resource.success(true)
        return data
    }

}