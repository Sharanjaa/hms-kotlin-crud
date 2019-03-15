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

package com.hms.usermanagement.core.service

import com.hms.usermanagement.core.enum.ResponseCode
import com.hms.usermanagement.core.model.User
import com.hms.usermanagement.core.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataAccessException
import org.springframework.stereotype.Service
import java.util.*


interface UserServiceInterface {
    fun findAll(): List<User>
    fun findById(id: Long): Optional<User>
    fun findByFirstName(firstName: String): List<User>
    fun add(user: User): User
    fun update(user: User): User
    fun deleteById(id: Long)
}


@Service
class UserService : UserServiceInterface {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var generatorService: SequenceGeneratorServiceImpl

    private val logger=LoggerFactory.getLogger(UserService::class.java)

    override fun findAll(): List<User> {

        return executeServiceMethod {
            userRepository.findAll()
        } as List<User>

    }

    override fun findById(id: Long): Optional<User> {

        return executeServiceMethod {
            if (!userRepository.findById(id).isPresent) {
                throw ServiceException(null, ResponseCode.USER_DOES_NOT_EXISTS)
            }
            userRepository.findById(id)
        } as Optional<User>

    }

    override fun findByFirstName(firstName: String): List<User> {

        return executeServiceMethod {
            if (!userRepository.findByFirstnameContainingIgnoreCase(firstName).isNotEmpty()) {
                throw ServiceException(null, ResponseCode.USER_DOES_NOT_EXISTS)
            }
            userRepository.findByFirstnameContainingIgnoreCase(firstName)
        } as List<User>

    }

    override fun add(user: User): User {

        return executeServiceMethod {
            if (userRepository.findByFirstnameIgnoreCase(user.firstname).isNotEmpty()) {
                throw ServiceException(null, ResponseCode.USER_ALREADY_EXISTS)
            }
            user.id=generatorService.generateSequence(user.seqname)!!
            userRepository.save(user)
        } as User

    }

    override fun update(user: User): User {

        return executeServiceMethod {
            if (!findById(user.id).isPresent) {
                throw ServiceException(null, ResponseCode.USER_DOES_NOT_EXISTS)
            }
            userRepository.save(user)
        } as User

    }

    override fun deleteById(id: Long) {

        return executeServiceMethod {
            if (!findById(id).isPresent) {
                throw ServiceException(null, ResponseCode.USER_DOES_NOT_EXISTS)
            }
            userRepository.deleteById(id)
        } as Unit

    }

    fun executeServiceMethod(method: () -> Any): Any {
        try {
            logger.info("Method Started : ${method.javaClass.name}")
            return method()
        } catch (e: DataAccessException) {
            logger.error("Error Occurred : ${method.javaClass.name}", e)
            throw ServiceException(e, ResponseCode.DATABASE_ERROR)
        } catch (e: Exception) {
            logger.error("Error Occurred : ${method.javaClass.name}", e)
            throw e
        }
    }
}