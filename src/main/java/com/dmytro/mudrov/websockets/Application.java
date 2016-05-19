package com.dmytro.mudrov.websockets;

import com.dmytro.mudrov.websockets.controller.EchoEndpointHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import java.util.Arrays;

@Configuration
@EnableAutoConfiguration
@EnableWebSocket
public class Application extends SpringBootServletInitializer implements WebSocketConfigurer {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
    }

    @Bean
    public EchoEndpointHandler webSocketEndpoint() {
        return new EchoEndpointHandler();
    }

    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler(), "/screen/creation/request").withSockJS();
    }

    @Bean
    public WebSocketHandler webSocketHandler() {
        return new EchoEndpointHandler();
    }

}