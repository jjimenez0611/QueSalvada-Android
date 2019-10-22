package com.soin.quesalvada.ui.activities

import android.content.Intent
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseUser
import com.soin.quesalvada.R
import com.soin.quesalvada.common.BaseActivity
import com.soin.quesalvada.common.LOGIN_GOOGLE_REQUEST_CODE
import com.soin.quesalvada.common.Resource
import com.soin.quesalvada.common.Status
import com.soin.quesalvada.utils.ValidateFieldsUtils.validateEditTextFieldField
import com.soin.quesalvada.viewModel.LoginViewModel
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.TwitterSession
import com.twitter.sdk.android.core.identity.TwitterAuthClient
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*


class LoginActivity : BaseActivity() {

    override val layout: Int = R.layout.activity_login

    private lateinit var loginViewModel: LoginViewModel

    private lateinit var fadeInAnimation: Animation
    private lateinit var bounceAnimation: Animation

    private lateinit var _callbackManagerFacebook: CallbackManager

    private lateinit var _twitterAuthClient: TwitterAuthClient

    override fun initializeViewModel() {
        loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
        _twitterAuthClient = TwitterAuthClient()
        _callbackManagerFacebook = CallbackManager.Factory.create()

    }

    override fun initializeUI() {
        executeAnimations()
    }

    override fun observeLiveData() {
        checkUserIsLogin()
    }

    private fun checkUserIsLogin() {

        loginViewModel.checkIfUserIsLogin.observe(this, Observer<Resource<Boolean>> { resource ->
            if (resource != null) {
                when (resource.status) {
                    Status.SUCCESS -> {
                        intent = Intent(this, DashboardActivity::class.java)
                        startActivity(intent)
                    }
                    Status.ERROR -> {
                        setOnclickListeners()
                    }
                    Status.LOADING -> {

                    }
                }
            }
        })
    }

    private fun setOnclickListeners() {
        btnLogin.setOnClickListener {
            observerLoginWithEmailAndPassword()
        }

        btnLoginOpt1?.setOnClickListener {
            loginWithGoogle()
        }

        btnLoginOpt2?.setOnClickListener {
            loginWithFacebook()
        }

        btnLoginOpt3?.setOnClickListener {
            loginWithTwitter()

        }

    }

    /**
     * Method to start or end the loading, this method call the showOrHideProgress and enableButtons
     */
    private fun startOrEndLoading(action: Boolean) = if (action) {
        enableButtons(!action)
        hideOrShowProgress(VISIBLE)
    } else {
        enableButtons(!action)
        hideOrShowProgress(GONE)
    }

    /**
     * Enable or disable the buttons
     */
    private fun enableButtons(action: Boolean) {
        btnLogin.isEnabled = action
        btnLoginOpt1?.isEnabled = action
        btnLoginOpt2?.isEnabled = action
        btnLoginOpt3?.isEnabled = action
    }

    /**
     * Hide or Show the progress bar
     */
    private fun hideOrShowProgress(action: Int) {
        progressCircular.visibility = action
    }

    /**
     * Validate fields of login
     */
    private fun validateFields(): Boolean {
        var result = true

        if (!validateEditTextFieldField(etUser, getString(R.string.lb_required_field))) result = false
        if (!validateEditTextFieldField(etPassword, getString(R.string.lb_required_field))) result = false

        return result
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        _callbackManagerFacebook.onActivityResult(requestCode, resultCode, data)
        _twitterAuthClient.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOGIN_GOOGLE_REQUEST_CODE) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                observerLoginWithGoogle(account!!)
            } catch (e: ApiException) {
                Log.e("Login Google:",e.localizedMessage)
            }
        }
    }

    //Login types section
    /**
     * function to observer the login email and password
     */
    private fun observerLoginWithEmailAndPassword() {

        if (validateFields()) {
            loginViewModel.logInUserWithEmailAndPassword(etUser.text.toString(), etPassword.text.toString()).observe(this, Observer<Resource<FirebaseUser>> { resource ->
                if (resource != null) {
                    when (resource.status) {
                        Status.SUCCESS -> {
                            Toast.makeText(this, "User: " + resource.data.toString(), Toast.LENGTH_LONG).show()
                            startOrEndLoading(false)
                        }
                        Status.ERROR -> {
                            Toast.makeText(this, "Error: " + resource.message, Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {
                            startOrEndLoading(true)
                        }
                    }
                }
            })
        }
    }


    /**
     * function to observer the Google Login
     */
    private fun observerLoginWithGoogle(googleSignInAccount: GoogleSignInAccount) {

        loginViewModel.logInUserWithWithGoogle(googleSignInAccount).observe(this, Observer<Resource<FirebaseUser>> { resource ->
            if (resource != null) {
                when (resource.status) {
                    Status.SUCCESS -> {
                        Toast.makeText(this, "User: " + resource.data.toString(), Toast.LENGTH_LONG).show()
                        startOrEndLoading(false)
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, "Error: " + resource.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        startOrEndLoading(true)
                    }
                }
            }
        })
    }

    /**
     * function to observer the Facebook Login
     */
    private fun observerLoginWithFacebook(accessToken: AccessToken) {

        loginViewModel.logInUserWithWithFacebook(accessToken).observe(this, Observer<Resource<FirebaseUser>> { resource ->
            if (resource != null) {
                when (resource.status) {
                    Status.SUCCESS -> {
                        Toast.makeText(this, "User: " + resource.data.toString(), Toast.LENGTH_LONG).show()
                        startOrEndLoading(false)
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, "Error: " + resource.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        startOrEndLoading(true)
                    }
                }
            }
        })
    }


    /**
     * function to observer the Twitter Login
     */
    private fun observerLoginWithTwitter(session: TwitterSession) {

        loginViewModel.logInUserWithWithTwitter(session).observe(this, Observer<Resource<FirebaseUser>> { resource ->
            if (resource != null) {
                when (resource.status) {
                    Status.SUCCESS -> {
                        Toast.makeText(this, "User: " + resource.data.toString(), Toast.LENGTH_LONG).show()
                        startOrEndLoading(false)
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, "Error: " + resource.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        startOrEndLoading(true)
                    }
                }
            }
        })
    }

    //Animation section
    /**
     * Execute all animation on the elements when create the activity
     */
    private fun executeAnimations() {
        fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        bounceAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce_anim)
        etUser.startAnimation(fadeInAnimation)
        etPassword.startAnimation(fadeInAnimation)
        btnLogin.startAnimation(fadeInAnimation)
        btnLoginOpt1?.startAnimation(bounceAnimation)
        btnLoginOpt2?.startAnimation(bounceAnimation)
        btnLoginOpt3?.startAnimation(bounceAnimation)

    }

    //Login options section
    private fun loginWithTwitter() {

        _twitterAuthClient.authorize(this, object : Callback<TwitterSession>() {
            override fun success(result: com.twitter.sdk.android.core.Result<TwitterSession>?) {
                observerLoginWithTwitter(result!!.data)

            }

            override fun failure(exception: TwitterException) {
                Log.e("", "twitter:onError", exception.cause)
            }
        })
    }

    private fun loginWithGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(this.getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        val googleSignInClient: GoogleSignInClient = GoogleSignIn.getClient(this, gso)
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, LOGIN_GOOGLE_REQUEST_CODE)
    }

    private fun loginWithFacebook(){
        setCallbackManagerFacebook()
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "public_profile", "user_birthday"))
    }

    private fun setCallbackManagerFacebook() {
        LoginManager.getInstance().registerCallback(_callbackManagerFacebook, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                Log.d("", "facebook:onSuccess:$loginResult")
                observerLoginWithFacebook(loginResult.accessToken)
            }

            override fun onCancel() {
                Log.d("", "facebook:onCancel")
                // ...
            }

            override fun onError(error: FacebookException) {
                Log.d("", "facebook:onError", error)
                // ...
            }
        })
    }
}