package uk.co.peakdev.news.ui.model

data class Headline(
    val source: String,
    val date: String,
    val title: String,
    val description: String,
    val author: String?
)
