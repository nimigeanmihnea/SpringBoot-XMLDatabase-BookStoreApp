package application;

import application.controller.BookController;
import application.repository.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by MIHONE on 4/8/2017.
 */

@SpringBootApplication
public class Program extends SpringBootServletInitializer {

    @Autowired
    private static Users users;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder){
        return applicationBuilder.sources(Program.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Program.class, args);
    }
}
