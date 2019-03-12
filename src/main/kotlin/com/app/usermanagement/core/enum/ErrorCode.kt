/*
 * (C) Copyright 2008-2019 hSenid Software International (Pvt) Limited.
 *
 *  All Rights Reserved.
 *
 *  These materials are unpublished, proprietary, confidential source code of
 *  hSenid Software International (Pvt) Limited and constitute a TRADE SECRET
 *  of hSenid Software International (Pvt) Limited.
 *
 *  hSenid Software International (Pvt) Limited retains all title to and intellectual
 *  property rights in these materials.
 */

package com.app.usermanagement.core.enum

enum class ErrorCode constructor(id: Int, msg: String) {

    DatabaseError(1001, "Database operation failed."),
    RecordAlreadyExistsException(1002, "Record already exists."),
    RecordDoesNotExists(1003, "Record does not exists"),
    Unknown(1004, "Unknown error occurred");


    val id: Int
    val msg: String

    init {
        this.id = id
        this.msg = msg
    }
}