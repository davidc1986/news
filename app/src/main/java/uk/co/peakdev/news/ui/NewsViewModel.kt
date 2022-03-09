package uk.co.peakdev.news.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uk.co.peakdev.news.data.repo.NewsRepo
import uk.co.peakdev.news.domain.NewsResult
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepo: NewsRepo
): ViewModel() {

    fun onViewCreated() {
        viewModelScope.launch {
            when (newsRepo.fetchHeadlines()) {
                is NewsResult.Success -> {
                    Log.d("Blah", "blah")
                }
                else -> {
                    Log.d("Blah", "blah")
                }
            }
        }
    }
}