package net.borlis.mapboxandroidapp.network.data

import net.borlis.mapboxandroidapp.domain.Result
import retrofit2.Response

internal fun <T : Any> Response<T>.bodyAsResult() = when (val body: T? = body()) {
    null -> Result.Error(IllegalStateException("body is null: $this"))
    else -> Result.Success(body)
}
internal fun Response<*>.asHttpErrorResult() = Result.Error(getHttpError())

internal fun Response<*>.getHttpError(): HttpError = when {
    isSuccessful -> throw IllegalStateException()
    else -> HttpError(code())
}