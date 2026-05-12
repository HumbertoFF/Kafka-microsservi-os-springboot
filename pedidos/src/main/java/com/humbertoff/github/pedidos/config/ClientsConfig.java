package com.humbertoff.github.pedidos.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.humbertoff.github.pedidos.client")
public class ClientsConfig {
}
