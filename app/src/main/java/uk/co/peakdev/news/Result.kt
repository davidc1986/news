package uk.co.peakdev.news

sealed class Result<T> {
    data class Success<T>(val value: T): Result<T>()
    class Error<T> : Result<T>()
}