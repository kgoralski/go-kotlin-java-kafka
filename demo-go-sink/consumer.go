package main

import (
	"fmt"

	"encoding/json"

	"github.com/Shopify/sarama"
)

var brokers = []string{"localhost:9092"}

type Message struct {
	Name string `json:"name"`
}

func subscribe(topic string, consumer sarama.Consumer) {
	partitionList, err := consumer.Partitions(topic) //get all partitions on the given topic
	if err != nil {
		fmt.Println("Error retrieving partitionList ", err)
	}
	initialOffset := sarama.OffsetNewest //get offset for the newest message on the topic

	for _, partition := range partitionList {
		pc, _ := consumer.ConsumePartition(topic, partition, initialOffset)

		go func(pc sarama.PartitionConsumer) {
			for message := range pc.Messages() {
				messageReceived(message)
			}
		}(pc)
	}
}

func messageReceived(message *sarama.ConsumerMessage) {
	var m Message

	err := json.Unmarshal(message.Value, &m)
	if err != nil {
		println("JSON parsing error ", err)
	}
	println("Golang received: " + m.Name)

	saveMessage(m.Name)
}
