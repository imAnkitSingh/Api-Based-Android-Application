package com.kiit.founder.Fragments


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.kiit.founder.Adapters.ScreenSlidePagerAdapter
import com.kiit.founder.MainActivity

import com.kiit.founder.R
import com.kiit.founder.falseActiivty

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class MainFrameFragment : Fragment() {

    var mPager: ViewPager? = null
    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    var actiivty:Activity?=null

    var mPagerAdapter: PagerAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        var view=inflater.inflate(R.layout.fragment_main_frame, container, false)
        mPager =  view.findViewById(R.id.pager)
        mPager?.clipToPadding = false
        return  view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        actiivty=context as Activity
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        actiivty=activity
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mPagerAdapter = ScreenSlidePagerAdapter(actiivty as Activity,childFragmentManager)
        mPager?.adapter = mPagerAdapter
        mPagerAdapter?.notifyDataSetChanged()

    }
}
