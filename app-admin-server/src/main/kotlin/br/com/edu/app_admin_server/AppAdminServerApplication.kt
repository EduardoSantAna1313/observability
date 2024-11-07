package br.com.edu.app_admin_server

import de.codecentric.boot.admin.server.config.EnableAdminServer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@EnableAdminServer
@SpringBootApplication
class AppAdminServerApplication

fun main(args: Array<String>) {
	runApplication<AppAdminServerApplication>(*args)
}
