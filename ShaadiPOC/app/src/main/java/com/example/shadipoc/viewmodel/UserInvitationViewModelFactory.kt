package com.example.shadipoc.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class UserInvitationViewModelFactory @Inject constructor(private val mMyViewModelProvider: Provider<UserInvitationViewModel>) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel: ViewModel
        if (modelClass == UserInvitationViewModel::class.java) {
            viewModel = mMyViewModelProvider.get()
        } else {
            throw RuntimeException("unsupported view model class: $modelClass")
        }

        return viewModel as T
    }
}