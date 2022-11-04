package uk.co.peakdev.news.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.co.peakdev.news.ui.model.Headline
import uk.co.peakdev.news.ui.theme.NewsTheme

@Composable
fun NewsScreen(
    viewModel: NewsViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    NewsView(uiState, modifier)
}

@Composable
fun NewsView(
    uiState: NewsUiState,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        when (uiState) {
            is NewsUiState.Initial -> {
                Text(text = "Initial")
            }
            is NewsUiState.Loading -> {
                Text(text = "Loading")
            }
            is NewsUiState.Headlines -> {
                Headlines(uiState.headlines)
            }
            is NewsUiState.Error -> {
                Text(text = "Error")
            }
        }
    }
}

@Composable
fun Headlines(
    headlines: List<Headline>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        itemsIndexed(headlines) { index, headline ->
            // Todo - add padding/margin (incl divider)
            HeadlineItem(headline, index == headlines.size - 1)
        }
    }
}

@Composable
fun HeadlineItem(
    headline: Headline,
    isLast: Boolean,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        headline.author?.let {
            Text(text = it)
        }
        Text(text = headline.title)
        headline.description?.let {
            Text(text = it)
        }
        if (!isLast) {
            Divider(color = Color.Black)
        }
    }
}

@Preview (name = "Headlines")
@Composable
fun PreviewHeadlines() {
    NewsTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            NewsView(
                NewsUiState.Headlines(
                    listOf(
                        Headline(
                            author = "Author",
                            title = "Title 1",
                            description = "Description 1"
                        ),
                        Headline(
                            author = null,
                            title = "Title 2",
                            description = "Description 2"
                        ),
                    )
                )
            )
        }
    }
}

@Preview (name = "Loading")
@Composable
fun PreviewLoading() {
    NewsTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            NewsView(NewsUiState.Loading)
        }
    }
}

@Preview (name = "Error")
@Composable
fun PreviewError() {
    NewsTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            NewsView(NewsUiState.Error)
        }
    }
}