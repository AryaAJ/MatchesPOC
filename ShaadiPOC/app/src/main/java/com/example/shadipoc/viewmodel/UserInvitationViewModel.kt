package com.example.shadipoc.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.shadipoc.data.room.UserData
import com.example.shadipoc.repository.UserRepository
import javax.inject.Inject

class UserInvitationViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    init {
        userRepository.fetchOnlineData()
    }

    fun getAllUsers(): LiveData<List<UserData>> {
        return userRepository.getAllUsers()
    }

    fun insertUser(user: UserData) {
        userRepository.insertUser(user)
    }

    fun updateUser(user: UserData) {
        userRepository.updateUser(user)
    }

    fun insertUsers(user: List<UserData>) {
        userRepository.insertUsers(user)
    }
}
