package br.com.edu.observability.domain

import org.slf4j.LoggerFactory

object Logger {

    fun logger(clazz: Class<*>): org. slf4j. Logger = LoggerFactory.getLogger(clazz)

}
