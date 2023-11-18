package com.ettounani.gatewayy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties.Discovery;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mustache.MustacheProperties.Reactive;
import org.springframework.cloud.client.discovery.composite.reactive.ReactiveCompositeDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayyApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayyApplication.class, args);
	}
	//@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p.path("/customers/**").uri("lb://CUSTOMER-SERVICE"))
				.route(p -> p.path("/products/**").uri("lb://PRODUCT-SERVICE"))
				.build();
	}
	@Bean
	public DiscoveryClientRouteDefinitionLocator definitionLocator(ReactiveCompositeDiscoveryClient discoveryClient, DiscoveryLocatorProperties properties ) {
		
		return new DiscoveryClientRouteDefinitionLocator(discoveryClient, properties);
	}
}
