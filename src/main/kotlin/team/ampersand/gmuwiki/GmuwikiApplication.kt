package team.ampersand.gmuwiki

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GmuwikiApplication

fun main(args: Array<String>) {
	runApplication<GmuwikiApplication>(*args)
}
