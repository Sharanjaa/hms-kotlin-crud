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

import com.hms.usermanagement.core.model.DatabaseSequence
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Service

import java.util.Objects

import org.springframework.data.mongodb.core.FindAndModifyOptions.options
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query.query


@Service("sequenceGeneratorService")
class SequenceGeneratorServiceImpl(private var mongoOperations: MongoOperations) : SequenceGeneratorService {

    override fun generateSequence(seqName: String): Long? {

        val counter = mongoOperations.findAndModify(query(where("_id").`is`(seqName)),
                Update().inc("seq", 1), options().returnNew(true).upsert(true),
                DatabaseSequence::class.java)
        return (if (!Objects.isNull(counter)) counter?.getSeq() else 1)?.toLong()

    }


}