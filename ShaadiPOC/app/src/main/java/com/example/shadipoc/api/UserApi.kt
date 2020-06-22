package com.example.shadipoc.api

import com.example.shadipoc.data.MainData
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET

interface UserApi {

    var x: String

    @GET("?results=10")
    fun getUserList(): Single<MainData>

    fun withBody(): String {
        return "I am Body"
    }
}