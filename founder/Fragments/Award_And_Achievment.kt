package com.kiit.founder.Fragments


import android.app.Activity
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.kiit.founder.Adapters.AwardsAdapter
import com.kiit.founder.MainActivity.Staticated.decodeSampledBitmapFromResource

import com.kiit.founder.R
import com.squareup.picasso.Picasso

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class Award_And_Achievment : Fragment(){


    var mglobelImage:String="https://updateodisha.com/wp-content/uploads/2016/10/Achyut-Samant-644x330.jpg"


    private var title: String? = null
    private var page: Int = 0
    // copied design
    var globelimage: ImageView? = null
    var globelTitle: TextView? = null
    var globelContent: TextView? = null
    //  variables  contanting params
    var mglobalTitle = "GLOBAL RECOGNITIONS"
    var mglobelContent = "A visionary social architect who embarked back in 1992" +
            " on a social development mission using education as the strategic medium to eradicate" +
            " poverty and alienation from the surface of the earth, " +
            "a goal envisaged today by global leaders that goes as " +
            "Sustainable Development Goals (SDGs)"
    var myActiivty: Activity? = null

    var mawardRecyclerView: RecyclerView? = null

    object Staticated {
        fun newAboutInstance(page: Int, title: String): Award_And_Achievment {
            val fragmentAward = Award_And_Achievment()
            val args = Bundle()
            args.putInt("someInt", page)
            args.putString("someTitle", title)
            fragmentAward.arguments = args
            return fragmentAward
        }


    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        var view = inflater.inflate(R.layout.fragment_award__and__achievment, container, false)
        mawardRecyclerView = view.findViewById<RecyclerView>(R.id.awardRecyclerView)
        globelimage = view.findViewById(R.id.globel)
        globelTitle = view.findViewById(R.id.achievmentTitle)
        globelContent = view?.findViewById(R.id.achievmentcontent)
        return view

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        myActiivty = context as Activity
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)

        myActiivty = activity
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var achievment = AwardsAdapter(activity as Context)
        achievment.notifyDataSetChanged()
        mawardRecyclerView?.adapter = achievment
        mawardRecyclerView?.layoutManager = LinearLayoutManager(activity, OrientationHelper.HORIZONTAL, false)
        mawardRecyclerView?.itemAnimator = DefaultItemAnimator()
        mawardRecyclerView?.isNestedScrollingEnabled()

        //    this.globelimage?.setBackgroundResource(R.drawable.globelrecognization)
/*        globelimage?.setImageBitmap(
                decodeSampledBitmapFromResource(resources, R.mipmap.globelrecognization, 300, 300)
        )  */

        Picasso.get().load(mglobelImage).into(globelimage)
        this.globelTitle?.text = mglobalTitle
        this.globelContent?.text = mglobelContent
        val options = BitmapFactory.Options().apply {
            inJustDecodeBounds = true
        }


    }
}
