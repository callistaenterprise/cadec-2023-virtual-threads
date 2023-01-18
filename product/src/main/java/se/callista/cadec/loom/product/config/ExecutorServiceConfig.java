package se.callista.cadec.loom.product.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.boot.web.embedded.tomcat.TomcatProtocolHandlerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;

@Configuration
public class ExecutorServiceConfig {

//  @Bean
//  AsyncTaskExecutor applicationTaskExecutor() {
//    ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
//    return new TaskExecutorAdapter(executorService::execute);
//  }
//
//  @Bean
//  TomcatProtocolHandlerCustomizer<?> protocolHandlerVirtualThreadExecutorCustomizer() {
//    return protocolHandler ->
//        protocolHandler.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
//  }

}
