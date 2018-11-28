package com.soin.quesalvada.utils

import android.app.Activity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.soin.quesalvada.R
import com.soin.quesalvada.common.LOGIN_GOOGLE_REQUEST_CODE
import java.lang.ref.WeakReference


object LoginOptionsUtils {


    /**
     * Function to start the login with Google
     */
    fun loginWithGoogle(reference: WeakReference<Activity>) {
        val activity = reference.get()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(activity?.getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        val googleSignInClient: GoogleSignInClient = GoogleSignIn.getClient(activity!!, gso)
        val signInIntent = googleSignInClient.signInIntent
        activity.startActivityForResult(signInIntent, LOGIN_GOOGLE_REQUEST_CODE)
    }
}
