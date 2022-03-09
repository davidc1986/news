package uk.co.peakdev.news.domain

sealed class NewsResult<T> {
    data class Success<T>(val value: T): NewsResult<T>()
    class Error<T> : NewsResult<T>()
}