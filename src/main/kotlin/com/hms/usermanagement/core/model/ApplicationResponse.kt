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

package com.hms.usermanagement.core.model

import com.hms.usermanagement.core.enum.ResponseCode

class ApplicationResponse<T>(
        val statusCode: Int = ResponseCode.UNKNOWN.id,
        val message: String = ResponseCode.UNKNOWN.msg,
        val data: T? = null,
        val error: ApplicationResponseError? = null
) {}

class ApplicationResponseError(
        val errorCode: ResponseCode = ResponseCode.UNKNOWN,
        val errorMesage: String? = ResponseCode.UNKNOWN.msg) {
}