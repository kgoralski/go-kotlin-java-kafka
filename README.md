# About
'Hello world' Kafka integration of Java, Kotlin and Go apps

- demo-java-source with Spring Cloud Stream - localhost:8080
- demo-kotlin-sink with Spring Cloud Stream - localhost:8081 (Consumer)
- demo-go-sink with Shopify/sarama (Kafka Client) - localhost:8082 (Consumer)
- Apache Kafka as Message Broker
- Go Http/2 Push example (just for fun)

Running Kafka from docker:
```
docker run --name spotifykafka -p 2181:2181 -p 9092:9092 --env ADVERTISED_HOST=localhost --env ADVERTISED_PORT=9092 spotify/kafka 
```

to get Shopify/sarama lib:
```
go get github.com/Shopify/sarama
```

# How to run
1. Run Java/Kotlin/Go app from IDE (Prefer IDEA & Gogland)
1. Send request to Java Source App - POST http://localhost:8080/message {"name": "Krzysztof"}
1. Both Go and Kotlin app will print the messages on their consoles, something like this:
```
INFO 22360 --- [afka-listener-1] com.example.SampleSink  Kotlin received: Krzysztof
```

```
Golang received: "Krzysztof"}

```
# Http/2 Go Push
1. Go 1.8beta2 required
1. Run crypto/tls/generate_cert.go to generate cert.pem and key.pem. and put them to the project
1. Go to https://localhost:8082/ (yes https)
1. Resources will be pushed to browser, check Network (devtools)


# References
1. https://medium.com/@Oskarr3/implementing-cqrs-using-kafka-and-sarama-library-in-golang-da7efa3b77fe#.razrnz8eh
1. https://blog.codecentric.de/en/2016/04/event-driven-microservices-spring-cloud-stream/
1. [Go 1.8 Http/2 Push](https://gist.github.com/rakyll/eec415977f85d50a493ca8472ba97b68)

# TODO
1. Make it more interesting... because printing to console is not cool enough ;)
1. Add SSE/ Websockets ?  


