package uk.co.peakdev.news.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
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
        val httpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val urlWithAuth = chain.request().url().newBuilder()
                    .addQueryParameter("access_key", "f1c377b4bf6e4b38d2dcac3b95e68880")
                    .build()
                chain.proceed(chain.request().newBuilder().url(urlWithAuth).build())
            }
            .build()

        return Retrofit.Builder()
            .baseUrl("http://api.mediastack.com/v1/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(httpClient)
            .build()
            .create(NewsApi::class.java)
    }
}