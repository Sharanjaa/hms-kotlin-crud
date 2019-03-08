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

package com.app.kotlinkcrud.controller

import com.app.kotlinkcrud.model.User
import com.app.kotlinkcrud.repository.UserRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import javax.validation.Valid


@Controller
class UserController(private val userRepository: UserRepository) {

    //It redirects to index.html and shows all user details
    @GetMapping("/")
    fun showUsers(model: Model): String {
        model.addAttribute("users", userRepository.findAll())
        return "index"
    }

    //It finds the user byt thier firstname
    @GetMapping("/search")
    fun showUpdate(@RequestParam("firstname") firstname: String, model: Model): String {
        val user = userRepository.findByFirstnameContainingIgnoreCase(firstname)
        //Adds all identifies user to User object
        model.addAttribute("users", user)
        return "index"
    }

    //It redirects user to add-user.html
    @GetMapping("/add")
    fun showAddUserForm(user: User): String {
        return "add-user"
    }

    //Uses to add user to the db
    @PostMapping("/adduser")
    fun addUser(@Valid user: User, result: BindingResult, model: Model): String {
        //It will redirect to user form if there is error in request data
        if (result.hasErrors()) {
            return "add-user"
        }
    //It will save the user to data base
        userRepository.save(user)
        model.addAttribute("users", userRepository.findAll())
        return "index"
    }

    //It helps to edit the selected user
    @GetMapping("/edit/{id}", "/view/{id}")
    fun showUpdateForm(@PathVariable("id") id: Long, model: Model): String {
        val user = userRepository.findById(id)
                .orElseThrow({ IllegalArgumentException("Invalid user Id:$id") })

        model.addAttribute("user", user)

        return "update-user";
    }

    @PostMapping("/update/{id}")
    fun updateUser(@PathVariable("id") id: Long, @Valid user: User,
                   result: BindingResult, model: Model): String {
        if (result.hasErrors()) {
            return "update-user"
        }

        userRepository.save(user)
        model.addAttribute("users", userRepository.findAll())
        return "index"
    }

    @GetMapping("/delete/{id}")
    fun deleteUser(@PathVariable("id") id: Long, model: Model): String {
        val user = userRepository.findById(id)
                .orElseThrow({ IllegalArgumentException("Invalid user Id:$id") })
        userRepository.delete(user)
        model.addAttribute("users", userRepository.findAll())
        return "index"
    }

}