package uk.co.peakdev.news.data.api

import retrofit2.Response

fun <T: Any> Response<out T>.mapToResult(): ApiResult<T> {
    if (isSuccessful) {
        body()?.let {
            return ApiResult.Success(it)
        }
    }

    return ApiResult.Error()
}