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
 */

package com.hms.usermanagement.core.service

import com.hms.usermanagement.core.model.User
import java.util.*

interface UserService {
    fun findAll(): List<User>
    fun findById(id: Long): Optional<User>
    fun findByFirstName(firstName: String): List<User>
    fun add(user: User): User
    fun update(user: User): User
    fun deleteById(id: Long)
}