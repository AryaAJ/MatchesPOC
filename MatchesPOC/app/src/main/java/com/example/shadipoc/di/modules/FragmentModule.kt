package com.example.shadipoc.di.modules

import com.example.shadipoc.ui.user.UserInvitationFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contirbutesFragmentInjector(): UserInvitationFragment
}