# About
'Hello world' Kafka integration of Java, Kotlin and Go apps

- demo-java-source with Spring Cloud Stream - localhost:8080
- demo-kotlin-sink with Spring Cloud Stream - localhost:8081 (Consumer)
- demo-go-sink with Shopify/sarama - localhost:8082 (Consumer)

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
1. Send reqest to Java Source App - POST http://localhost:8080/message {"name": "Krzysztof"}
1. Both Go and Kotlin app will print the messages on their consoles

# TODO
1. Make it more interesting...
1. Add SSE/ Websockets/ Http2 Push ?  
