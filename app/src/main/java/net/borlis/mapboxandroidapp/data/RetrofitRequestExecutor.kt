package net.borlis.mapboxandroidapp.data

import net.borlis.mapboxandroidapp.network.ApiClient
import retrofit2.Response
import net.borlis.mapboxandroidapp.domain.Result

interface RetrofitRequestExecutor {
    suspend fun <T : Any> execute(
        request: suspend (ApiClient) -> Response<T>
    ): Result<T>
}