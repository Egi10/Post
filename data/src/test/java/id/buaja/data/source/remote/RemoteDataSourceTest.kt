package id.buaja.data.source.remote

import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import id.buaja.data.source.remote.network.ApiResponse
import id.buaja.data.source.remote.network.ApiService
import id.buaja.data.source.remote.response.PostResponseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.amshove.kluent.shouldBeEqualTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

/**
 * Created by Julsapargi Nursam on 3/24/21.
 */

class RemoteDataSourceTest {
    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    private val post = ApiResponse.Success(
        listOf(
            PostResponseItem(
                0, "title", "body", 0
            )
        )
    )

    private val error = ApiResponse.Error(errorMessage = "null")

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should get post success`() = runBlockingTest {
        // GIVEN
        val apiService = mock<ApiService> {
            onBlocking { getPost() } doReturn listOf(
                PostResponseItem(
                    0, "title", "body", 0
                )
            )
        }

        val remoteDataSource = RemoteDataSource(apiService, testDispatcher)

        // WHEN
        val getPost = remoteDataSource.getPost()

        // THEN
        getPost.collect {
            it shouldBeEqualTo post
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should get post failed`() = runBlockingTest {
        // GIVEN
        val apiService = mock<ApiService> {
            onBlocking { getPost() } doAnswer {
                throw IOException()
            }
        }

        val remoteDataSource = RemoteDataSource(apiService, testDispatcher)

        // WHEN
        val getPost = remoteDataSource.getPost()

        // THEN
        getPost.collect {
            it shouldBeEqualTo error
        }
    }
}