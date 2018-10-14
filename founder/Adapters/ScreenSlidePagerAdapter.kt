package com.kiit.founder.Adapters

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.kiit.founder.Fragments.FacebookPageFragment
import com.kiit.founder.Fragments.News
import com.kiit.founder.Fragments.twitterPageFragment

@Suppress("CAST_NEVER_SUCCEEDS")
class ScreenSlidePagerAdapter (context: Context, fm: FragmentManager?): FragmentPagerAdapter(fm) {
    var NUM_ITEMS: Int = 3
    var mContext: Context? = null
    var mFm: FragmentManager? = null

    init {
        this.mContext = context
        this.mFm = fm
    }

    override fun getCount(): Int {

        return NUM_ITEMS

    }

    override fun getItem(p0: Int): Fragment {


        when (p0) {
            0 // Fragment # 0 - This will show FirstFragment
            -> return (FacebookPageFragment.Staticated.newInstance(0, "Facebook"))
            1 // Fragment # 0 - This will show FirstFragment different title
            -> return (twitterPageFragment.Staticated.newInstance(1, "Twitter"))
            2 // Fragment # 1 - This will show SecondFragment
            -> return (News.Staticated.newInstance(3, "News"))

            else -> return null!!


        }

    }

    override fun getPageTitle(position: Int): CharSequence? {

        if (position == 0) {
            return "Facebook"
        }
        else if(position==1) {
            return "Twitter"

        }
        else
        {
            return "News"
        }
    }

    override fun getPageWidth(position: Int): Float {
        return 1.00f
    }

}
