package com.hongwei.kotlintest

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    companion object {
        var BASE_URL = "https://dev.buenafrutasolutions.com/api/"

        fun create() : ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return  retrofit.create(ApiInterface::class.java)
        }
    }

    @POST("account/login")
    fun login(@Body params : HashMap<String, Any>) : Call<ResponseBody>
}