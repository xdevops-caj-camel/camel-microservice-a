[TOC]

# Demo for Camel with Spring Boot

## Camel Route Demos
- Timer
- Log
- File
- ActiveMQ


## ActiveMQ
Start ActiveMQ docker image via podman:
```bash
podman run -p 61616:61616 -p 8161:8161 rmohr/activemq
```

Access ActiveMQ dashboard: <http://localhost:8161>, and click "Manage ActiveMQ broker".
Login with default username and password: `admin/admin`.
Open "Queue" to check the message queue 

- When no consumer, check its pending messages on ActiveMQ
- When has consumer, check consumer and pending messages again

## Troubleshooting

Error: No endpoint could be found for: activemq://my-activemq-queue
Solution: Need import Camel ActiveMQ starter dependency:
```xml
<dependency>
    <groupId>org.apache.camel.springboot</groupId>
    <artifactId>camel-activemq-starter</artifactId>
    <version>${camel.version}</version>
</dependency>
```

And configure `spring.activemq.broker-url` in `application.properties`.
Example:
```properties
spring.activemq.broker-url=tcp://localhost:61616
```

## References
References:
- [Apache Camel Framework Tutorial with Spring Boot, Eclipse and Maven](https://www.youtube.com/watch?v=eh9C0GyxtHE)