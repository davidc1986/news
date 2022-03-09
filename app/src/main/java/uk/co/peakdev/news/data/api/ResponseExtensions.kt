package uk.co.peakdev.news.data.api

import retrofit2.Response
import uk.co.peakdev.news.domain.NewsResult

fun <T: Any> Response<out T>.mapToResult(): NewsResult<T> {
    if (isSuccessful) {
        body()?.let {
            return NewsResult.Success(it)
        }
    }

    return NewsResult.Error()
}