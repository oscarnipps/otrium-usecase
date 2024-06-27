package com.android.otrium.domain.common

sealed class ApiResult<T>(val data: T? = null, val error: AppError = AppError.NONE) {

    class Loading<T>(data: T? = null) : ApiResult<T>(data)

    class Success<T>(data: T?) : ApiResult<T>(data)

    class Error<T>(appError: AppError, data: T? = null) : ApiResult<T>(error = appError)
}