package config;

import client.RestClientProductsRestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientBeans {
    @Bean
    public RestClientProductsRestClient productsRestClient(@Value("${service.catalogue.uri:http://localhost:8080}") String catalogueBaseUri){
        return new RestClientProductsRestClient(RestClient.builder()
                .baseUrl(catalogueBaseUri)
                .build());
    }
}
