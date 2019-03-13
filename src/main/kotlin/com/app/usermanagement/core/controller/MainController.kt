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

package com.app.usermanagement.core.controller

import com.app.usermanagement.core.enum.ResponseCode
import com.app.usermanagement.core.model.ApplicationResponse
import com.app.usermanagement.core.model.User
import com.app.usermanagement.core.service.UserServiceImpl
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class MainController(private val userService: UserServiceImpl) {

    private val logger = LoggerFactory.getLogger(MainController::class.java)

    @GetMapping("/users")
    fun getAllUsers():
            ResponseEntity<ApplicationResponse<List<User>>> {

        try {
            val applicationResponse = ApplicationResponse(
                    ResponseCode.OK.id,
                    ResponseCode.OK.msg,
                    userService.findAll())
            return ResponseEntity(applicationResponse, HttpStatus.OK)

        } catch (ex: Exception) {
            logger.error("Error occurred: ", ex.message)
            throw ex
        }

    }

    @GetMapping("/users/{id}")
    fun getUsersById(@PathVariable id: Long):
            ResponseEntity<ApplicationResponse<Optional<User>>> {

        try {
            val applicationResponse =
                    ApplicationResponse(
                    ResponseCode.OK.id,
                    ResponseCode.OK.msg,
                    userService.findById(id))
            return ResponseEntity(applicationResponse, HttpStatus.OK)

        } catch (ex: Exception) {
            logger.error("Error occurred: ", ex.message)
            throw ex
        }

    }

    @GetMapping("/users/search")
    fun searchUsersByName(@RequestParam name: String):
            ResponseEntity<ApplicationResponse<List<User>>> {

        try {

            val applicationResponse = ApplicationResponse(
                    ResponseCode.OK.id,
                    ResponseCode.OK.msg,
                    userService.findByFirstName(name))
            return ResponseEntity(applicationResponse, HttpStatus.OK)

        } catch (ex: Exception) {
            logger.error("Error occurred: ", ex.message)
            throw ex
        }
    }

    @PostMapping("/users")
    fun addUser(@Valid @RequestBody user: User):
            ResponseEntity<ApplicationResponse<User>> {

        try {
            val applicationResponse = ApplicationResponse(
                    ResponseCode.CREATED.id,
                    ResponseCode.CREATED.msg,
                    userService.add(user))
            return ResponseEntity(applicationResponse, HttpStatus.CREATED)

        } catch (ex: Exception) {
            logger.error("Error occurred: ", ex.message)
            throw ex
        }
    }

    @PutMapping("/users")
    fun updateUser(@Valid @RequestBody user: User):
            ResponseEntity<ApplicationResponse<User>>? {
        try {
            val applicationResponse = ApplicationResponse(
                    ResponseCode.OK.id,
                    ResponseCode.OK.msg,
                    userService.update(user))
            return ResponseEntity(applicationResponse, HttpStatus.OK)

        } catch (ex: Exception) {
            logger.error("Error occurred: ", ex.message)
            throw ex
        }
    }

    @DeleteMapping("/users/{id}")
    fun deleteUser(@PathVariable id: Long):
            ResponseEntity<ApplicationResponse<Nothing>> {

        try {
            userService.deleteById(id)
            val applicationResponse = ApplicationResponse(
                    ResponseCode.NO_CONTENT.id,
                    ResponseCode.NO_CONTENT.msg,
                    null)
            return ResponseEntity(applicationResponse, HttpStatus.OK)

        } catch (ex: Exception) {
            logger.error("Error occurred: ", ex.message)
            throw ex
        }
    }
}