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

package com.app.kotlinkcrud.repository

import com.app.kotlinkcrud.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {

    // Finds by user first name and it ignores case
    fun findByFirstnameContainingIgnoreCase(firstname: String): List<User>
}