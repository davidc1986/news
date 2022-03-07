package uk.co.peakdev.news.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uk.co.peakdev.news.data.api.NewsApi
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsApi: NewsApi
): ViewModel() {

    fun onViewCreated() {
        viewModelScope.launch(Dispatchers.IO) {
            val headlines = newsApi.fetchHeadlines()
            Log.d("Blah", "blah")
        }
    }
}