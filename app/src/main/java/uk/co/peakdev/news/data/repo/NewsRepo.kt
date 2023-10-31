package uk.co.peakdev.news.data.repo

import uk.co.peakdev.news.data.Result
import uk.co.peakdev.news.data.Result.Error
import uk.co.peakdev.news.data.Result.Success
import uk.co.peakdev.news.data.api.ApiResult
import uk.co.peakdev.news.data.api.NewsApi
import uk.co.peakdev.news.data.api.mapToResult
import uk.co.peakdev.news.data.api.model.NewsResponse
import javax.inject.Inject

class NewsRepo @Inject constructor(
    private val newsApi: NewsApi
) {

    suspend fun fetchHeadlines(): Result<NewsResponse> {
        return when (val apiResult = newsApi.fetchNews().mapToResult()) {
            is ApiResult.Success -> {
                Success(apiResult.value)
            }
            is ApiResult.Error -> {
                Error()
            }
        }
    }
}