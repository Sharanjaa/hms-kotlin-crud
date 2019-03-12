package com.app.usermanagement.core.service

import com.app.usermanagement.core.model.User
import java.util.*

interface UserService {
    fun findAll(): List<User>
    fun findById(id: Long): Optional<User>
    fun findByFirstName(firstName: String): List<User>
    fun add(user: User): User
    fun update(user: User): User
    fun deleteById(id: Long)
}