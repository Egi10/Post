package id.buaja.data.source.remote

import id.buaja.data.di.IoDispatcher
import id.buaja.data.source.remote.network.ApiResponse
import id.buaja.data.source.remote.network.ApiService
import id.buaja.data.source.remote.response.PostResponseItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Julsapargi Nursam on 3/23/21.
 */

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend fun getPost(): Flow<ApiResponse<List<PostResponseItem>>> {
        return flow {
            try {
                val response = apiService.getPost()
                if (response.isNullOrEmpty()) {
                    emit(ApiResponse.Empty)
                } else {
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(dispatcher)
    }
}