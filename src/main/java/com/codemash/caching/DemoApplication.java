package com.codemash.caching;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import static java.lang.String.format;
import static java.lang.System.nanoTime;
import static java.lang.System.out;
import static java.util.concurrent.TimeUnit.NANOSECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

@SpringBootApplication
public class DemoApplication {
    public interface CityService {
        public String getCity(String state);

    }

    @Bean
    public CityService getCityService() {
        return state -> {
            try {
                SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch (state) {
                case "OH":
                    return "Sandusky";
                default:
                    return "duno";
            }
        };
    }

    @Component
    public static class Launcher implements CommandLineRunner {

        @Autowired
        CityService service;

        @Override
        public void run(String... args) throws Exception {
            out.println(" \n\n\nCalling for city");
            long start = nanoTime();
            out.println(service.getCity("OH"));
            out.println(format("Took: %s mills \n", (NANOSECONDS.toMillis(nanoTime() - start))));

            out.println("\n\n\nCalling for city");
            start = nanoTime();
            out.println(service.getCity("OH"));
            out.println(format("Took: %s mills\n ", (NANOSECONDS.toMillis(nanoTime() - start))));
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
