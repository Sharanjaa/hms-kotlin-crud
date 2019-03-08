/*
* KotlinkcrudApplication.kt
*
* Main Application: V1
*
* sharanjaas@hsenid.virtusa.om
*/

package com.app.kotlinkcrud

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinkcrudApplication

private val logger = LoggerFactory.getLogger(KotlinkcrudApplication::class.java)

fun main(args: Array<String>) {
    runApplication<KotlinkcrudApplication>(*args)
    logger.info("--Application Started--");
}
