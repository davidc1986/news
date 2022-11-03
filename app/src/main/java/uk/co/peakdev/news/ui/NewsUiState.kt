package uk.co.peakdev.news.ui

sealed class NewsUiState {
    object Initial: NewsUiState()
    object Loading: NewsUiState()
    object News: NewsUiState()
    object Error: NewsUiState()
}