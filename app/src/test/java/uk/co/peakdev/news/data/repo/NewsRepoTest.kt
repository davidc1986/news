package uk.co.peakdev.news.data.repo

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import uk.co.peakdev.news.data.Result
import uk.co.peakdev.news.data.api.NewsApi
import uk.co.peakdev.news.data.api.model.NewsResponse

@RunWith(MockitoJUnitRunner::class)
class NewsRepoTest {

    @Mock
    private lateinit var newsApi: NewsApi

    @Mock
    private lateinit var response: Response<NewsResponse>

    @Test
    fun `Given the api returns an error, When the headlines are fetched, Then return error result`() = runTest {
        Mockito.`when`(newsApi.fetchNews()).thenReturn(response)

        Mockito.`when`(response.isSuccessful).thenReturn(false)

        val systemUnderTest = NewsRepo(newsApi)
        val result = systemUnderTest.fetchHeadlines()

        assertEquals(Result.Error<NewsResponse>().javaClass, result.javaClass)
    }

    @Test
    fun `Given the api returns a response, When the headlines are fetched, Then return a news response result`() = runTest {
        Mockito.`when`(newsApi.fetchNews()).thenReturn(response)

        Mockito.`when`(response.isSuccessful).thenReturn(true)
        Mockito.`when`(response.body()).thenReturn(newsResponse)

        val systemUnderTest = NewsRepo(newsApi)
        val result = systemUnderTest.fetchHeadlines()
        val expected = Result.Success(newsResponse)

        assertEquals(expected, result)
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
}