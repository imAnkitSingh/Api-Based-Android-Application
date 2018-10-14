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
import android.widget.RelativeLayout
import android.widget.TextView
import com.facebook.CallbackManager
import com.kiit.founder.Adapters.PageDisplayingAdapter

import com.kiit.founder.R
import com.kiit.founder.SplashScreen
import kotlinx.android.synthetic.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FacebookPageFragment : Fragment() {

    private var title: String? = null
    private var page: Int = 0

// variable for layout resource file

    private var myActivity: Activity?=null

    private var pageDisplayingAdapters: PageDisplayingAdapter?=null
    //recycler View
    private  var  pageDisplayRecyclerView: RecyclerView?=null
    // Empty section handling
    private var empty: TextView?=null



    object Staticated
    {
        fun newInstance(page: Int, title: String): FacebookPageFragment {
            val fragmentFirst = FacebookPageFragment()
            val args = Bundle()
            args.putInt("someInt", page)
            args.putString("someTitle", title)
            fragmentFirst.arguments = args
            return fragmentFirst
        }

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
       var view=inflater.inflate(R.layout.fragment_facebook_page2, container, false)

        pageDisplayRecyclerView=view.findViewById(R.id.pageRecyclerView)
        empty=view.findViewById(R.id.facebookempty)

        return   view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        page = arguments!!.getInt("someInt", 0)
        title = arguments!!.getString("someTitle")
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

    myActivity=context as Activity
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
            myActivity=activity
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (SplashScreen.Statified.allPageId!=null)
        {
            pageDisplayingAdapters = PageDisplayingAdapter( )
            pageDisplayingAdapters?.notifyDataSetChanged()
            pageDisplayRecyclerView?.setHasFixedSize(true)
            pageDisplayRecyclerView?.layoutManager = LinearLayoutManager(myActivity as Context)
            pageDisplayRecyclerView?.itemAnimator = DefaultItemAnimator()
            pageDisplayRecyclerView?.adapter = pageDisplayingAdapters

        }
        else
        {

            empty?.visibility=View.VISIBLE

            pageDisplayRecyclerView?.visibility =View.INVISIBLE
        }

    }

}
