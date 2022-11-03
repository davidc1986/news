package uk.co.peakdev.news.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uk.co.peakdev.news.R

@AndroidEntryPoint
class NewsActivity: AppCompatActivity() {

    private val viewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_launch)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is NewsUiState.Initial -> {
                            Log.d("Blah", "blah")
                        }
                        is NewsUiState.Loading -> {
                            Log.d("Blah", "blah")
                        }
                        is NewsUiState.News -> {
                            Log.d("Blah", "blah")
                        }
                        is NewsUiState.Error -> {
                            Log.d("Blah", "blah")
                        }
                    }
                }
            }
        }
    }

}