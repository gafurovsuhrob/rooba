package com.seros.rooba.feature.home

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seros.rooba.core.domain.model.User
import com.seros.rooba.core.domain.repository.UserRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    var users by mutableStateOf<List<User>>(emptyList())
        private set

    private var _selectedUser by mutableStateOf<User?>(null)
    val selectedUser: User?
        get() = _selectedUser


    init {
        loadUsers()
    }

    private fun loadUsers() {
        viewModelScope.launch {
            users = userRepository.getUsers()
        }
    }

    fun setSelectedUserById(id: String) {
        viewModelScope.launch {
            _selectedUser = userRepository.getUserById(id)
        }
    }

}
