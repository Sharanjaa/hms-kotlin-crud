package com.app.kotlinkcrud.controller

import com.app.kotlinkcrud.model.User
import com.app.kotlinkcrud.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class MainController(private val userRepository: UserRepository) {
    private val logger = LoggerFactory.getLogger(MainController::class.java)
    @GetMapping("/users")
    fun getAllUsers(): List<User> =
            userRepository.findAll()

    @PostMapping("/users")
    fun createNewUser(@Valid @RequestBody user: User): User? {
        logger.info("--Initiated--");
        return userRepository.save(user)
    }
}