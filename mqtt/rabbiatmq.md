## Install
- RabbitMQ 도커 컨테이너 실행
> 웹 콘솔 포트: 15672
> AMQP 포트: 5672
`$ docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management-alpine`

--
## Setting (RabbitmqConfig, build.gradle, application.properties)
- build. gradle
`implementation "org.springframework.boot:spring-boot-starter-amqp"`
- application.properties
```
spring.rabbitmq.host = localhost
spring.rabbitmq.port = 5672
spring.rabbitmq.username = guest
spring.rabbitmq.password = guest
```

- RabbitmqConfig
```
package com.vitcon.core.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
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
    private static final String directExchangeName = "hello.message";
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
    DirectExchange directExchange() {
        return new DirectExchange(directExchangeName);
    }

    @Bean
    Binding binding(DirectExchange directExchange, Queue queue) {
        return BindingBuilder.bind(queue).to(directExchange).with(routingKey);
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
--
## Use (Controller + Service)

```
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


- REFERENCE
> https://jsonobject.tistory.com/560