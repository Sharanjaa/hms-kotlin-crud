/*
 * (C) Copyright 2008-2019 hSenid Software International (Pvt) Limited.
 *
 * All Rights Reserved.
 *
 * These materials are unpublished, proprietary, confidential source code of
 * hSenid Software International (Pvt) Limited and constitute a TRADE SECRET
 * of hSenid Software International (Pvt) Limited.
 *
 * hSenid Software International (Pvt) Limited retains all title to and intellectual
 * property rights in these materials.
 *
 */

package com.app.usermanagement.core.model

//import javax.persistence.Entity
//import javax.persistence.GeneratedValue
//import javax.persistence.GenerationType
//import javax.persistence.Id
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.constraints.NotBlank

//@Entity
@Document
data class User(

        @Id
//        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        @get: NotBlank(message = "First Name is mandatory")
        val firstname: String = "",

        @get: NotBlank(message = "Last Name is mandatory")
        val lastname: String = "",

        @get: NotBlank(message = "Email is mandatory")
        val email: String = "",

        // This is an optional field
        val contactno: Int = 0
) {
    @Transient
    @JsonIgnore
    val seqname: String = "users_sequence"
}