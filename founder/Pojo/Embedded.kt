package com.kiit.founder.Pojo
import retrofit2.Call
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
class Embedded
{

    @SerializedName("wp:featuredmedia")
    @Expose
    private var wpFeaturedmedia: ArrayList<WpFeaturedmedium_>? = null


    fun getWpFeaturedmedia(): ArrayList<WpFeaturedmedium_>? {
        return wpFeaturedmedia
    }

    fun setWpFeaturedmedia(wpFeaturedmedia: ArrayList<WpFeaturedmedium_>) {
        this.wpFeaturedmedia = wpFeaturedmedia
    }




}