package server;

import java.sql.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

// Main class. Starts the Spring application
@ComponentScan
@EnableAutoConfiguration
public class Application 
{
    public static void main(String[] args) 
	{
        SpringApplication.run(Application.class, args);
    }
}