package org.ortynskyi.movier.network

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RestApi {

    private val MOVIES_BASE_URL: String = "https://api.themoviedb.org/"
    private val MOVIES_API_KEY: String = "efb4290d9f59cc933f0d050df363db5a"

    val movieApi: MovieApi

    init {
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.HEADERS
        logging.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addNetworkInterceptor(logging)

        clientBuilder.addInterceptor({
            val original: Request = it.request()
            val httpUrl: HttpUrl = original.url()
            val url: HttpUrl = httpUrl.newBuilder()
                    .addQueryParameter("api_key", MOVIES_API_KEY)
                    .build()
            val requestBuilder : Request.Builder = original.newBuilder().url(url)
            val request: Request = requestBuilder.build()
            it.proceed(request)
        })

        val movieRetrofit = Retrofit.Builder()
                .baseUrl(MOVIES_BASE_URL)
                .client(clientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        movieApi = movieRetrofit.create(MovieApi::class.java)
    }
}