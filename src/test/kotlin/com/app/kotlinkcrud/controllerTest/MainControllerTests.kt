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

package com.app.kotlinkcrud.controllerTest

import com.app.kotlinkcrud.controller.MainController
import com.app.kotlinkcrud.model.User
import com.app.kotlinkcrud.repository.UserRepository
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForObject
import org.springframework.http.MediaType
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class MainControllerTests(@Autowired private val restTemplate: TestRestTemplate) {

    lateinit var mvc: MockMvc

    @InjectMocks
    lateinit var controller: MainController

    @Mock
    lateinit var respository: UserRepository

    @Test
    fun findAll() {
        val content = """[{"id":1,"firstname":"Test","lastname":"Test","email":"sharusenthu@gmail.com","contactno":24236},{"id":2,"firstname":"sharu","lastname":"s","email":"cb","contactno":0},{"id":3,"firstname":"Test1","lastname":"Test1","email":"ssharu@gmail.com","contactno":1234},{"id":4,"firstname":"Test2","lastname":"Test","email":"sharusenthu@gmail.com","contactno":24236},{"id":5,"firstname":"string","lastname":"string","email":"string","contactno":0}]"""
        val response = restTemplate.getForObject<String>("/api/users")
        assertThat(content).isEqualTo(response)
    }


    @Test
    fun createUser() {
        mvc = MockMvcBuilders.standaloneSetup(controller).setMessageConverters(MappingJackson2HttpMessageConverter()).build()
        var user = User(1, "Test", "Test Content", "test", 1324)
        var jsonData = jacksonObjectMapper().writeValueAsString(user)
        mvc.perform(MockMvcRequestBuilders.post("/api/users/").contentType(MediaType.APPLICATION_JSON).content(jsonData))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
    }
}
