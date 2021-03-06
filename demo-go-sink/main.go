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

func html(text string) string {
	html := `<html>
	<head>
		<title>Hello</title>
		<script src="/main.js"></script>
	</head>
	<body> Received: ` + text +
		`</body>
	</html>
	`
	return html

}

func main() {
	http.HandleFunc("/main.js", func(w http.ResponseWriter, r *http.Request) {

		mainJS := `alert("` + getMessage() + `");`
		fmt.Fprintf(w, mainJS)
	})
	http.HandleFunc("/", func(w http.ResponseWriter, r *http.Request) {
		if r.URL.Path != "/" {
			http.NotFound(w, r)
			return
		}
		pusher, ok := w.(http.Pusher)
		if ok {
			if err := pusher.Push("/main.js", nil); err != nil {
				log.Printf("Failed to push: %v", err)
			}
		}
		fmt.Fprintf(w, html(getMessage()))
	})

	config := sarama.NewConfig()
	config.Consumer.Return.Errors = true
	consumer, err := sarama.NewConsumer(brokers, config)
	if err != nil {
		fmt.Println("Could not create consumer: ", err)
	}
	subscribe(topic, consumer)

	log.Fatal(http.ListenAndServeTLS(":8082", "cert.pem", "key.pem", nil))
}
