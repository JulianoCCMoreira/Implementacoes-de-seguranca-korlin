package com.julianoccm.seguraca.repositories

import com.julianoccm.seguraca.models.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Int> {
    fun findByEmail(email: String): User?
}