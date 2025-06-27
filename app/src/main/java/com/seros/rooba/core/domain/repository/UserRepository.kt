package com.seros.rooba.core.domain.repository

import com.seros.rooba.core.domain.model.User

interface UserRepository {
    suspend fun getUsers(): List<User>
    suspend fun getUserById(id: String): User?
}
