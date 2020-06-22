package com.example.shadipoc.di

import com.example.shadipoc.DaggerApp
import com.example.shadipoc.di.modules.*
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Component(modules = [AndroidInjectionModule::class, ActivityModule::class, FragmentModule::class, AppModule::class, NetworkModule::class, ViewModelModule::class])
interface AppComponent : AndroidInjector<DaggerApp> {
}