package com.kiit.founder.Fragments


import android.app.Activity
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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
class Foundation : Fragment() {


    var mkiss:String="https://i.ytimg.com/vi/0sskIEttdzQ/maxresdefault.jpg"
    var mkiit:String="https://i.ytimg.com/vi/PGoq6F7t394/maxresdefault.jpg"
    var mart:String="https://i.ytimg.com/vi/U5EV1Fx8Hc4/maxresdefault.jpg"


    //foundatioon variable initilization
    var imagekiss: ImageView?=null
    var titleKiss: TextView?=null
    var subTitleKiss: TextView?=null
    var kiisContent: TextView?=null
    var imageKiit: ImageView?=null
    var titleKiit: TextView?=null
    var subTitleKiit: TextView?=null
    var kiitContent: TextView?=null
    var imageart: ImageView?=null
    var titleart: TextView?=null
    var subTitleart: TextView?=null
    var artContent: TextView?=null
    var mlearnKiss: CardView?=null
    var mdonate: CardView?=null
    var mlearnKiit: CardView?=null
    var mlearnArt: CardView?=null




    //Foundation variable initilization declearation
    var mContentkiss:String="Kalinga Institute of Social Sciences – KISS, Bhubaneswar," +
            " India is a fully free, fully residential home for 27000 poorest of the poor indigenous " +
            "children who are provided holistic education from Kindergarten to Post Graduation along with lodging, boarding," +
            " health care facilities besides vocational, life skills empowerment."
    var mtitlekiss:String="Kalinga Institute of Social Sciences (KISS)"
    var mSubTitlekiss:String="A HOME FOR 2700 INDIGENOUS CHILDREN"

    var mContentKiit:String="An Institute aimed to create an advanced centre of professional learning of international " +
            "standing where pursuit of knowledge and excellence shall reign supreme, unfettered " +
            "by the barriers of nationality, language, cultural plurality and religion.Courses like" +
            "B.Tech,M.Tech,MBBS,Law,MBA,PGDBM are offered to more than 27000 students of 45 different countries."
    var mtitlekiit:String="Kalinga Institute of Industrial Technology (KIIT)"
    var mSubtitleKiit:String="Deemed to be University"

    var mContentArt:String="Founded in 2013 by Shri Achyuta Samanta, Art of Giving is a humanitarian movement engaged in spreading peace and happiness."
    var mTitle:String="AOG PHILOSOPHY OF LIFE"


    private var title:String?=null
    private var page:Int= 0
    var myActivity:Activity?=null

    object Staticated
    {
        fun newFoundationInstance(page: Int, title: String): Foundation {
            val fragmentFoundation = Foundation()
            val args = Bundle()
            args.putInt("someInt", page)
            args.putString("someTitle", title)
            fragmentFoundation.arguments = args
            return fragmentFoundation
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    myActivity=context as Activity
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
    myActivity=activity
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

       var view= inflater.inflate(R.layout.fragment_foundation, container, false)
        imagekiss=view.findViewById(R.id.kissView)
        titleKiss=view.findViewById(R.id.kissTitle)
        subTitleKiss=view.findViewById(R.id.kissSubTitle)
        kiisContent=view.findViewById(R.id.kissContent)
        imageKiit=view.findViewById(R.id.kiitView)
        titleKiit=view.findViewById(R.id.kiitTitle)
        subTitleKiit=view.findViewById(R.id.kiitSubTitle)
        kiitContent=view.findViewById(R.id.kiitContent)
        imageart=view.findViewById(R.id.artView)
        titleart=view.findViewById(R.id.artTitle)
        artContent=view.findViewById(R.id.artContent)
        mlearnKiss=view.findViewById(R.id.kissLearn)
        mdonate=view.findViewById(R.id.kissDonate)
        mlearnKiit=view.findViewById(R.id.cardViewKiit)
        mlearnArt=view.findViewById(R.id.cardViewArt)
        return  view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


   /* imagekiss?.setImageBitmap(
            decodeSampledBitmapFromResource(resources, R.mipmap.kiss, 300, 300)
    )  */

        Picasso.get().load(mkiss).into(imagekiss)

    val options = BitmapFactory.Options().apply {
        inJustDecodeBounds = true
    }
 //   BitmapFactory.decodeResource(resources, R.mipmap.kiss, options)
    titleKiss?.text = mtitlekiss
    subTitleKiss?.text = mSubTitlekiss
    kiisContent?.text = mContentkiss



  /*  imageKiit?.setImageBitmap(
            decodeSampledBitmapFromResource(resources, R.mipmap.kiit, 300, 300)
    )*/
        Picasso.get().load(mkiit).into(imageKiit)
    titleKiit?.text = mtitlekiit
    subTitleKiit?.text = mSubtitleKiit
    kiitContent?.text = mContentKiit

    // art
    //   imageart?.setImageResource(R.drawable.art)

 /*   imageart?.setImageBitmap(
            decodeSampledBitmapFromResource(resources, R.mipmap.art, 300, 300)
    ) */


  //  BitmapFactory.decodeResource(resources, R.mipmap.art, options)
        Picasso.get().load(mart).into(imageart)
       titleart?.text = mTitle
       artContent?.text = mContentArt

// click handling
    mlearnKiss?.setOnClickListener({

        //  moveToKissActivity()
    })
    mdonate?.setOnClickListener({

        //  moveToDonateActivity()
    })
    mlearnKiit?.setOnClickListener({

        //  moveToKiitActivity()
    })
    mlearnArt?.setOnClickListener({

        //  moveToArtActivity()
    })


    }


}
