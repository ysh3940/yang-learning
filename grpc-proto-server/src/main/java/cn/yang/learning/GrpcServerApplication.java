package cn.yang.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class GrpcServerApplication {

    public static void main(String[] args) {
        // --spring.profiles.active=dev
        SpringApplication.run(GrpcServerApplication.class, args);
    }

}
