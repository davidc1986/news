package uk.co.peakdev.news.data.api.model

import com.squareup.moshi.Json

data class NewsResponse(
    val pagination: Pagination,
    @field:Json(name = "data") val articles: List<Article>
    ) {

    data class Pagination(
        val limit: Int,
        val offset: Int,
        val count: Int,
        val total: Int
    )

    data class Article(
        val author: String?,
        val title: String,
        val description: String,
        val url: String,
        val source: String,
        val image: String?,
        @field:Json(name = "published_at") val publishedAt: String,
    )

}
