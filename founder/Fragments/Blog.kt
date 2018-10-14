package com.kiit.founder.Fragments


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import com.kiit.founder.Adapters.BlogDisplayingAdapter

import com.kiit.founder.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */


class Blog : Fragment() {





    //recyclerView DEcleration

    var mBlogRecyclerView: RecyclerView?=null
    // Blog Adapter decleration
    var  mBlogDisplayingAdapter: BlogDisplayingAdapter?=null

    // Activity context
    var myactivity:Activity?=null



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        var view =inflater.inflate(R.layout.fragment_blog, container, false)

        mBlogRecyclerView=view?.findViewById(R.id.blogRecyclerView)
        return  view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        myactivity=context as Activity
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        myactivity = activity
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mBlogDisplayingAdapter=BlogDisplayingAdapter()
        mBlogDisplayingAdapter?.notifyDataSetChanged()
        mBlogRecyclerView?.adapter = mBlogDisplayingAdapter
        mBlogRecyclerView?.layoutManager = LinearLayoutManager(activity) as RecyclerView.LayoutManager?
        mBlogRecyclerView?.itemAnimator = DefaultItemAnimator()

    }



}
