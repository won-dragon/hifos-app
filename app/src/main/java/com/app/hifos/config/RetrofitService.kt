package kf.hifos.edu.config

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kf.hifos.edu.dto.UserTodoDTO
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface RetrofitService {

    @GET("user-todo")
    suspend fun userTodoAll(): List<UserTodoDTO>

    @POST("user-todo")
    suspend fun userTodo(@Body userTodoDTO: UserTodoDTO) : UserTodoDTO

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