package com.nighthawk.spring_portfolio;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Main.class);

        // Set a custom banner
        application.setBannerMode(Banner.Mode.CONSOLE);

        // Run the application
        application.run(args);
    }
}
