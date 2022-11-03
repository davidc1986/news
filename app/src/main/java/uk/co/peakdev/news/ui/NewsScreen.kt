package uk.co.peakdev.news.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.co.peakdev.news.ui.model.Headline
import uk.co.peakdev.news.ui.theme.NewsTheme

@Composable
fun NewsScreen(
    viewModel: NewsViewModel = viewModel(),
    modifier: Modifier = Modifier // Todo - required???
) {
    val uiState by viewModel.uiState.collectAsState()
    NewsView(uiState)
}

@Composable
fun NewsView(
    uiState: NewsUiState,
    modifier: Modifier = Modifier // Todo - required???
) {
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

@Composable
fun Headlines(
    headlines: List<Headline>,
    modifier: Modifier = Modifier // Todo - required???
) {
    LazyColumn(modifier) {
        items(headlines) { headline ->
            HeadlineItem(headline)
        }
    }
}

@Composable
fun HeadlineItem(
    headline: Headline,
    modifier: Modifier = Modifier // Todo - required???
) {
    Column(
        modifier = modifier
    ) {
        headline.author?.let {
            Text(text = it)
        }
        Text(text = headline.title)
        headline.description?.let {
            Text(text = it)
        }
    }
}

@Preview
@Composable
fun PreviewHeadlines() {
    NewsTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Headlines(
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
        }
    }
}