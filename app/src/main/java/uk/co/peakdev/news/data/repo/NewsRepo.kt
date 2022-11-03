package uk.co.peakdev.news.data.repo

import uk.co.peakdev.news.Result
import uk.co.peakdev.news.data.api.NewsApi
import uk.co.peakdev.news.data.api.mapToResult
import uk.co.peakdev.news.data.api.model.HeadlinesResponse
import javax.inject.Inject

class NewsRepo @Inject constructor(
    private val newsApi: NewsApi
) {

    suspend fun fetchHeadlines(): Result<HeadlinesResponse> {
        return newsApi.fetchHeadlines().mapToResult()
    }

}