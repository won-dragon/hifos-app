package com.app.hifos.config

import com.app.hifos.dto.UserTodoDTO
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface RetrofitService {

    @GET("user-todo")
    suspend fun userTodoSearchAll(): List<UserTodoDTO>

    @GET("user-todo/user/{userId}")
    suspend fun userTodoSearch(@Path("userId") userId : String): List<UserTodoDTO>

    @PUT("user-todo/todo/{id}")
    suspend fun userTodoMod(@Path("id") id : Long, @Body userTodoDTO: UserTodoDTO) : UserTodoDTO

    @POST("user-todo")
    suspend fun userTodoReg(@Body userTodoDTO: UserTodoDTO) : UserTodoDTO

    @DELETE("user-todo/todo/{id}")
    suspend fun userTodoRemove(@Path("id") id : Long)

    companion object { // static 처럼 공유객체로 사용가능함. 모든 인스턴스가 공유하는 객체로서 동작함.
        var API : RetrofitService
        private var BASE_URL = "http://localhost:9100/hifos-api/v1/" // 주소

        private val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(20L, TimeUnit.SECONDS)
            .readTimeout(20L, TimeUnit.SECONDS)
            .writeTimeout(20L, TimeUnit.SECONDS)
            .build()

        init {
            val gson : Gson =   GsonBuilder().setLenient().create()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            API = retrofit.create(RetrofitService::class.java)
        }
    }
}