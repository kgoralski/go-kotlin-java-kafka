package com.example

import org.slf4j.LoggerFactory
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.cloud.stream.messaging.Sink
import org.springframework.stereotype.Component

@Component
@EnableBinding(Sink::class)
open class SampleSink {

    private val logger = LoggerFactory.getLogger(SinkApplication::class.java)
    @StreamListener(Sink.INPUT)
    fun loggerSink(message: String) {
        logger.info("Kotlin received: " + message)
    }
}


