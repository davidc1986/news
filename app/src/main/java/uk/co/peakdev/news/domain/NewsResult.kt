package uk.co.peakdev.news.domain

sealed class NewsResult {
    data class Success<out T>(val value: T): NewsResult()
    object Error : NewsResult()
}