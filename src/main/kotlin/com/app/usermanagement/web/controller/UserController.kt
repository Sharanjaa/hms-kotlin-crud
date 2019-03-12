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

package com.app.usermanagement.web.controller

import com.app.usermanagement.core.model.User
import com.app.usermanagement.core.service.UserServiceImpl
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import javax.validation.Valid


@Controller
class UserController(private val userService: UserServiceImpl) {

    @Value("\${api-url}")
    private val apiUrl: String? = null

    fun getUsers(): List<User>? {
        val (request, response, result) = "$apiUrl/api/users"
                .httpGet().responseString()

        if (response.statusCode == HttpStatus.OK.value()) {
            val parser: Parser = Parser()
            val stringBuilder: StringBuilder = StringBuilder(result.get().toString())
            val json: JsonObject = parser.parse(stringBuilder) as JsonObject
            return json.array<User>("data")
        } else {
            return null
        }
    }


    @GetMapping("/")
    fun showUsers(model: Model): String {
        model.addAttribute("users", getUsers())
        return "index"
    }


    @GetMapping("/search")
    fun showUpdate(@RequestParam("firstname") firstname: String, model: Model): String {

        val (request, res, result)
                = "$apiUrl/api/users/search".httpGet(listOf("name" to firstname)).responseString()

        if (res.statusCode == HttpStatus.OK.value()) {

            val parser: Parser = Parser()
            val stringBuilder: StringBuilder = StringBuilder(result.get().toString())
            val json: JsonObject = parser.parse(stringBuilder) as JsonObject
            model.addAttribute("users", json.array<User>("data"))
            return "index"

        } else {
            return "add-user"
        }
    }

    @GetMapping("/add")
    fun showAddUserForm(user: User): String {
        return "add-user"
    }

    @PostMapping("/adduser")
    fun addUser(@Valid user: User, result: BindingResult, model: Model): String {
        if (result.hasErrors()) {
            return "add-user"
        }
        val bodyJson = Gson().toJson(user)
        val (request, res, result)
                = "$apiUrl/api/users".httpPost().header("Content-Type" to "application/json").body(bodyJson.toString()).response()

        if (res.statusCode == HttpStatus.CREATED.value()) {
            model.addAttribute("users", getUsers())
            return "index"
        } else {
            return "add-user"
        }
    }

    @GetMapping("/edit/{id}", "/view/{id}")
    fun showUpdateForm(@PathVariable("id") id: Long, model: Model): String {
        val user = userService.findById(id)
                .orElseThrow({ IllegalArgumentException("Invalid user Id:$id") })

        model.addAttribute("user", user)
        return "update-user"

    }

    @PostMapping("/update/{id}")
    fun updateUser(@PathVariable("id") id: Long, @Valid user: User,
                   result: BindingResult, model: Model): String {

        user.id = id
        val bodyJson = Gson().toJson(user)
        val (request, res, result)
                = "$apiUrl/api/users".httpPut().
                header("Content-Type" to "application/json").body(bodyJson.toString()).response()

        if (res.statusCode == HttpStatus.OK.value()) {
            model.addAttribute("users", getUsers())
            return "index"
        } else {
            return "update-user"
        }
    }

    @GetMapping("/delete/{id}")
    fun deleteUser(@PathVariable("id") id: Long, model: Model): String {
        val (request, res, result)
                = "$apiUrl/api/users/$id".httpDelete().responseString()

        if (res.statusCode == HttpStatus.NO_CONTENT.value()) {
            model.addAttribute("users", getUsers())
            return "index"

        } else {
            return "add-user"
        }
    }

}