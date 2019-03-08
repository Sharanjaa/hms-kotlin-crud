/*
* APIExceptionAdvice.kt*
* This class will handle all the rest controller exceptions
*
* sharanjaas@hsenid.virtusa.om
*/

package com.app.kotlinkcrud.service

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletResponse

@ControllerAdvice
class APIExceptionAdvice {

    //  logs class name
    private val logger = LoggerFactory.getLogger(APIExceptionAdvice::class.java)

    // Handles the Moethod argument not valid exception : 400
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleBadRequests(ex: MethodArgumentNotValidException, response: HttpServletResponse) {
        logger.error("Error occurred during the operation: Method Argument Not Valid Exception", ex)
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.message)
    }

    //Handles  all other erros and marked it as 500 (Internal Server Error)
    @ExceptionHandler(Throwable::class)
    fun handleErrorRequests(ex: Throwable, response: HttpServletResponse) {
        logger.error("Error occurred during the operation", ex)
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.message)
    }
}