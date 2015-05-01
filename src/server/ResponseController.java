package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// The response controller maps the different calls to addresses.
@RestController
public class ResponseController 
{
    private final AtomicLong counter = new AtomicLong();
	
	// The mapping for the response based on survey answers. 
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
		
		// Generate a JDBC connection. Using local database for testing. 
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
		// Close the connection after use. 
		try {
			connection.close();
		} catch (SQLException e) { 
			throw new RuntimeException("Error closing connection: ", e);
		}
		return resp;
    }
    
	// Mapping for retrieving categories. 
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
		// Close the connection after use. 
		try {
			connection.close();
		} catch (SQLException e) { 
			throw new RuntimeException("Error closing connection: ", e);
		}
		return resp;
    }
    
	// The address mapping for retirving the list of plants under a given category.
    @RequestMapping("/subcategories")
    public Response response(@RequestParam(value="id", defaultValue="0") int id) 
	{	  		
    	String url = "jdbc:mysql://localhost:3306/ipmapp";
    	String username = "root";
    	String password = "";
    	Connection connection = null;
		// Create JDBC connection
    	try {
    	    System.out.println("Connecting database...");
    	    connection = DriverManager.getConnection(url, username, password);
    	    System.out.println("Database connected!");
    	} catch (SQLException e) {
    	    throw new RuntimeException("Cannot connect the database!", e);
    	}             
		Response resp = new Response(connection, id);		
		// Close connection after use. 
		try {
			connection.close();
		} catch (SQLException e) { 
			throw new RuntimeException("Error closing connection: ", e);
		}
		return resp;
    }
}