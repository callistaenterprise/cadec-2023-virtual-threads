package se.callista.cadec.loom.product.config;

import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ClientRequest;
import se.callista.cadec.loom.product.multitenancy.interceptor.TenantInterceptor;
import se.callista.cadec.loom.product.multitenancy.util.TenantContext;

@Configuration
public class WebClientConfig {

  @Bean
  WebClientCustomizer tenantIdWebClientCustomizer() {
    return webClientBuilder ->
        // Add Authorization header from token, if present
        webClientBuilder
            .filter((request, next) ->
                next.exchange(ClientRequest.from(request)
                    .header(TenantInterceptor.X_TENANT_ID, TenantContext.getTenantId())
                    .build())
            );
  }
}
