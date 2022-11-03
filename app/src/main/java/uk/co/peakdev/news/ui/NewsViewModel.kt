package uk.co.peakdev.news.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uk.co.peakdev.news.data.Result
import uk.co.peakdev.news.data.api.model.Status
import uk.co.peakdev.news.data.repo.NewsRepo
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepo: NewsRepo
): ViewModel() {

    fun onViewCreated() {
        viewModelScope.launch {
            newsRepo.headlines.collect { result ->
                when (result) {
                    is Result.Loading -> {
                        // Todo - send loading state to view
                        Log.d("Blah", "blah")
                    }
                    is Result.Success -> {
                        if (result.value.status == Status.OK) {
                            // Todo - send success state to view
                            Log.d("Blah", "blah")
                        } else {
                            // Todo - send error state to view
                            Log.d("Blah", "blah")
                        }
                    }
                    is Result.Error -> {
                        // Todo - send error state to view
                        Log.d("Blah", "blah")
                    }
                }
            }
        }
    }
}