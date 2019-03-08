/*
* User.kt
*
* This class contains the user details
*
* sharanjaas@hsenid.virtusa.om
*/

package com.app.kotlinkcrud.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank

@Entity
data class User(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @get: NotBlank(message = "First Name is mandatory")
        val firstname: String = "",

        @get: NotBlank(message = "Last Name is mandatory")
        val lastname: String = "",

        @get: NotBlank(message = "Email is mandatory")
        val email: String = "",

        // This is an optional field
        val contactno: Int = 0
)