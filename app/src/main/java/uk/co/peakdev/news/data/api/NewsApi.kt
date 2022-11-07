package uk.co.peakdev.news.data.api

import retrofit2.Response
import retrofit2.http.GET
import uk.co.peakdev.news.data.api.model.NewsResponse

interface NewsApi {

    @GET("news?countries=gb")
    suspend fun fetchNews(): Response<NewsResponse>
}