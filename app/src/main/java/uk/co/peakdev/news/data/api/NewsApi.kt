package uk.co.peakdev.news.data.api

import retrofit2.Response
import retrofit2.http.GET
import uk.co.peakdev.news.data.api.model.HeadlinesResponse

interface NewsApi {

    @GET("/v2/top-headlines?country=gb")
    suspend fun fetchHeadlines(): Response<HeadlinesResponse>
}