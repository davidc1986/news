package uk.co.peakdev.news.data.api.model

import com.squareup.moshi.Json

data class HeadlinesResponse(
    val status: Status,
    @Json(name = "code") val errorCode: String?,
    @Json(name = "message") val errorMessage: String?,
    val totalResults: Int,
    val articles: List<Article>
    ) {

    data class Article(
        val source: Source,
        val author: String?,
        val title: String,
        val description: String,
        val url: String,
        @Json(name = "urlToImage") val imageUrl: String,
        val publishedAt: String,
        val content: String?
    ) {

        data class Source(
            val name: String
        )

    }

}
