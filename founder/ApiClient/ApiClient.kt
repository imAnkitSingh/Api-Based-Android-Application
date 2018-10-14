package com.kiit.founder.ApiClient

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    // var retrofit: Retrofit? = null

    object Staticated {

        fun getClient(): Retrofit {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()


            var   retrofit = Retrofit.Builder()
                    .baseUrl("https://achyutasamanta.com")
                    // .callbackExecutor(Executors.newSingleThreadExecutor())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()



            return (retrofit as Retrofit)
        }

    }


}
