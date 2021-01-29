package net.borlis.mapboxandroidapp.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {

    private val loggingInterceptor = HttpLoggingInterceptor().apply { level = logLevel }

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    val client: ApiInterface by lazy {
        val client = OkHttpClient.Builder()
            .addNetworkInterceptor(loggingInterceptor)
            .connectTimeout(TIMEOUT_MINUTE, TimeUnit.MINUTES)
            .readTimeout(TIMEOUT_MINUTE, TimeUnit.MINUTES)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        retrofit.create(ApiInterface::class.java)
    }

    companion object {
        private const val BASE_URL: String = "https://drive.google.com/file/"
        private const val TIMEOUT_MINUTE: Long = 1
        private val logLevel = HttpLoggingInterceptor.Level.BODY
    }
}