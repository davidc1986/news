package uk.co.peakdev.news.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import uk.co.peakdev.news.BuildConfig
import uk.co.peakdev.news.data.api.NewsApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun providesNewsApi(): NewsApi {
        val httpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val requestWithAuthHeader = chain.request().newBuilder()
                    .addHeader("x-Api-Key", BuildConfig.API_KEY)
                    .build()
                chain.proceed(requestWithAuthHeader)
            }
            .build()

        return Retrofit.Builder()
            .baseUrl("https://newsapi.org")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(httpClient)
            .build()
            .create(NewsApi::class.java)
    }
}