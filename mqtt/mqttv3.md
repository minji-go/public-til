## MQTT
- MQTT : Message Queuing Telemetry Transfer
- MQTT는 Port로 1883을 사용하고, SSL상에서는 8883을 사용한다. 
- MQTT Explorer 사용
* QoS : Quality of Service
0: Message는 한 번만 전달되고, 전달여부를 확인하지 않는다. 
1: Message는 한 번 이상 전달되나, Hand-Shake가 없기 때문에 중복전송될 수도 있다.  
2: Message는 한 번만 전달되고, Hand-Shake 과정이 있다. 

---
## Setting (MqttConfig, build.gradle, application.properties)

- build. gradle
```            
implementation 'org.springframework.boot:spring-boot-starter-integration'
implementation 'org.springframework.integration:spring-integration-mqtt'
```

- application.properties
```
spring.mqtt.url = tcp://localhost:1883
spring.mqtt.username = guest
spring.mqtt.password = guest
```

- MqttConfig
```
package com.edgecross.core.config;

import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
public class MqttConfig {

    @Value("${spring.mqtt.username}")
    private String username;
    @Value("${spring.mqtt.password}")
    private String password;
    @Value("${spring.mqtt.url}")
    private String url;
    private static final String MQTT_CLIENT_ID = MqttAsyncClient.generateClientId();
    private static final String TOPIC_FILTER = "test/#";

    private MqttConnectOptions connectOptions() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        options.setUserName(username);
        options.setPassword(password.toCharArray());
        options.setServerURIs(new String[] { url });
        return options;
    }

    @Bean
    public DefaultMqttPahoClientFactory defaultMqttPahoClientFactory() {
        DefaultMqttPahoClientFactory clientFactory = new DefaultMqttPahoClientFactory();
        clientFactory.setConnectionOptions(connectOptions());
        return clientFactory;
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inboundChannel() {

        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(MQTT_CLIENT_ID, defaultMqttPahoClientFactory(), TOPIC_FILTER);
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler inboundMessageHandler() {
        return message -> {
            String topic = (String) message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC);
            System.out.println("Topic:" + topic);
            System.out.println("Payload" + message.getPayload());
        };
    }
}
```
---
## REFERENCE
- Springboot Mqtt 코드
https://kdevkr.github.io/spring-boot-integration-mqtt/
https://docs.spring.io/spring-integration/reference/html/mqtt.html#mqtt
- MqttSercurityException: 연결할 수 있는 권한이 부여되지 않음
https://stackoverflow.com/questions/63737765/mqtt-and-springboot-integration-connection-lost
