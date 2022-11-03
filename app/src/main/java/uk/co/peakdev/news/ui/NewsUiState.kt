package uk.co.peakdev.news.ui

import uk.co.peakdev.news.ui.model.Headline

sealed class NewsUiState {
    object Initial: NewsUiState()
    object Loading: NewsUiState()
    data class Headlines(val headlines: List<Headline>): NewsUiState()
    object Error: NewsUiState()
}