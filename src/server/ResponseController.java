package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResponseController 
{
    private final AtomicLong counter = new AtomicLong();
	
    @RequestMapping("/response")
    public Response response(@RequestParam(value="leaf", defaultValue="0") String leaf, 
							 @RequestParam(value="fruit", defaultValue="0") String fruit,
							 @RequestParam(value="bugs", defaultValue="0") String bugs,
							 @RequestParam(value="branches", defaultValue="0") String branches,
							 @RequestParam(value="plant", defaultValue="0") String plant,
							 @RequestParam(value="pestNum", defaultValue="0") String pestNumber,
							 @RequestParam(value="plantPerc", defaultValue="0") String plantPercent,
							 @RequestParam(value="plantNum", defaultValue="0") String plantNumber,
							 @RequestParam(value="name", defaultValue="0") String name) 
	{	  
		System.out.println(leaf);
		System.out.println(fruit);
		System.out.println(bugs);
		System.out.println(branches);
		System.out.println(plant);
		System.out.println(pestNumber);
		System.out.println(plantPercent);
		System.out.println(plantNumber);
		
    	String url = "jdbc:mysql://localhost:3306/ipmapp";
    	String username = "root";
    	String password = "";
    	Connection connection = null;
    	try {
    	    System.out.println("Connecting database...");
    	    connection = DriverManager.getConnection(url, username, password);
    	    System.out.println("Database connected!");
    	} catch (SQLException e) {
    	    throw new RuntimeException("Cannot connect the database!", e);
    	}    	
		
        Request req = new Request(counter.incrementAndGet(), leaf, fruit, bugs, branches, plant, name, connection);		
		System.out.println(req.generateContent());
		Response resp = new Response(req);
		System.out.println(resp.getContent());
		return resp;
    }
    
    @RequestMapping("/categories")
    public Response response() 
	{	  		
    	String url = "jdbc:mysql://localhost:3306/ipmapp";
    	String username = "root";
    	String password = "";
    	Connection connection = null;
    	try {
    	    System.out.println("Connecting database...");
    	    connection = DriverManager.getConnection(url, username, password);
    	    System.out.println("Database connected!");
    	} catch (SQLException e) {
    	    throw new RuntimeException("Cannot connect the database!", e);
    	}             
		Response resp = new Response(connection);		
		try {
			connection.close();
		} catch (SQLException e) { 
			throw new RuntimeException("Error closing connection: ", e);
		}
		return resp;
    }
    
    @RequestMapping("/subcategories")
    public Response response(@RequestParam(value="id", defaultValue="0") int id) 
	{	  		
    	String url = "jdbc:mysql://localhost:3306/ipmapp";
    	String username = "root";
    	String password = "";
    	Connection connection = null;
    	try {
    	    System.out.println("Connecting database...");
    	    connection = DriverManager.getConnection(url, username, password);
    	    System.out.println("Database connected!");
    	} catch (SQLException e) {
    	    throw new RuntimeException("Cannot connect the database!", e);
    	}             
		Response resp = new Response(connection, id);		
		try {
			connection.close();
		} catch (SQLException e) { 
			throw new RuntimeException("Error closing connection: ", e);
		}
		return resp;
    }
}