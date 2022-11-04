package cn.yang.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        // --spring.profiles.active=dev
        SpringApplication.run(Main.class, args);
    }

}
