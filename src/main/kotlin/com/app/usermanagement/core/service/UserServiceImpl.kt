/*
 * (C) Copyright 2008-2019 hSenid Software International (Pvt) Limited.
 *
 *  All Rights Reserved.
 *
 *  These materials are unpublished, proprietary, confidential source code of
 *  hSenid Software International (Pvt) Limited and constitute a TRADE SECRET
 *  of hSenid Software International (Pvt) Limited.
 *
 *  hSenid Software International (Pvt) Limited retains all title to and intellectual
 *  property rights in these materials.
 */

package com.app.usermanagement.core.service

import com.app.usermanagement.core.enum.ResponseCode
import com.app.usermanagement.core.model.User
import com.app.usermanagement.core.repository.UserRepository
import org.springframework.dao.DataAccessException
import org.springframework.stereotype.Service
import java.util.*

@Service("userService")
class UserServiceImpl(private val userRepository: UserRepository, private val generatorService: SequenceGeneratorServiceImpl) : UserService {

    override fun findAll(): List<User> {
        try {
            return userRepository.findAll()
        } catch (e: DataAccessException) {
            throw ServiceException(e, ResponseCode.DATABASE_ERROR)
        }
    }

    override fun findById(id: Long): Optional<User> {
        try {
            if (!userRepository.findById(id).isPresent) {
                throw ServiceException(null, ResponseCode.USER_DOES_NOT_EXISTS)
            } else {
                return userRepository.findById(id)
            }
        } catch (e: DataAccessException) {
            throw ServiceException(e, ResponseCode.DATABASE_ERROR)
        }
    }

    override fun findByFirstName(firstName: String): List<User> {
        try {
            if(!userRepository.findByFirstnameContainingIgnoreCase(firstName).isNotEmpty()){
                throw ServiceException(null, ResponseCode.USER_DOES_NOT_EXISTS)
            }
            return userRepository.findByFirstnameContainingIgnoreCase(firstName)
        } catch (e: DataAccessException) {
            throw ServiceException(e, ResponseCode.DATABASE_ERROR)
        }

    }

    override fun add(user: User): User {
        try {
            if (userRepository.findByFirstnameIgnoreCase(user.firstname).isNotEmpty()) {
                throw ServiceException(null, ResponseCode.USER_ALREADY_EXISTS)
            }
            user.id = generatorService.generateSequence(user.seqname)!!
            return userRepository.save(user)
        } catch (e: DataAccessException) {
            throw ServiceException(e, ResponseCode.DATABASE_ERROR)
        }

    }

    override fun update(user: User): User {
        try {
            if (!findById(user.id).isPresent) {
                throw ServiceException(null, ResponseCode.USER_DOES_NOT_EXISTS)
            }

            return userRepository.save(user)
        } catch (e: DataAccessException) {
            throw ServiceException(e, ResponseCode.DATABASE_ERROR)
        }

    }

    override fun deleteById(id: Long) {
        try {
            if (!findById(id).isPresent) {
                throw ServiceException(null, ResponseCode.USER_DOES_NOT_EXISTS)
            }
            userRepository.deleteById(id)
        } catch (e: DataAccessException) {
            throw ServiceException(e, ResponseCode.DATABASE_ERROR)
        }
    }

}