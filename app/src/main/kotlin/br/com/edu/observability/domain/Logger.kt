package br.com.edu.observability.domain

import org.slf4j.LoggerFactory

object Logger {

    fun logger(clazz: Class<*>) = LoggerFactory.getLogger(clazz)

}