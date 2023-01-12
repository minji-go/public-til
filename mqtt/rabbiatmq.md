## Install

- RabbitMQ 도커 컨테이너 실행 `웹 콘솔 포트: 15672, AMQP 포트: 5672`
```
$ docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management-alpine
```


---
## Setting (RabbitmqConfig, build.gradle, application.properties)
- build. gradle
```            
implementation 'org.springframework.boot:spring-boot-starter-amqp'
```

- application.properties
```
spring.rabbitmq.host = localhost
spring.rabbitmq.port = 5672
spring.rabbitmq.username = guest
spring.rabbitmq.password = guest
```

- RabbitmqConfig
```java
package com.vitcon.core.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RabbitmqConfig {

    private static final String queueName = "hello.queue";
    private static final String exchangeName = "hello.message";
    private static final String routingKey = "hello.key";

    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;
    @Value("${spring.rabbitmq.port}")
    private int port;


    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange directExchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    Binding binding(TopicExchange topicExchange, Queue queue) {
        return BindingBuilder.bind(queue).to(topicExchange).with(routingKey);
    }


    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

    @Bean
    MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
```
---
## Use (Controller + Service)

```java
@Autowired
private RabbitTemplate rabbitTemplate;

@GetMapping("publish")
public void publish() {
    Calendar calendar = Calendar.getInstance();
    rabbitTemplate.convertAndSend("hello.message", "hello.key", "PUBLISH: " + calendar);
}

@RabbitListener(queues = "hello.queue")
public void receiveMessage(String message) {
    System.out.println("message = " + message);
}

```

---
## REFERENCE
 - RabbitMQ 사용 이유
 https://mio-java.tistory.com/50

 - RabbitMQ 연동 코드
 https://jsonobject.tistory.com/560
 https://chamggae.tistory.com/164

