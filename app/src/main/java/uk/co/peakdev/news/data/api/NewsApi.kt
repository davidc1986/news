package uk.co.peakdev.news.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import uk.co.peakdev.news.data.api.model.HeadlinesResponse

interface NewsApi {

    @Headers("x-Api-Key: a1ea0fdabf7441d189f1b450ba4c14da")
    @GET("/v2/top-headlines?country=gb")
    suspend fun fetchHeadlines(): Response<HeadlinesResponse>
}