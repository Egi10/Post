package id.buaja.data.source.remote.network

/**
 * Created by Julsapargi Nursam on 3/23/21.
 */

sealed class ApiResponse<out R> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val errorMessage: String) : ApiResponse<Nothing>()
    object Empty : ApiResponse<Nothing>()
}
