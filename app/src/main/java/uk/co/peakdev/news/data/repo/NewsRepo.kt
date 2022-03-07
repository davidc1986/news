package uk.co.peakdev.news.data.repo

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import uk.co.peakdev.news.data.api.NewsApi
import uk.co.peakdev.news.data.api.model.HeadlinesResponse
import uk.co.peakdev.news.di.IoDispatcher
import javax.inject.Inject

class NewsRepo @Inject constructor(
    private val newsApi: NewsApi,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun fetchHeadlines(): Response<HeadlinesResponse> {
        return withContext(dispatcher) {
            newsApi.fetchHeadlines()
        }
    }

}