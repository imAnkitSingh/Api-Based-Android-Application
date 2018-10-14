package com.kiit.founder.Pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Example
{
    @SerializedName("_embedded")
    @Expose
    private var embedded:Embedded?=null

    fun   getEmbedded():Embedded? {
        return this.embedded
    }

    fun setEmbedded( embedded:Embedded) {
        this.embedded = embedded
    }

}