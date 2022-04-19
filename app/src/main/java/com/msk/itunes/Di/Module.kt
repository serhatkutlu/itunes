package com.msk.itunes.Di

import android.util.Log
import com.msk.itunes.Api.itunesApi
import com.msk.itunes.Repository.SearchRepository
import com.msk.itunes.Repository.SearchRepositoryImp
import com.msk.itunes.Util.Constants.BaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {



    @Singleton
    @Provides
    fun provideLogInterceptor()= HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor)=OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient):Retrofit{

        return Retrofit.Builder().baseUrl(BaseUrl)
            .client(client).addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideitunesApi(retrofit: Retrofit):itunesApi{
        return retrofit.create(itunesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: itunesApi):SearchRepository{
        return SearchRepositoryImp(api)
    }


}