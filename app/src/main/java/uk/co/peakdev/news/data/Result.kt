package uk.co.peakdev.news.data

sealed class Result<T> {
    data class Success<T>(val value: T): Result<T>()
    class Error<T>: Result<T>()
}