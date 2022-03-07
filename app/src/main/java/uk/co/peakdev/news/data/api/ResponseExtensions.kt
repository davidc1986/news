package uk.co.peakdev.news.data.api

import retrofit2.Response
import java.io.IOException

fun <T: Any> Response<out T>.mapToResult(): Result<T> {
    if (isSuccessful) {
        body()?.let {
            return Result.success(it)
        }
    }

    return Result.failure(IOException())
}