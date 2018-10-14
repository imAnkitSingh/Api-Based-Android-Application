package com.kiit.founder.ApiClient

import com.kiit.founder.Pojo.BlogList
import com.kiit.founder.Pojo.Example
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
interface APIInterface {

    /* @GET("/wp-json/wp/v2/posts?categories=58")
     fun doGetListResources(): Call<MultipleResource>  */


    /*  @POST("/api/users")
      fun createUser(@Body user: User): Call<User>
      */

    @Headers("Content-type:application/json")
    //  @GET("/wp-json/wp/v2/posts?categories=58&per_page=30&page=1&_embed")
    // fun doGetUserList():Call <ArrayList<UserList>>

//    fun doGetUserList():Call <ArrayList<Awards>>
    @GET("/wp-json/wp/v2/posts?categories=67&per_page=30&page=1&_embed")
    fun doGetBlogList():Call<ArrayList<BlogList>>


    @GET("/wp-json/wp/v2/posts?categories=67&per_page=30&page=1&_embed")
    fun doGetImageList():Call<ArrayList<Example>>



}




