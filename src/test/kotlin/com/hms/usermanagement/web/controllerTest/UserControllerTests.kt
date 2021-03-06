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

package com.hms.usermanagement.web.controllerTest

import com.hms.usermanagement.core.service.UserService
import com.hms.usermanagement.web.controller.UserController
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.util.ReflectionTestUtils
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders


@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTests {

    lateinit var mvc: MockMvc

    @InjectMocks
    lateinit var controller: UserController

    @Mock
    lateinit var service: UserService

    @Test
    fun createUser() {
        ReflectionTestUtils.setField(controller, "apiUrl", "http://localhost:8080/sharu")
        mvc = MockMvcBuilders.standaloneSetup(controller).setMessageConverters(MappingJackson2HttpMessageConverter()).build()
        mvc.perform(MockMvcRequestBuilders.post("/adduser")
                .param("firstname", "Deeps")
                .param("lastname", "Senthu")
                .param("email", "sharu@gmail.com")
                .param("contactno", 4234.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk)
//                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andReturn();
    }

    @Test
    fun getUser() {
        ReflectionTestUtils.setField(controller, "apiUrl", "http://localhost:8080/sharu")
        mvc = MockMvcBuilders.standaloneSetup(controller).setMessageConverters(MappingJackson2HttpMessageConverter()).build()
        mvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string(containsString("String")));
    }

    @Test
    fun searchUser() {
        ReflectionTestUtils.setField(controller, "apiUrl", "http://localhost:8080/sharu")
        mvc = MockMvcBuilders.standaloneSetup(controller).setMessageConverters(MappingJackson2HttpMessageConverter()).build()
        mvc.perform(MockMvcRequestBuilders.get("/search")
                .param("firstname", "string"))
                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string(containsString("String")));
    }

    @Test
    fun addUser() {
        ReflectionTestUtils.setField(controller, "apiUrl", "http://localhost:8080/sharu")
        mvc = MockMvcBuilders.standaloneSetup(controller).setMessageConverters(MappingJackson2HttpMessageConverter()).build()
        mvc.perform(MockMvcRequestBuilders.get("/add"))
                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string(containsString("String")));
    }

    @Test
    fun updateUser() {
        ReflectionTestUtils.setField(controller, "apiUrl", "http://localhost:8080/sharu")
        mvc = MockMvcBuilders.standaloneSetup(controller).setMessageConverters(MappingJackson2HttpMessageConverter()).build()
        mvc.perform(MockMvcRequestBuilders.post("/update/7")
                .param("id", 7.toString())
                .param("firstname", "Deeps")
                .param("lastname", "Senthu")
                .param("email", "sharu@gmail.com")
                .param("contactno", 4234.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk)
//                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andReturn()
    }

    @Test
    fun viewUser() {
        ReflectionTestUtils.setField(controller, "apiUrl", "http://localhost:8080/sharu")
        mvc = MockMvcBuilders.standaloneSetup(controller).setMessageConverters(MappingJackson2HttpMessageConverter()).build()
        mvc.perform(MockMvcRequestBuilders.get("/view/8"))
                .andExpect(MockMvcResultMatchers.status().isOk)
//                .andExpect(MockMvcResultMatchers.content().string(containsString("String")))
    }

    @Test
    fun editUser() {
        ReflectionTestUtils.setField(controller, "apiUrl", "http://localhost:8080/sharu")
        mvc = MockMvcBuilders.standaloneSetup(controller).setMessageConverters(MappingJackson2HttpMessageConverter()).build()
        mvc.perform(MockMvcRequestBuilders.get("/edit/7"))
                .andExpect(MockMvcResultMatchers.status().isOk)
//                .andExpect(MockMvcResultMatchers.content().string(containsString("String")))
    }

//    @Test
//    fun deleteUser() {
//        mvc = MockMvcBuilders.standaloneSetup(controller).setMessageConverters(MappingJackson2HttpMessageConverter()).build()
//        mvc.perform(MockMvcRequestBuilders.get("/delete/11"))
//                .andExpect(MockMvcResultMatchers.status().isOk)
////                .andExpect(MockMvcResultMatchers.content().string(containsString("String")))
//    }
}