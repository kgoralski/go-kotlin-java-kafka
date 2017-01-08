package main

import (
	"fmt"
	"log"
	"net/http"

	"github.com/Shopify/sarama"
)

const (
	topic           = "messages"
	contentType     = "Content-Type"
	applicationJSON = "application/json"
)

func main() {

	consumer, err := sarama.NewConsumer(brokers, nil)
	if err != nil {
		fmt.Println("Could not create consumer: ", err)
	}
	subscribe(topic, consumer)

	http.HandleFunc("/", func(w http.ResponseWriter, r *http.Request) { fmt.Fprint(w, "Hello Sarama!") })

	http.HandleFunc("/retrieve", func(w http.ResponseWriter, r *http.Request) {
		w.Header().Set(contentType, applicationJSON)
		fmt.Fprint(w, getMessage())

	})

	log.Fatal(http.ListenAndServe(":8082", nil))
}
