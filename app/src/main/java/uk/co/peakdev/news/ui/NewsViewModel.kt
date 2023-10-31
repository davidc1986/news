package uk.co.peakdev.news.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uk.co.peakdev.news.data.Result
import uk.co.peakdev.news.data.repo.NewsRepo
import uk.co.peakdev.news.ui.model.Headline
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepo: NewsRepo
): ViewModel() {

    private val _uiState: MutableStateFlow<NewsUiState> = MutableStateFlow(NewsUiState.Loading)
    val uiState: StateFlow<NewsUiState> = _uiState

    init {
        viewModelScope.launch {
            when (val result = newsRepo.fetchHeadlines()) {
                is Result.Success -> {
                    val headlines = result.value.articles.map { article ->
                        Headline(
                            source = article.source,
                            date = article.publishedAt,
                            title = article.title,
                            description = article.description,
                            author = article.author
                        )
                    }
                    _uiState.value = NewsUiState.Headlines(headlines)

                }
                is Result.Error -> _uiState.value = NewsUiState.Error
            }
        }
    }
}