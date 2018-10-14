package com.kiit.founder

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.facebook.*
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.kiit.founder.R.id.toolbar

import kotlinx.android.synthetic.main.activity_facebook_login.*
import java.util.*

class FacebookLogin : AppCompatActivity() {

    //login facebook
    var callbackManager: CallbackManager?=null
    private val EMAIL = "email"
    var loginButton: LoginButton?=null
    var accessToken:AccessToken?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facebook_login)
       // setSupportActionBar(toolbar)

        FacebookSdk.sdkInitialize(getApplicationContext())
        callbackManager = CallbackManager.Factory.create()

        //login button
          loginButton =  findViewById(R.id.login_button);

        Thread(Runnable {
            android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND)
            loginButton?.setReadPermissions(Arrays.asList(EMAIL));
            accessToken = AccessToken.getCurrentAccessToken()
            var  isLoggedIn:Boolean = accessToken != null && accessToken?.isExpired()!=true
            if (!isLoggedIn) {

                loginButton?.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                    override fun onError(error: FacebookException?) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onCancel() {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onSuccess(result: LoginResult?) {
                        //   Log.d("login",""+accessToken)
                        Log.d("login", "Hellow")
                        var accessToken: AccessToken = AccessToken.getCurrentAccessToken()
                        var isLoggedIn: Boolean = accessToken != null && !accessToken.isExpired()
                        if (isLoggedIn) {
                        //    Handler().postDelayed({
                             //   android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND)
                                var i = Intent(this@FacebookLogin, Login::class.java)
                                startActivity(i)
                                overridePendingTransition(0, 0)
                            }
                        //, 1000)


                      //  }

                        Log.d("login", "" + accessToken)
                        //To change body of created functions use File | Settings | File Templates.
                    }

                })
            }
            else {
                Handler(Looper.getMainLooper()).postDelayed({
                //    android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND)
                    var i = Intent(this@FacebookLogin, Login::class.java)
                    startActivity(i)
                    overridePendingTransition(0, 0)
                }, 0)
                this.finish()
            }

        }).start()

            }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        callbackManager?.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }


}
