package com.kiit.founder

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.facebook.*
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import java.util.*
import com.facebook.FacebookException
import com.facebook.FacebookCallback
import com.facebook.login.LoginManager



class Login : Activity() {

    // facebok login variable decleration

var button:Button?=null
var signUpButton:Button?=null
var loginButton:Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



            setContentView(R.layout.activity_login)

            button=findViewById<Button>(R.id.guest_sign_in_button)
            signUpButton=findViewById(R.id.email_sign_up_button)
            loginButton= findViewById(R.id.email_sign_in_button)




Thread(Runnable {
    button?.setOnClickListener({


        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND)
        guestLogin()



    })
}).start()





    }

    fun guestLogin()   //it is called directly from the layout
    {




   Handler(Looper.getMainLooper()).postDelayed({

       android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND)

    var i = Intent(this@Login,falseActiivty::class.java)
    startActivity(i)
},0)
                               // App code



    }




}
