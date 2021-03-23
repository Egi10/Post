package id.buaja.data.source.remote.network

import id.buaja.data.source.remote.response.PostResponseItem
import retrofit2.http.GET

/**
 * Created by Julsapargi Nursam on 3/23/21.
 */

interface ApiService {
    @GET("posts")
    suspend fun getPost(): List<PostResponseItem>
}