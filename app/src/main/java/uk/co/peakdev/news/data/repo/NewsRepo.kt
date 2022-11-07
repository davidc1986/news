package uk.co.peakdev.news.data.repo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uk.co.peakdev.news.data.Result
import uk.co.peakdev.news.data.api.ApiResult
import uk.co.peakdev.news.data.api.NewsApi
import uk.co.peakdev.news.data.api.mapToResult
import uk.co.peakdev.news.data.api.model.NewsResponse
import javax.inject.Inject

class NewsRepo @Inject constructor(
    private val newsApi: NewsApi
) {

    val headlines: Flow<Result<NewsResponse>> =  flow {
        emit(Result.Loading())
        when (val apiResult = newsApi.fetchNews().mapToResult()) {
            is ApiResult.Success -> {
                emit(Result.Success(apiResult.value))
            }
            is ApiResult.Error -> {
                emit(Result.Error())
            }
        }
    }
}