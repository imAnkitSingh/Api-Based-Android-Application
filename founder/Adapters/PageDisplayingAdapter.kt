package com.kiit.founder.Adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView

import com.facebook.FacebookSdk
import com.facebook.FacebookSdk.getApplicationContext
import com.kiit.founder.R
import com.kiit.founder.SplashScreen
import com.squareup.picasso.Picasso

class PageDisplayingAdapter(): RecyclerView.Adapter<PageDisplayingAdapter.MyViewHolderr>()

{


    var _Pagemsg =SplashScreen.Statified.allPageMessage
    var _Pageid=SplashScreen.Statified.allPageId
    var _PagedateOfCreation=SplashScreen.Statified.allpageDoc
    //    var mPageContext: Context?=null
    var _PagefullPic=SplashScreen.Statified.allPagePic
    var _PageStory=SplashScreen.Statified.allPageStory
    //  var _Shares:ArrayList<String>?=null
    //  var _Commentmsg:ArrayList<String>?=null
    var _ActionLink=SplashScreen.Statified.allActionLink
  //  var onButtonClickListener: onButtonClickListener



    init {

    //    this.onButtonClickListener=onButtonClickListener
        Log.d("adapterFacebookId",""+_Pageid)




    }



    override fun onBindViewHolder(p0: MyViewHolderr, p1: Int) {

        try {


            p0.mPageCreatedTime?.text = _PagedateOfCreation.get(p1)
            p0.mPageStory?.text = _PageStory.get(p1)



            var mPagephotoArrangment= _PagefullPic.get(p1)
            if(mPagephotoArrangment=="")
            {
                p0.mPagePhoto?.visibility= View.INVISIBLE
            }
            else {
                p0.mPagePhoto?.visibility= View.VISIBLE
                Picasso.get().load(mPagephotoArrangment).resize(500,500).into(p0.mPagePhoto)
//
// Glide.with(mPageContext).load(mPagephotoArrangment).into(p0?.mPagePhoto)
            }

        }

        catch (e: Exception)
        {
            e.printStackTrace()
        }


        p0.pageMessage?.text = _Pagemsg.get(p1)
        p0.mPagePostContainer?.setOnClickListener({
           // onButtonClickListener.onImageClick(_ActionLink,p1)
        })


    }

    override fun getItemCount(): Int {
        if ( _PagedateOfCreation == null ) {
            return 0
        } else {
            return _PagedateOfCreation.size
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolderr {
        var itemView = LayoutInflater.from(p0.context)
                .inflate(R.layout.row_custome_page_displayy, p0, false)
        return MyViewHolderr(itemView)
    }

    class MyViewHolderr(view: View) : RecyclerView.ViewHolder(view) {
        var pageMessage: TextView?=null
        var mPageCreatedTime: TextView?=null
        var mPagePhoto: ImageView?=null
        var mPageStory: TextView?=null
        var mPagePostContainer: RelativeLayout?=null
        init {
            this.pageMessage=view.findViewById(R.id.pageMessage)
            this.mPageCreatedTime=view.findViewById(R.id.pageDOC)
            this.mPagePhoto=view.findViewById(R.id.pageImage)
            this.mPagePostContainer=view.findViewById(R.id.pageBaseContent)
            this.mPageStory=view.findViewById(R.id.pageStory)

        }
    }




}
interface onButtonClickListener {
    fun onImageClick(_mActionLink: ArrayList<String>,position: Int)
}

