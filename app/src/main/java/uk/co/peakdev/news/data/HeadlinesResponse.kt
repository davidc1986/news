package uk.co.peakdev.news.data

import com.google.gson.annotations.SerializedName

data class HeadlinesResponse(
    val status: String,
    @SerializedName("code") val errorCode: String?,
    @SerializedName("message") val errorMessage: String?,
    val totalResults: Int,
    val articles: List<Article>
    ) {

    data class Article(
        val source: Source,
        val author: String?,
        val title: String,
        val description: String,
        val url: String,
        @SerializedName("urlToImage") val imageUrl: String,
        val publishedAt: String,
        val content: String?
    ) {

        data class Source(
            val name: String
        )

    }

}
