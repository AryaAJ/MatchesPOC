package com.example.shadipoc.di.modules

import android.app.Application
import com.example.shadipoc.repository.UserRepository
import dagger.Module
import dagger.Provides

@Module
class UserModule constructor(private val application: Application) {

    @Provides
    fun providesProductRepository(application: Application): UserRepository {
        return UserRepository(application)
    }

    @Provides
    fun provideApplication(): Application {
        return application
    }
}