package com.internshala.demoapp

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.net.Uri
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.internshala.demoapp.adapters.NotificationDisplayAdapter


class MyService : FirebaseMessagingService()
 {

     var adapterCount:Int=0

     object Statified
    {
        var TAG:String="MyFirebaseMsgService"
        var count:Int = 0
        var bodyMessage= arrayListOf<String>()
        var dateOfPublish= arrayListOf<String>()
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)

        //Displaying data in log
        //It is optional
        Log.d(Statified.TAG, "Notification Message TITLE: " + remoteMessage?.notification!!.title)
        Log.d(Statified.TAG, "Notification Message BODY: " + remoteMessage.notification!!.body)
        Log.d(Statified.TAG, "Notification Message DATA: " + remoteMessage.data.get("Date").toString())

        adapterCount++
        for (i in 0 until adapterCount)
        {
            Statified.bodyMessage.add(remoteMessage.notification!!.body!!)
            Statified.dateOfPublish.add(remoteMessage.data.get("Date")!!)

            adapterCount=0
        }
        Log.d("myservice",""+ Statified.bodyMessage)
//Calling method to generate notification
if (remoteMessage.notification!!.title==null || remoteMessage.data == null ) {

    remoteMessage.notification!!.title == ""
    remoteMessage.data == null
 // var notificationDisplayAdapter=NotificationDisplayAdapter(bodyMessage)
 //  notificationDisplayAdapter.notifyDataSetChanged()
    sendNotification(remoteMessage.getNotification()!!.title,
            remoteMessage.getNotification()!!.body!!, remoteMessage.getData())
}
        else
{



    sendNotification(remoteMessage.getNotification()!!.title,
            remoteMessage.getNotification()!!.body!!, remoteMessage.getData())


}

    }

//This method is only generating push notification

    fun sendNotification( messageTitle:String?, messageBody:String?,  row:Map<String?, String?>) {
        var contentIntent: PendingIntent? = null
        var defaultSoundUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        var notificationBuilder:  NotificationCompat.Builder  =  NotificationCompat.Builder(this)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.foundersirrr))
                .setSmallIcon(R.drawable.foundersirrr)
                .setContentTitle(messageTitle)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(contentIntent)
        var notificationManager: NotificationManager =
        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(Statified.count, notificationBuilder.build())
        Statified.count++
    }

 }






