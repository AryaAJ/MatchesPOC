package com.example.shadipoc.di.modules

import com.example.shadipoc.api.UserApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class APIModule {

    @Provides
    fun providesAPI(retrofit: Retrofit): UserApi{
        return retrofit.create(UserApi::class.java)
    }
}