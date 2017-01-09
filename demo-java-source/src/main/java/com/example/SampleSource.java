package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.http.MediaType;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@EnableBinding(Source.class)
public class SampleSource {

	private final MessageChannel output;

	@Autowired
	public SampleSource(MessageChannel output) {
		this.output = output;
	}

	private ObjectMapper objectMapper = new ObjectMapper();

	@PostMapping(path = "/message", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody void sendMessage(@RequestBody MessageDto message) throws JsonProcessingException {
		byte[] bytes = objectMapper.writeValueAsBytes(message);

		// normally you can use something like this instead of bytes :
		// MessageBuilder.withPayload(name).build()
		output.send(new GenericMessage<>(bytes));
	}
}