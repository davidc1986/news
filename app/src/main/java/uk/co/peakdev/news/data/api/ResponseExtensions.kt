package uk.co.peakdev.news.data.api

import retrofit2.Response
import uk.co.peakdev.news.Result

fun <T: Any> Response<out T>.mapToResult(): Result<T> {
    if (isSuccessful) {
        body()?.let {
            return Result.Success(it)
        }
    }

    return Result.Error()
}