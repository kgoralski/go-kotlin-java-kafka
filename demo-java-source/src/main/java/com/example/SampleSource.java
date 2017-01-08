package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableBinding(Source.class)
public class SampleSource {

	private final MessageChannel output;

	@Autowired
	public SampleSource(MessageChannel output) {
		this.output = output;
	}

	@PostMapping(path = "/message")
	public void sendMessage(@RequestBody String name) {
		output.send(MessageBuilder.withPayload(name).build());
	}
}