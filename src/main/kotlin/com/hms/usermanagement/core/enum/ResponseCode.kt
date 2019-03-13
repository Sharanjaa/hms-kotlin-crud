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

package com.hms.usermanagement.core.enum

enum class ResponseCode constructor(id: Int, msg: String) {

    OK(200, "User Details Added Successfully"),
    CREATED(201, "User Created Successfully"),
    NO_CONTENT(204, "User Deleted Successfully"),
    USER_ALREADY_EXISTS(400, "There is already user with this first name"),
    METHOD_ARGUMENT_NOT_VALID_EXCEPTION(400, "'Field' is missing. Please try again"),
    MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION(400, "Required String parameter " +
            "'field' is not present"),
    METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTION(400, "'Filed' should be of type '" +
            "Correct Filed'"),
    USER_DOES_NOT_EXISTS(404, "User is not found in the system"),
    REQUEST_URL_FOUND_EXCEPTION(404, "The URL you have reached is not " +
            "in service at this time"),
    HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION(405, "The request method " +
            "does not support"),
    REQUEST_TIMEOUT(408, "The request took longer than the server was prepared to wait."),
    CONFLICT(409, "The request could not be completed because of a conflict."),
    UNSUPPORTED_MEDIA_TYPE	(415	,"The request entity has a media type which the " +
            "server/resource does not support."),
    INTERNAL_SERVER_ERROR(500, "The request was not completed. The server met an " +
            "unexpected condition"),
    DATABASE_ERROR(500, "Database operation failed"),
    NOT_IMPLEMENTED(501, "The request was not completed. The server did not " +
            "support the functionality required."),
    BAD_GATEWAY(502, "The request was not completed. The server received an " +
            "invalid response from the upstream server."),
    SERVICE_UNAVAILABLE(503, "The request was not completed. The server is " +
            "temporarily overloading or down."),
    GATEWAY_TIMEOUT(504, "The gateway has timed out"),
    UNKNOWN(0, "Unknown error occurred");

    val id: Int
    val msg: String

    init {
        this.id = id
        this.msg = msg
    }
}

