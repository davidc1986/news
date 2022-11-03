package uk.co.peakdev.news.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uk.co.peakdev.news.data.Result
import uk.co.peakdev.news.data.api.model.Status
import uk.co.peakdev.news.data.repo.NewsRepo
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepo: NewsRepo
): ViewModel() {

    private val _uiState: MutableStateFlow<NewsUiState> = MutableStateFlow(NewsUiState.Initial)
    val uiState: StateFlow<NewsUiState> = _uiState

    init {
        viewModelScope.launch {
            newsRepo.headlines.collect { result ->
                when (result) {
                    is Result.Loading -> {
                        _uiState.value = NewsUiState.Loading
                    }
                    is Result.Success -> {
                        if (result.value.status == Status.OK) {
                            _uiState.value = NewsUiState.News
                        } else {
                            _uiState.value = NewsUiState.Error
                        }
                    }
                    is Result.Error -> {
                        _uiState.value = NewsUiState.Error
                    }
                }
            }
        }
    }
}