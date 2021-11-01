package com.coding.test.net


import android.util.Log
import com.coding.test.model.remote.BlogService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @Author zhangke
 * @Date   on 2021/11/1
 */

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    private  val BASE_URL = "https://arcblockio.cn"
    private  val IMEOUT_MILLS = 20L
    @Provides
    fun provideBlogService(retrofit: Retrofit): BlogService = retrofit.create(BlogService::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(okHttp: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttp)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(IMEOUT_MILLS, TimeUnit.SECONDS)
            .readTimeout(IMEOUT_MILLS, TimeUnit.SECONDS).addInterceptor(HttpLoggingInterceptor { message ->
                Log.d("okhttp", message)
            }.setLevel(HttpLoggingInterceptor.Level.BODY))
                   .hostnameVerifier { _, _ -> true }
        return builder.build()
    }


}