package id.buaja.data.utils

import id.buaja.data.source.remote.network.ApiResponse
import id.buaja.domain.Resource
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType> {

    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)) {
            emit(Resource.Loading())
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    showDataFromDb()
                }
                is ApiResponse.Empty -> {
                    showDataFromDb()
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(Resource.Error<ResultType>(apiResponse.errorMessage))

                    showDataFromDb()
                }
            }
        } else {
            showDataFromDb()
        }
    }

    private suspend fun FlowCollector<Resource<ResultType>>.showDataFromDb() {
        emitAll(loadFromDB().map { Resource.Success(it) })
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<Resource<ResultType>> = result
}