/*
* UserRepository.kt
*
*V1
*
* sharanjaas@hsenid.virtusa.om
*/
package com.app.kotlinkcrud.repository

import com.app.kotlinkcrud.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {

    // Finds by user first name and it ignores case
    fun findByFirstnameContainingIgnoreCase(firstname: String): List<User>
}