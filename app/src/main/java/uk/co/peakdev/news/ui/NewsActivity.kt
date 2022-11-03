package uk.co.peakdev.news.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import uk.co.peakdev.news.ui.theme.NewsTheme

@AndroidEntryPoint
class NewsActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NewsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NewsScreen()
                }
            }
        }

        /*lifecycleScope.launch {
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
        }*/
    }

}