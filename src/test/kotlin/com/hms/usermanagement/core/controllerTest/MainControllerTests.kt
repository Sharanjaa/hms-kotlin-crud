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

package com.hms.usermanagement.core.controllerTest

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.hms.usermanagement.core.controller.MainController
import com.hms.usermanagement.core.model.User
import com.hms.usermanagement.core.service.UserService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
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
    lateinit var service: UserService

    @Test
    fun findAll() {
        val content="""{"statusCode":200,"message":"User Details Added Successfully","data":[{"id":2,"firstname":"string1","lastname":"string","email":"string","contactno":0},{"id":5,"firstname":"string3","lastname":"string","email":"string","contactno":0},{"id":6,"firstname":"string4","lastname":"string","email":"string","contactno":0},{"id":9,"firstname":"string45","lastname":"string","email":"string","contactno":0},{"id":10,"firstname":"string","lastname":"string","email":"string","contactno":0},{"id":11,"firstname":"strwing","lastname":"string","email":"string","contactno":0},{"id":12,"firstname":"Deeps","lastname":"Senthu","email":"sharu@gmail.com","contactno":4234}],"error":null}"""
        val response = restTemplate.getForObject<String>("/api/users")
        assertThat(content).isEqualTo(response)
    }

    @Test
    fun createUser() {
        mvc = MockMvcBuilders.standaloneSetup(controller).setMessageConverters(MappingJackson2HttpMessageConverter()).build()
        var user = User(1, "Test", "Test Content", "test", 1324)
        var jsonData = jacksonObjectMapper().writeValueAsString(user)
        mvc.perform(MockMvcRequestBuilders.post("/api/users/").contentType(MediaType.APPLICATION_JSON).content(jsonData))
                .andExpect(MockMvcResultMatchers.status().isCreated)
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
    }

    @Test
    fun searhUserErrorCheck() {
        mvc=MockMvcBuilders.standaloneSetup(controller).setMessageConverters(MappingJackson2HttpMessageConverter()).build()
        val content = """{"statusCode":400,"message":"Required String parameter 'field' is not present","data":null,"error":{"errorCode":"MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION","errorMesage":"Required String parameter 'name' is not present"}}"""
        val response=restTemplate.getForObject<String>("/api/users/search?nae/strwing")
        assertThat(content).isEqualTo(response)
    }


}
