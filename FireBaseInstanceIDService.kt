package com.internshala.demoapp

import android.support.v7.widget.RecyclerView
import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

class FireBaseInstanceIDService: FirebaseInstanceIdService()
{
    object Statified
    {
        var TAG:String = "MyFirebaseIIDService"

    }

    override fun onTokenRefresh() {
        super.onTokenRefresh()
        //Getting registration token
        var refreshedToken: String? = FirebaseInstanceId.getInstance().token
//Displaying token on logcat
        Log.d(Statified.TAG, "Refreshed token: " + refreshedToken)
    }


}