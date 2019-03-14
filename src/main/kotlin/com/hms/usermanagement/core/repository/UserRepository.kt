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

package com.hms.usermanagement.core.repository

import com.hms.usermanagement.core.model.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface  UserRepository : MongoRepository<User, Long> {
    // Finds by user first name and it ignores case
    fun findByFirstnameContainingIgnoreCase(firstname: String): List<User>

    fun findByFirstnameIgnoreCase(firstname: String): List<User>
}



