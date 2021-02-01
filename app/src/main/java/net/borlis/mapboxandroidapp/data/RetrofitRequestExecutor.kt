package net.borlis.mapboxandroidapp.data

import net.borlis.mapboxandroidapp.network.ApiClient
import retrofit2.Response
import net.borlis.mapboxandroidapp.domain.Result
import net.borlis.mapboxandroidapp.network.data.asHttpErrorResult
import net.borlis.mapboxandroidapp.network.data.bodyAsResult

interface RetrofitRequestExecutor {
    suspend fun <T : Any> execute(
        request: suspend (ApiClient) -> Response<T>
    ): Result<T>
}

class RetrofitRequestExecutorImpl(
    private val api: ApiClient
) : RetrofitRequestExecutor {
    override suspend fun <T : Any> execute(request: suspend (ApiClient) -> Response<T>): Result<T> {
        val response = request(api)
        return when {
            response.isSuccessful -> {
                response.bodyAsResult()
            }
            else -> {
                return response.asHttpErrorResult()
            }
        }
    }

}