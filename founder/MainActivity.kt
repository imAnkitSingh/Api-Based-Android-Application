package com.kiit.founder

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.media.Image
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.kiit.founder.Adapters.ScreenSlidePagerAdapter
import com.kiit.founder.MainActivity.Staticated.decodeSampledBitmapFromResource
import com.mikhaellopez.circularimageview.CircularImageView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import android.graphics.Color.parseColor
import android.R.attr.fragment
import android.R.attr.start
import android.content.Intent
import android.graphics.Color
import android.os.Handler
import android.support.design.widget.BottomNavigationView
import android.widget.Toast
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.kiit.founder.Fragments.About
import com.kiit.founder.Fragments.Blog
import com.kiit.founder.Fragments.FacebookPageFragment
import com.kiit.founder.Fragments.MainFrameFragment


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,BottomNavigationView.OnNavigationItemSelectedListener {

    //bottomNaveView

    var bottomNavigationView:BottomNavigationView?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_URGENT_DISPLAY)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


    }

    override fun onStart() {
        super.onStart()

    }

    override fun onResume() {
        super.onResume()

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        //fetching bottom navigation view

//pageradapter
      /*  mPager =  findViewById(R.id.pager)
        mPager?.clipToPadding = false */

        bottomNavigationView=findViewById(R.id.bottomNav)

        Thread(Runnable {

            android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_LOWEST)
            nav_view.setNavigationItemSelectedListener(this)

            bottomNavigationView?.setOnNavigationItemSelectedListener(this)


        }).start()

       /* mPagerAdapter = ScreenSlidePagerAdapter(this,supportFragmentManager)
        mPager?.adapter = mPagerAdapter
        mPagerAdapter?.notifyDataSetChanged()
*/
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
       // menuInflater.inflate(R.menu.my_navigation_items,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> {
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the notification here action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

                // Handle the Download  here action

            }
            R.id.nav_manage -> {



            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
            R.id.nav_blog ->{

                var blogFragment= Blog()
                this.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.detail_fragment,blogFragment , "BlogFragment")
                        .commit()

            }
            R.id.nav_about ->{
                var aboutFragment= About()
                this.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.detail_fragment,aboutFragment , "aboutFragment")
                        .commit()
            }
            R.id.nav_home -> {

                var  mainFrameFragment = MainFrameFragment()

                this.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.detail_fragment,mainFrameFragment , "MainFrameFragment")
                        .commit()


            }
        }





        drawer_layout.closeDrawer(GravityCompat.START)
        return true
     }




   object  Staticated
    {
        fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
            // Raw height and width of image
            val (height: Int, width: Int) = options.run { outHeight to outWidth }
            var inSampleSize = 1

            if (height > reqHeight || width > reqWidth) {

                val halfHeight: Int = height / 2
                val halfWidth: Int = width / 2

                // Calculate the largest inSampleSize value that is a power of 2 and keeps both
                // height and width larger than the requested height and width.
                while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                    inSampleSize *= 2
                }
            }

            return inSampleSize
        }

        fun decodeSampledBitmapFromResource(
                res: Resources,
                resId: Int,
                reqWidth: Int,
                reqHeight: Int
        ): Bitmap {
            // First decode with inJustDecodeBounds=true to check dimensions
            return BitmapFactory.Options().run {
                inJustDecodeBounds = true
                BitmapFactory.decodeResource(res, resId, this)

                // Calculate inSampleSize
                inSampleSize = calculateInSampleSize(this, reqWidth, reqHeight)

                // Decode bitmap with inSampleSize set
                inJustDecodeBounds = false

                BitmapFactory.decodeResource(res, resId, this)
            }
        }
    }




}
