package uk.co.peakdev.news.ui

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import uk.co.peakdev.news.data.Result
import uk.co.peakdev.news.data.api.model.NewsResponse
import uk.co.peakdev.news.data.repo.NewsRepo
import uk.co.peakdev.news.ui.model.Headline

@RunWith(MockitoJUnitRunner::class)
class NewsViewModelTest {

    private val dispatcher = StandardTestDispatcher()

    @Mock
    private lateinit var newsRepo: NewsRepo

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @Test
    fun `Given the repo returns an error, When the view model is initialised, Then emit error ui state`() = runTest {
        Mockito.`when`(newsRepo.fetchHeadlines()).thenReturn(Result.Error())

        val systemUnderTest = NewsViewModel(newsRepo)

        advanceUntilIdle()

        assertEquals(NewsUiState.Error, systemUnderTest.uiState.value)
    }

    @Test
    fun `Given the repo returns headlines, When the view model is initialised, Then emit headlines ui state`() = runTest {
        Mockito.`when`(newsRepo.fetchHeadlines()).thenReturn(Result.Success(newsResponse))

        val systemUnderTest = NewsViewModel(newsRepo)

        advanceUntilIdle()

        assertEquals(headlines, systemUnderTest.uiState.value)
    }

    private val newsResponse = NewsResponse(
        listOf(
            NewsResponse.Article(
                author = "Author 1",
                title = "Title 1",
                description = "Description 1",
                url = "Url 1",
                source = "Source 1",
                image = "Image 1",
                publishedAt = "Published At 1"
            ),
            NewsResponse.Article(
                author = "Author 2",
                title = "Title 2",
                description = "Description 2",
                url = "Url 2",
                source = "Source 2",
                image = "Image 2",
                publishedAt = "Published At 2"
            )
        )
    )

    private val headlines = NewsUiState.Headlines(
        listOf(
            Headline(
                source = "Source 1",
                date = "Published At 1",
                title = "Title 1",
                description = "Description 1",
                author = "Author 1"
            ),
            Headline(
                source = "Source 2",
                date = "Published At 2",
                title = "Title 2",
                description = "Description 2",
                author = "Author 2"
            ),
        )
    )

}