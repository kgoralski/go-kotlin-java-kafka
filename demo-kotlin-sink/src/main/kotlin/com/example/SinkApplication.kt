package com.example


import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class SinkApplication {


    companion object {

        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(SinkApplication::class.java, *args)
        }
    }

}