package uk.co.peakdev.news.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import uk.co.peakdev.news.data.api.NewsApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun providesNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }
}