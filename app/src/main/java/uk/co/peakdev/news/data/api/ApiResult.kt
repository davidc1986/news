package uk.co.peakdev.news.data.api

sealed class ApiResult<T> {
    data class Success<T>(val value: T): ApiResult<T>()
    class Error<T> : ApiResult<T>()
}