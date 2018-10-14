package com.kiit.founder.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.facebook.FacebookSdk
import com.kiit.founder.Pojo.BlogList
import com.kiit.founder.R
import com.squareup.picasso.Picasso

class BlogDisplayingAdapter
    : RecyclerView.Adapter<BlogDisplayingAdapter.NavViewHolder>() {


    object Statified {
        var BlogAward = arrayListOf<BlogList>()
        var mIDBlog = arrayListOf<String>()
        var msourceUrl = arrayListOf<String>()
    }


    var imageurl = Statified.msourceUrl

    init {

    }

    override fun onBindViewHolder(p0: NavViewHolder, p1: Int) {


        try {
            p0.awardTitle?.text = Statified.BlogAward.get(p1).title?.rendered
            // p0.awardTitle?.setText(mTitle?.get(p1))
            //  p0.awardExcerpt?.setText(mExcerpt?.get(p1))
            p0.awardExcerpt?.text = Statified.BlogAward.get(p1).excerpt?.rendered
            //  p0.awardContent?.setText(mContent?.get(p1))
            var count = 0

            p0.knowMore?.setOnClickListener({
                var cp1 = p1

                if (count == 0) {
                    //                           p0.awardExcerpt?.setText(mContent?.get(cp1))
                    p0.awardExcerpt?.text = Statified.BlogAward.get(p1).content?.rendered
                    count = 1
                } else {
                    //  p0.awardExcerpt?.setText(mExcerpt?.get(p1))
                    p0.awardExcerpt?.text = Statified.BlogAward.get(p1).excerpt?.rendered
                    count = 0
                }


            })


//                    Picasso.get().load(imageurl?.get(p1)).resize(700,550).into(p0?.img)
            Picasso.get().load(imageurl.get(p1)).resize(500,500).into(p0.img)

        } catch (e: Exception) {
            e.printStackTrace()
        }


    }

    override fun getItemCount(): Int {
        if (Statified.BlogAward.isEmpty()) {
            return 0
        } else {
            return (Statified.BlogAward).size
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): NavViewHolder {
        var itemView = LayoutInflater.from(p0.context)
                .inflate(R.layout.row_custome_blog_displaying_adapter, p0, false)
        var returnThis = BlogDisplayingAdapter.NavViewHolder(itemView)
        return returnThis

    }


    class NavViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView as View) {
        var mawardBaseContent: RelativeLayout? = null
        var awardTitle: TextView? = null
        var awardContent: TextView? = null
        var awardExcerpt: TextView? = null
        var knowMore: RelativeLayout? = null
        var img: ImageView? = null

        init {
            this.img = itemView?.findViewById(R.id.imgBlog)
            this.knowMore = itemView?.findViewById(R.id.knowMoreBlog)
            this.mawardBaseContent = itemView?.findViewById(R.id.BlogBaseContent)
            this.awardTitle = itemView?.findViewById(R.id.titleBlog)
            // this.awardContent=itemView?.findViewById(R.id.contentAward)
            this.awardExcerpt = itemView?.findViewById(R.id.blogexcerpt)
        }


    }
}
