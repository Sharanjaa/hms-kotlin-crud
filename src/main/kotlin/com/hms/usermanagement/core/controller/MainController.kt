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

package com.hms.usermanagement.core.controller

import com.hms.usermanagement.core.enum.ResponseCode
import com.hms.usermanagement.core.model.ApplicationResponse
import com.hms.usermanagement.core.model.User
import com.hms.usermanagement.core.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class MainController {

    @Autowired
    lateinit var userService: UserService

    private val logger = LoggerFactory.getLogger(MainController::class.java)

    @GetMapping("/users")
    fun getAllUsers():
            ResponseEntity<ApplicationResponse<List<User>>> {

        return withTryCatch({ userService.findAll() }, ResponseCode.OK)
                as ResponseEntity<ApplicationResponse<List<User>>>

    }

    @GetMapping("/users/{id}")
    fun getUsersById(@PathVariable id: Long):
            ResponseEntity<ApplicationResponse<Optional<User>>> {

            return withTryCatch({ userService.findById(id) }, ResponseCode.OK)
                as ResponseEntity<ApplicationResponse<Optional<User>>>

    }

    @GetMapping("/users/search")
    fun searchUsersByName(@RequestParam name: String):
            ResponseEntity<ApplicationResponse<List<User>>> {

        return withTryCatch({ userService.findByFirstName(name) }, ResponseCode.OK)
                as ResponseEntity<ApplicationResponse<List<User>>>

    }

    @PostMapping("/users")
    fun addUser(@Valid @RequestBody user: User):
            ResponseEntity<ApplicationResponse<User>> {

        return withTryCatch({ userService.add(user) }, ResponseCode.CREATED)
                as ResponseEntity<ApplicationResponse<User>>

    }

    @PutMapping("/users")
    fun updateUser(@Valid @RequestBody user: User):
            ResponseEntity<ApplicationResponse<User>>? {

        return withTryCatch({ userService.update(user) }, ResponseCode.OK)
                as ResponseEntity<ApplicationResponse<User>>

    }

    @DeleteMapping("/users/{id}")
    fun deleteUser(@PathVariable id: Long):
            ResponseEntity<ApplicationResponse<Nothing>> {

         return withTryCatch({userService.deleteById(id)} , ResponseCode.NO_CONTENT)
                 as ResponseEntity<ApplicationResponse<Nothing>>
    }


    fun withTryCatch(method : () -> Any, code: ResponseCode): Any {

        try {

            logger.info("Method Started : ${javaClass.name}")
            var result = method()
            var response = ApplicationResponse(code.id, code.msg, result)
            return ResponseEntity(response, HttpStatus.valueOf(code.id))

        } catch (e: Exception) {

            logger.error("Error Occurred : ${javaClass.name}", e)
            throw e

        }

    }
}