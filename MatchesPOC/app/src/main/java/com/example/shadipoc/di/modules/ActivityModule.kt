package com.example.shadipoc.di.modules

import com.example.shadipoc.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contirbutesActivityInjector(): MainActivity
}