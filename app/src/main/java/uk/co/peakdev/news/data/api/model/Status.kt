package uk.co.peakdev.news.data.api.model

import com.squareup.moshi.Json

enum class Status {
    @Json(name = "ok") OK,  @Json(name = "error") ERROR
}