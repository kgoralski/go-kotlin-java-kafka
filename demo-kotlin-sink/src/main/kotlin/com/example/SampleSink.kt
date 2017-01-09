package com.example

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.cloud.stream.messaging.Sink
import org.springframework.stereotype.Component

@Component
@EnableBinding(Sink::class)
open class SampleSink {

    // jackson kotlin module
    val JSON = jacksonObjectMapper()

    data class Message(var name: String)

    private val logger = LoggerFactory.getLogger(SampleSink::class.java)
    @StreamListener(Sink.INPUT)
    fun receive(message: ByteArray) {
        val parsedMessage = JSON.readValue(message, Message::class.java)
        logger.info("Kotlin received: " + parsedMessage.name)
    }
}


