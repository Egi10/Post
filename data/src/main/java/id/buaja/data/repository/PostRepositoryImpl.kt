package id.buaja.data.repository

import id.buaja.data.source.local.LocalDataSource
import id.buaja.data.source.remote.RemoteDataSource
import id.buaja.data.source.remote.response.PostResponseItem
import id.buaja.data.utils.DataMapper
import id.buaja.data.utils.NetworkBoundResource
import id.buaja.domain.Resource
import id.buaja.domain.model.Post
import id.buaja.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Julsapargi Nursam on 3/23/21.
 */

@Singleton
class PostRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : PostRepository {
    override fun getPost(): Flow<Resource<List<Post>>> =
        object : NetworkBoundResource<List<Post>, List<PostResponseItem>>() {
            override fun loadFromDB(): Flow<List<Post>> {
                return localDataSource.getAllPost().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Post>?) = true

            override suspend fun createCall() = remoteDataSource.getPost()

            override suspend fun saveCallResult(data: List<PostResponseItem>) {
                val postList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertPost(postList)
            }
        }.asFlow()

    override fun getPostByTitle(title: String): Flow<List<Post>> =
        localDataSource.getPostByTitle(title).map {
            DataMapper.mapEntitiesToDomain(it)
        }
}