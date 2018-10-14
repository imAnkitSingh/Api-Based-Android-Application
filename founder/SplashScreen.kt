package com.kiit.founder

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.facebook.*
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import com.kiit.founder.Adapters.BlogDisplayingAdapter
import com.kiit.founder.ApiClient.APIInterface
import com.kiit.founder.ApiClient.ApiClient
import com.kiit.founder.Pojo.*
import com.kiit.founder.SplashScreen.Statified.allActionLink
import com.kiit.founder.SplashScreen.Statified.allActionName
import com.kiit.founder.SplashScreen.Statified.allPageId
import com.kiit.founder.SplashScreen.Statified.allPageMessage
import com.kiit.founder.SplashScreen.Statified.allPagePic
import com.kiit.founder.SplashScreen.Statified.allPageShares
import com.kiit.founder.SplashScreen.Statified.allPageStory
import com.kiit.founder.SplashScreen.Statified.allpageDoc
import com.kiit.founder.SplashScreen.Statified.mActionLink
import com.kiit.founder.SplashScreen.Statified.mActionName
import com.kiit.founder.SplashScreen.Statified.mCommentCreateTime
import com.kiit.founder.SplashScreen.Statified.mCommentId
import com.kiit.founder.SplashScreen.Statified.mCommentMessage
import com.kiit.founder.SplashScreen.Statified.mCount
import com.kiit.founder.SplashScreen.Statified.mallCommentCreateTime
import com.kiit.founder.SplashScreen.Statified.mallCommentId
import com.kiit.founder.SplashScreen.Statified.mallCommentMessage
import com.kiit.founder.SplashScreen.Statified.pageDOC
import com.kiit.founder.SplashScreen.Statified.pageId
import com.kiit.founder.SplashScreen.Statified.pageMessage
import com.kiit.founder.SplashScreen.Statified.pagePic
import com.kiit.founder.SplashScreen.Statified.pageStory
import com.mikhaellopez.circularimageview.CircularImageView
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class SplashScreen : Activity() {


    var imgUrl: String? = null
    var jsonObjectBlog: Example? = null
    var callImage: Call<ArrayList<Example>>? = null
// for blog

    var midBlog: String? = null
    var mRenderedBlog: String? = null
    var mtitleBlog: Title? = null
    var renderedExcerptBlog: String? = null

    //async task
    //blogdata
    var jsonObjectBlogg: BlogList? = null
    var mExcerpt: Excerpt? = null
    var mRenderedContentBlog: String? = null
    var mContent: Content? = null


    //for image
    var sImage: CircularImageView? = null
    var sLogo: ImageView? = null

    var permissionString =
            arrayOf(Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.INTERNET,
                    Manifest.permission.ACCESS_WIFI_STATE)

    //facebook page

    object Statified {

        var pageMessage: String? = null
        var pageStory: String? = null
        var pageId: String? = null
        var pagePic: String? = null
        var pageDOC: String? = null
        var mCount: String? = null
        var mCommentMessage: String? = null
        var mCommentCreateTime: String? = null
        var mCommentId: String? = null
        //   var mactions: String? = null
        var mActionName: String? = null
        var allActionName = arrayListOf<String>()
        var mActionLink: String? = null
        var allActionLink = arrayListOf<String>()
        var mallCommentMessage = arrayListOf<String>()
        var mallCommentCreateTime = arrayListOf<String>()
        var mallCommentId = arrayListOf<String>()
        var allPageMessage = arrayListOf<String>()
        var allPageStory = arrayListOf<String>()
        var allPageId = arrayListOf<String>()
        var allPagePic = arrayListOf<String>()
        var allpageDoc = arrayListOf<String>()
        var allPageShares = arrayListOf<String>()

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        sImage = findViewById(R.id.circularImg)
        sLogo = findViewById(R.id.sLogo)



        if (!haspermission(this@SplashScreen, *permissionString)) {
            // here i called the requestPermissions function for requesting the required permisiion
            ActivityCompat.requestPermissions(this@SplashScreen, permissionString, 131)
        } else {

            if (checkInternetConnectivitySplash()) {
/*
                sImage?.setImageBitmap(
                        MainActivity.Staticated.decodeSampledBitmapFromResource(resources, R.drawable.foundersirrr, 100, 100)
                )
                sLogo?.setImageBitmap(
                        MainActivity.Staticated.decodeSampledBitmapFromResource(resources, R.drawable.logo, 150, 150)
                )
                */
                apiBlog()
                apiImage()

                FacebookPageContent()
                Handler().postDelayed({
                    var startAct = Intent(this@SplashScreen, FacebookLogin::class.java)
                    startActivity(startAct)
                    this.finish()
                }, 3000)
            } else {
                alertMessage()
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            131 -> {
                if (grantResults.isNotEmpty() &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                        grantResults[1] == PackageManager.PERMISSION_GRANTED &&
                        grantResults[2] == PackageManager.PERMISSION_GRANTED
                ) {

                    if (checkInternetConnectivitySplash()) {

                        FacebookPageContent()
                        Handler().postDelayed({
                            var startAct = Intent(this@SplashScreen, FacebookLogin::class.java)
                            startActivity(startAct)
                            this.finish()
                        }, 3000)
                    } else {
                        alertMessage()
                    }

                } else {
                    Toast.makeText(this@SplashScreen, "please grant all permission", Toast.LENGTH_SHORT).show()
                    this.finish()
                }
                return
            }

            else -> {
                Toast.makeText(this@SplashScreen, "something went wrong", Toast.LENGTH_SHORT).show()
                this.finish()
                return
            }
        }
    }

    fun haspermission(context: Context, vararg permissions: String): Boolean {
        var haspermission = true
        for (permission in permissions) {
            var res = context.checkCallingOrSelfPermission(permission)
            if (res != PackageManager.PERMISSION_GRANTED) {

                haspermission = false
            }
        }
        return haspermission
    }

    fun checkInternetConnectivitySplash(): Boolean {
        var connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    fun alertMessage() {

        var alertDialog = AlertDialog.Builder(this).create()
        (alertDialog as AlertDialog).setTitle("Message")
        alertDialog.setMessage("Please check your  Internet  Connection")

        alertDialog.setButton("OK") { dialog, which ->
            // Write your code here to execute after dialog closed
            this.finish()
        }
        alertDialog.show()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }


    override fun onDestroy() {
        super.onDestroy()

        Runtime.getRuntime().gc()

    }

    fun FacebookPageContent() {

/* make the API call */


        //  AccessToken.getCurrentAccessToken()
        LoginManager.getInstance().logInWithPublishPermissions(
                this,
                Arrays.asList("manage_pages", "publish_pages"
                ))

        var mRequest: GraphRequest = GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/769268423274829/feed?fields=full_picture,id,message,story," +
                        "created_time,comments{comment_count,created_time,message},shares,actions",
                null,
                HttpMethod.GET,
                GraphRequest.Callback { response ->
                    Log.e(ContentValues.TAG, response.toString())
                    Log.d("accessToken", "" + AccessToken.getCurrentAccessToken())

                    if (response!!.jsonObject != null) {

                        var jsonResponse1: JSONObject = response.jsonObject
                        var jsonArray1: JSONArray = jsonResponse1.getJSONArray("data")


                        for (i in 0 until jsonArray1.length()) {
                            var jsonObject1: JSONObject = jsonArray1.getJSONObject(i)
                            if (jsonObject1.has("message")) {
                                pageMessage = jsonObject1.optString("message")
                                allPageMessage.add(pageMessage as String)

                            } else {
                                pageMessage = ""
                                allPageMessage.add(pageMessage as String)
                            }

                            if (jsonObject1.has("id")) {
                                pageId = jsonObject1.optString("id")
                                allPageId.add(pageId as String)
                            } else {
                                pageId = ""
                                allPageId.add(pageId as String)
                            }
                            if (jsonObject1.has("story")) {
                                pageStory = jsonObject1.optString("story")
                                allPageStory.add(pageStory as String)
                            } else {
                                pageStory = ""
                                allPageStory.add(pageStory as String)
                            }
                            if (jsonObject1.has("full_picture")) {
                                pagePic = jsonObject1.optString("full_picture")
                                allPagePic.add(pagePic as String)
                            } else {
                                pagePic = ""
                                allPagePic.add(pagePic as String)
                            }
                            if (jsonObject1.has("created_time")) {
                                pageDOC = jsonObject1.optString("created_time")
                                allpageDoc.add(pageDOC as String)
                            } else {
                                pageDOC = ""
                                allpageDoc
                                        .add(pageDOC as String)
                            }
                            if (jsonObject1.has("shares")) {
                                var response2: JSONObject = jsonObject1.getJSONObject("shares")
                                mCount = response2.getString("count")
                                allPageShares.add(mCount as String)
                            } else {
                                mCount = "0"
                                allPageShares.add(mCount as String)
                            }
                            if (jsonObject1.has("comments")) {
                                var response3: JSONObject = jsonObject1.getJSONObject("comments")
                                var jsonArray2: JSONArray = response3.getJSONArray("data")
                                for (j in 0 until jsonArray2.length()) {
                                    var response4: JSONObject = jsonArray2.getJSONObject(j)
                                    if (response4.has("message")) {
                                        mCommentMessage = response4.getString("message")
                                        mallCommentMessage.add(mCommentMessage as String)

                                    } else {
                                        mCommentMessage = ""
                                        mallCommentMessage.add(mCommentMessage as String)
                                    }
                                    if (response4.has("created_time")) {
                                        mCommentCreateTime = response4.getString("created_time")
                                        mallCommentMessage.add(mCommentMessage as String)
                                    } else {
                                        mCommentCreateTime = ""
                                        mallCommentCreateTime.add(mCommentCreateTime as String)

                                    }
                                    if (response4.has("id")) {
                                        mCommentId = response4.getString("id")
                                        mallCommentId.add(mCommentId as String)
                                    } else {
                                        mCommentId = ""
                                        mallCommentId.add(mCommentId as String)
                                    }
                                }
                            }
                            if (jsonObject1.has("actions")) {
                                var jsonArray3: JSONArray = jsonObject1.getJSONArray("actions")
                                //  var response5:JSONObject =jsonObject1?.getJSONObject("actions")
                                for (k in 0 until jsonArray3.length()) {
                                    var response5: JSONObject = jsonArray3.getJSONObject(k)
                                    if (k == 0) {

                                        mActionName = response5.getString("name")
                                        allActionName.add(mActionName as String)
                                        mActionLink = response5.getString("link")
                                        allActionLink.add(mActionLink as String)

                                    }
                                }
                            }


                        }


                    }
                }


        )
        var parm = Bundle()
        parm.putString("fields", "full_picture,message,actions,id,story,created_time,comments{comment_count,created_time,message},shares")
        mRequest.parameters = parm
        mRequest.executeAsync()


    }

    fun apiBlog() {
        try {
            var apiInterface = ApiClient.Staticated.getClient().create(APIInterface::class.java)
            var callBlog: Call<ArrayList<BlogList>> = apiInterface.doGetBlogList()

            callBlog.enqueue(object : Callback<ArrayList<BlogList>> {
                override fun onResponse(call: Call<ArrayList<BlogList>>?, response: Response<ArrayList<BlogList>>?) {
                    Log.d("BlogApi", "Server Response: " + response.toString())
                    Log.d("BlogApi", "ServerBody" + response?.body().toString())




                    for (i in 0 until response?.body()!!.size) {

                        jsonObjectBlogg = response.body()!!.get(i)

                        midBlog = jsonObjectBlogg?.id

                        //setting value in award class

                        BlogDisplayingAdapter.Statified.mIDBlog.add(midBlog!!)


                        // tiitle

                        mRenderedBlog = jsonObjectBlogg?.title?.rendered

                        //title class value setter
                        mtitleBlog = Title(mRenderedBlog as String)

                        //Ecerpt

                        renderedExcerptBlog = jsonObjectBlogg?.excerpt?.rendered
                        // Excerpt class value setter
                        mExcerpt = Excerpt(renderedExcerptBlog)
                        //    LoginActivity.Statified.mEXcerptBlog.add(renderedExcerptBlog!!)

                        //Content
                        mRenderedContentBlog = jsonObjectBlogg?.content?.rendered


                        // Content class setter
                        mContent = Content(mRenderedContentBlog as String)

                        //source url


                        BlogDisplayingAdapter.Statified.BlogAward.add(BlogList(midBlog, mtitleBlog, mContent, mExcerpt))


                        //   LoginActivity.Statified.mCOntentBlog.add(mRenderedContentBlog!!)


                    }

                }

                override fun onFailure(call: Call<ArrayList<BlogList>>?, t: Throwable?) {
                    Log.e("BlogApi", "Something went wrong error " + t?.printStackTrace())
                }


            })

        } catch (e: Exception) {
            e.printStackTrace()

        }


    }

    fun apiImage() {
        try {
            var apiInterface = ApiClient.Staticated.getClient().create(APIInterface::class.java)
            callImage = apiInterface.doGetImageList()

            callImage?.enqueue(object : Callback<ArrayList<Example>> {
                override fun onResponse(call: Call<ArrayList<Example>>?, response: Response<ArrayList<Example>>?) {
                    Log.d("BlogImage", "Server Response: " + response.toString())
                    Log.d("BlogImage", "ServerBody" + response?.body().toString())

                    for (i in 0 until response?.body()!!.size) {
                        jsonObjectBlog = response.body()!!.get(i)
                        try {
                            for (j in 0 until jsonObjectBlog?.getEmbedded()?.getWpFeaturedmedia()!!.size) {
                                imgUrl = jsonObjectBlog?.getEmbedded()?.getWpFeaturedmedia()?.get(j)?.getSourceUrl()
                                Log.d("imgUrl", "" + imgUrl)
                                BlogDisplayingAdapter.Statified.msourceUrl.add(imgUrl!!)

                                Log.d("imgUrl", "" + BlogDisplayingAdapter.Statified.msourceUrl)
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }


                    }
                }

                override fun onFailure(call: Call<ArrayList<Example>>?, t: Throwable?) {
                    Log.e("BlogError", "Something went wrong error " + t?.printStackTrace())
                }


            })

        } catch (e: Exception) {
            e.printStackTrace()

        }
    }
}

