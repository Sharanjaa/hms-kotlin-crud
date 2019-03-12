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

package com.app.usermanagement.core.service

import com.app.usermanagement.core.enum.ResponseCode
import com.app.usermanagement.core.model.ApplicationResponse
import com.app.usermanagement.core.model.ApplicationResponseError
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import javax.servlet.http.HttpServletResponse

@ControllerAdvice
class APIExceptionController {

    private val logger = LoggerFactory.getLogger(APIExceptionController::class.java)

    fun getResponseEntity(exceptionDetail: ResponseCode): ApplicationResponse<Nothing> {

        val errorResponse = ApplicationResponse(
                exceptionDetail.id,
                exceptionDetail.msg,
                null
        )

        return errorResponse
    }

    fun getResponseEntity(cause: Throwable, exceptionDetail: ResponseCode): ApplicationResponse<Nothing> {
        val applicationResponseError = ApplicationResponseError(exceptionDetail.id,
                cause.message.toString())

        val errorResponse = ApplicationResponse(
                exceptionDetail.id,
                exceptionDetail.msg,
                null,
                applicationResponseError
        )

        return errorResponse
    }

    @ExceptionHandler(ServiceException::class)
    fun handleBadRequests(ex: ServiceException, response: HttpServletResponse):
            ResponseEntity<ApplicationResponse<Nothing>> {

        if (ex.code == ResponseCode.USER_DOES_NOT_EXISTS)
            return ResponseEntity(getResponseEntity(ResponseCode.USER_DOES_NOT_EXISTS), HttpStatus.BAD_REQUEST)
        if (ex.code == ResponseCode.USER_ALREADY_EXISTS) {
            return ResponseEntity(getResponseEntity(ResponseCode.USER_ALREADY_EXISTS), HttpStatus.BAD_REQUEST)
        } else {
            return ResponseEntity(getResponseEntity(ResponseCode.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR)
        }

    }

    @ExceptionHandler(MissingServletRequestParameterException::class)
    fun handleSevltesRequestRequests(ex: MissingServletRequestParameterException, response: HttpServletResponse):
            ResponseEntity<ApplicationResponse<Nothing>> {

        return ResponseEntity(getResponseEntity(ex, ResponseCode.MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION),
                HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleBadRequests(ex: MethodArgumentNotValidException, response: HttpServletResponse):
            ResponseEntity<ApplicationResponse<Nothing>> {

        return ResponseEntity(getResponseEntity(ex, ResponseCode.METHOD_ARGUMENT_NOT_VALID_EXCEPTION),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleArgumentMismatchRequests(ex: MethodArgumentTypeMismatchException, response: HttpServletResponse):
            ResponseEntity<ApplicationResponse<Nothing>> {

        return ResponseEntity(getResponseEntity(ex, ResponseCode.METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTION),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleUnreadableRequests(ex: HttpMessageNotReadableException, response: HttpServletResponse):
            ResponseEntity<ApplicationResponse<Nothing>> {

        return ResponseEntity(getResponseEntity(ex, ResponseCode.METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTION),
                HttpStatus.BAD_REQUEST);
    }

    //Handles  all other erros and marked it as 500 (Internal Server Error)
    @ExceptionHandler(Throwable::class)
    fun handleErrorRequests(ex: Throwable, response: HttpServletResponse):
            ResponseEntity<ApplicationResponse<Nothing>> {

        return ResponseEntity(getResponseEntity(ex, ResponseCode.INTERNAL_SERVER_ERROR),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}