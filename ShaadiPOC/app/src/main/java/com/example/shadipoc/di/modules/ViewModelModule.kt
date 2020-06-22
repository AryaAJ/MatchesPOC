package com.example.shadipoc.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shadipoc.viewmodel.UserInvitationViewModel
import com.example.shadipoc.viewmodel.UserInvitationViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindsViewModelFactory(factoryMain: UserInvitationViewModelFactory): ViewModelProvider.Factory

    @Binds
    internal abstract fun bindsMainViewModel(viewModel: UserInvitationViewModel): ViewModel
}