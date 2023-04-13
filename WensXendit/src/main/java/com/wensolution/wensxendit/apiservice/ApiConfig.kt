package com.wensolution.wensxendit.apiservice

import com.wensolution.wensxendit.apiservice.Constant.BASE_URL
import okhttp3.Credentials
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        private var apiService: ApiService? = null
        fun getBaseApiService(username: String): ApiService? {
            val client = OkHttpClient.Builder().addInterceptor {
                val header = it.request().newBuilder().addHeader("Authorization", Credentials.basic(username, "")).build()
                it.proceed(header)
            }.build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            apiService = retrofit.create(ApiService::class.java)

            return apiService
        }
    }
}