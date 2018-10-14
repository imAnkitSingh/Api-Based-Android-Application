package com.internshala.demoapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.RelativeLayout
import com.internshala.demoapp.adapters.NotificationDisplayAdapter

class Notifications : AppCompatActivity() {
  //  var webView: WebView?=null

    var notificationRecyclerView:RecyclerView?=null
    var notificationDisplayAdapter:NotificationDisplayAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(0, 0)
        setContentView(R.layout.activity_notifications)
        var novalue=findViewById<RelativeLayout>(R.id.noValue)
    /*    webView=findViewById(R.id.webView_Notification)
        webView?.loadUrl("https://achyutasamanta.com/category/testimonials/")
        webView?.setWebViewClient(WebViewClient())   */

if (MyService.Statified.bodyMessage.size!= 0) {

    notificationDisplayAdapter = NotificationDisplayAdapter(MyService.Statified.bodyMessage, MyService.Statified.dateOfPublish,this)
    notificationDisplayAdapter?.notifyDataSetChanged()
    notificationRecyclerView = findViewById(R.id.notificationRecyclerView)
    Log.d("recycler view", "" + notificationRecyclerView)
    notificationRecyclerView?.adapter = notificationDisplayAdapter
    notificationRecyclerView?.layoutManager = LinearLayoutManager(this)
    notificationRecyclerView?.itemAnimator = DefaultItemAnimator()

}
        else
{
    novalue.visibility = View.VISIBLE

}


    }
}
