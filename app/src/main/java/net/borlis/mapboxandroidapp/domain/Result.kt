package net.borlis.mapboxandroidapp.domain

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */

sealed class Result<out T : Any> {

    data class Success<out T : Any>(val data: T) : Result<T>() {
        override fun toString(): String = "Success[data=$data]"
        inline fun <X : Any> convert(convertSuccess: (T) -> X) = Success(convertSuccess(data))
    }

    data class Error(val exception: Throwable) : Result<Nothing>() {
        override fun toString(): String = "Error[exception=$exception]"
        inline fun convert(convertFun: (Throwable) -> Throwable) = Error(convertFun(exception))
    }

    inline fun <X : Any> convertResult(
        convertSuccess: (T) -> X,
        convertError: (Throwable) -> Throwable
    ): Result<X> = when (this) {
        is Error -> this.convert(convertError)
        is Success -> this.convert(convertSuccess)
    }

    inline fun <X : Any> convertSuccess(
        convertFun: (T) -> X
    ): Result<X> = when (this) {
        is Error -> this
        is Success -> this.convert(convertFun)
    }

    inline fun convertError(
        convertFun: (Throwable) -> Throwable
    ): Result<T> = when (this) {
        is Error -> this.convert(convertFun)
        is Success -> this
    }

    inline fun <X : Any> chainSuccess(
        convertFun: (T) -> Result<X>
    ): Result<X> = when (this) {
        is Error -> this
        is Success -> convertFun(data)
    }
}