package com.kiit.founder.Adapters

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import android.util.Log
import android.view.ViewGroup
import com.kiit.founder.Fragments.Award_And_Achievment
import com.kiit.founder.Fragments.Contact
import com.kiit.founder.Fragments.Foundation


class AboutSlidePagerAdapter (context: Context, fm: FragmentManager?): FragmentPagerAdapter(fm)
{


    var NUM_ITEMS: Int = 3
    var mContext: Context? = null
    var mFm: FragmentManager? = null

    init {
        this.mContext = context
        this.mFm = fm
        Log.d("pagerADapater","adapter")
    }


    override fun getCount(): Int {
        return NUM_ITEMS

    }

    override fun getItem(p0: Int): Fragment {
        when (p0) {
            0 // Fragment # 0 - This will show FirstFragment
            -> return (Award_And_Achievment.Staticated.newAboutInstance(0, "AWARDs and Achievements"))
            1 // Fragment # 0 - This will show FirstFragment different title
            -> return (Foundation.Staticated.newFoundationInstance (1, "FOUNDATION"))
            2 // Fragment # 1 - This will show SecondFragment
            -> return (Contact.Staticated.newContactInstance(2, "Contact"))

            else -> return null!!


        }
    }
    override fun getPageTitle(position: Int): CharSequence? {

        if (position == 0) {
            return "AWARDS and ACHIEVEMENTS"
        }
        else if(position==1) {
            return  "FOUNDATION"

        }
        else
        {
            return "CONTACT"
        }
    }
    override fun getPageWidth(position: Int): Float {
        return 0.99f
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)

    }

    override fun getItemPosition(`object`: Any): Int {
        return super.getItemPosition(`object`)

    }


}
