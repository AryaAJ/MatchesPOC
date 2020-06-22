package com.example.shadipoc.data.network

class ResponseResult<T> private constructor(val status: ResponseResult.Status, val data: T?, val exception: Exception?) {
    enum class Status {
        SUCCESS, ERROR, LOADING
    }
    companion object {
        fun <T> success(data: T?): ResponseResult<T> {
            return ResponseResult(Status.SUCCESS, data, null)
        }
        fun <T> error(exception: Exception?): ResponseResult<T> {
            return ResponseResult(Status.ERROR, null, exception)
        }
        fun <T> loading(data: T?): ResponseResult<T> {
            return ResponseResult(Status.LOADING, data, null)
        }
    }
}