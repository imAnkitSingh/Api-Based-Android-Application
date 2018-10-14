package com.kiit.founder.Pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WpFeaturedmedium_ {
    @SerializedName("source_url")
    @Expose
    private var sourceUrl:String?=null

    fun getSourceUrl(): String? {
        return sourceUrl
    }

    fun setSourceUrl(sourceUrl: String) {
        this.sourceUrl = sourceUrl
    }

}