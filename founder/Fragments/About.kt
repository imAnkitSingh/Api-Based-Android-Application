package com.kiit.founder.Fragments


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kiit.founder.Adapters.AboutSlidePagerAdapter

import com.kiit.founder.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class About : Fragment() {



    /**
     * A simple [Fragment] subclass.
     *
     */
    var mPager: ViewPager? = null
    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */

    var actiivty: Activity?=null

    var mPagerAdapter: PagerAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment


        var view=inflater.inflate(R.layout.fragment_about, container, false)

        mPager = view.findViewById(R.id.pagerAbout)

        return view
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

        mPager?.clipToPadding = false
        mPager?.pageMargin = 50
        mPagerAdapter = AboutSlidePagerAdapter(actiivty as Activity, childFragmentManager)
        mPager?.adapter = mPagerAdapter
    }

}


