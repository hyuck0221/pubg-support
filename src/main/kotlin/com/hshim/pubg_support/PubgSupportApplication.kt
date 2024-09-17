package com.hshim.pubg_support

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class PubgSupportApplication

fun main(args: Array<String>) {
	runApplication<PubgSupportApplication>(*args)
}
