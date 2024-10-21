package org.example.springcloudgateway.Custom;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class AddRequestHeaderIfNotPresentGatewayFilterFactory extends AbstractGatewayFilterFactory<AddRequestHeaderIfNotPresentGatewayFilterFactory.Config> {

    public AddRequestHeaderIfNotPresentGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            // Check if the header is not already present
            if (!request.getHeaders().containsKey(config.getName())) {
                ServerHttpRequest modifiedRequest = request.mutate()
                        .header(config.getName(), config.getValue())
                        .build();

                return chain.filter(exchange.mutate().request(modifiedRequest).build());
            }

            return chain.filter(exchange);
        };
    }

    public static class Config {
        private String name;
        private String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
