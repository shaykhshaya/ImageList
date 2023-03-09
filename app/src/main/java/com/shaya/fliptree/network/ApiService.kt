package com.shaya.fliptree.network

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.shaya.fliptree.BaseApplication
import com.shaya.fliptree.response.ImageItem
import com.shaya.fliptree.utils.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET



private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val client: OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(ChuckerInterceptor(BaseApplication.instance.applicationContext))
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .client(client)
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    @GET("products")
    suspend fun getImages():List<ImageItem>
}

object ImageApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}