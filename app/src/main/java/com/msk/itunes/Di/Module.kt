package com.msk.itunes.Di

import android.app.Application
import androidx.room.Room
import com.msk.itunes.Api.itunesApi
import com.msk.itunes.Db.Dao
import com.msk.itunes.Db.itunesDatabase
import com.msk.itunes.Repository.DetailRepository.DetailRepository
import com.msk.itunes.Repository.DetailRepository.DetailRepositoryImp
import com.msk.itunes.Repository.FavoritesRepository.FavoritesRepository
import com.msk.itunes.Repository.FavoritesRepository.FavoritesRepositoryImp
import com.msk.itunes.Repository.SearchRepository.SearchRepository
import com.msk.itunes.Repository.SearchRepository.SearchRepositoryImp
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
    fun provideSearchRepository(api: itunesApi): SearchRepository {
        return SearchRepositoryImp(api)
    }
    @Provides
    @Singleton
    fun provideDetailRepository(api: itunesApi,dao: Dao): DetailRepository {
        return DetailRepositoryImp(api=api, dao = dao)
    }
@Provides
    @Singleton
    fun provideFavoritesRepository(api: itunesApi,dao: Dao): FavoritesRepository {
        return FavoritesRepositoryImp(api = api, dao = dao)
    }

    @Provides
    @Singleton
    fun provideDatabase(app:Application):Dao{
      return Room.databaseBuilder(
          app,itunesDatabase::class.java,
        "ItunesDB").build().dao

    }


}