package server;

import java.sql.*;
import java.util.ArrayList;

public class Response 
{
    private final long id;
    private final ArrayList<QueryResult> Content;
	
    public Response(long id, ArrayList<QueryResult> content) 
	{
        this.id = id;
		this.Content = content;
    }

	public Response(Request req) 
	{
		this.id = req.getID();
		this.Content = req.generateContent();
    }
	
	public Response(Connection con) 
	{
		this.id = 0;
		
		String statement = "SELECT * FROM ipmapp.subcategories";
		ArrayList<QueryResult> categories = new ArrayList<QueryResult>();
		
		try 
		{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(statement);
			
			while (rs.next()) {
	            String name = rs.getString("subcategoryname");
	            int id = rs.getInt("idsubcategories");	   
	            
	            categories.add(new QueryResult(id, name));
	            
	            System.out.println(name + "\t" + id);
	        }
		} 
		catch (SQLException e) 
		{		
			throw new RuntimeException("Error creating statement: ", e);
		}
		
		this.Content = categories;
    }
	
	public Response(Connection con, int id) 
	{
		this.id = 0;
		
		String statement = "SELECT * FROM ipmapp.plants INNER JOIN ipmapp.subtoplant ON " +
						   "ipmapp.plants.idplants = ipmapp.subtoplant.plants_idplants WHERE " + 
						   "ipmapp.subtoplant.subcategories_idsubcategories = " + id;
		
		ArrayList<QueryResult> categories = new ArrayList<QueryResult>();
		
		try 
		{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(statement);
			
			while (rs.next()) 
			{	
	            String plant = rs.getString("plantname");
	            categories.add(new QueryResult(id, plant));
	            
	            System.out.println(plant + "\t" + id);
	        }
		} 
		catch (SQLException e) 
		{		
			throw new RuntimeException("Error creating statement: ", e);
		}
		
		this.Content = categories;
    }
	
    public long getId() 
	{
        return id;
    }
	
	public ArrayList<QueryResult> getContent()
	{
		return Content;
	}
}