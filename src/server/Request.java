package server;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

// The class for the request sent to the server from the app.
public class Request 
{

    private final long id;
    private final String leaf;
	private final String fruit;
	private final String bugs;
	private final String branches;
	private final String noResponse = "Sorry! We were unable to match your query with a response.";
	private final String plant;
	private final String name;
	private final Connection connection;
	
	// Standard request from survey
    public Request(long id, String leaf, String fruit, String bugs, String branches, String plant, String name, Connection connection) 
	{
        this.id = id;
        this.leaf = leaf;
		this.fruit = fruit;
		this.bugs = bugs;
		this.branches = branches;
		this.plant = plant;
		this.name = name;
		this.connection = connection;
    }

    public long getID() 
	{
        return id;
    }
	
	// Generate the results to be sent to the app based on the request.
	public ArrayList<QueryResult> generateContent()
	{
		ArrayList<QueryResult> content = new ArrayList<QueryResult>();
		
		String[] leafData = leaf.split(",");
		int[] leafTranslate = new int[6];
		String[] fruitData = fruit.split(",");
		int[] fruitTranslate = new int[6];
		String[] branchData = branches.split(",");
		int[] branchTranslate = new int[6];
		String[] plantData = plant.split(",");
		int[] plantTranslate = new int[7];
		String[] bugData = bugs.split(",");
		int[] bugTranslate = new int[4];		
		
		// Convert the string arguments from the app into usable integer arrays.
		for (int i = 0; i < leafData.length; i++) 
		{
			try 
			{
				leafTranslate[i] = (Integer.parseInt(leafData[i]));
			} 
			catch (NumberFormatException nfe) 
			{};
		}

		for (int i = 0; i < fruitData.length; i++) 
		{
			try 
			{
				fruitTranslate[i] = (Integer.parseInt(fruitData[i]));
			} 
			catch (NumberFormatException nfe) 
			{};
		}
		
		for (int i = 0; i < branchData.length; i++) 
		{
			try 
			{
				branchTranslate[i] = (Integer.parseInt(branchData[i]));
			} 
			catch (NumberFormatException nfe) 
			{};
		}
		
		for (int i = 0; i < plantData.length; i++) 
		{
			try 
			{
				plantTranslate[i] = (Integer.parseInt(plantData[i]));
			} 
			catch (NumberFormatException nfe) 
			{};
		}
		
		for (int i = 0; i < bugData.length; i++) 
		{
			try 
			{
				bugTranslate[i] = (Integer.parseInt(bugData[i]));
			} 
			catch (NumberFormatException nfe) 
			{};
		}
		
		// THIS SQL STATEMENT IS NOT CORRECT, IT WILL GRAB EVERYTHING. 
		String statement = "SELECT ipmapp.pests.PestName, ipmapp.pests.text, ipmapp.pests.links " + 
				"FROM ipmapp.pests INNER JOIN ipmapp.plantstopest ON ipmapp.pests.idpests = " +
				"ipmapp.plantstopest.pests_idpests INNER JOIN ipmapp.plants ON ipmapp.plants.idplants " +
				"= ipmapp.plantstopest.plants_idplants WHERE ipmapp.plants.plantname = " + "'" + name + "'" + 
				" AND " + 
				"ipmapp.pests.LeafHoles" + " = " +  leafTranslate[1] + " OR " + 
				"ipmapp.pests.LeafDiscolored" + " = " +  leafTranslate[0] + " OR " +  
				"ipmapp.pests.LeafSpots" + " = " +  leafTranslate[2] + " OR " +
				"ipmapp.pests.LeafDistorted" + " = " +  leafTranslate[5] + " OR " +
				"ipmapp.pests.Leafdefoliated" + " = " +  leafTranslate[3] + " OR " +
				"ipmapp.pests.LeafRot" + " = " +  leafTranslate[4] + " OR " +
				"ipmapp.pests.FruitHoles" + " = " +  fruitTranslate[1] + " OR " +
				"ipmapp.pests.FruitDiscolored" + " = " +  fruitTranslate[0] + " OR " +
				"ipmapp.pests.FruitSpots" + " = " +  fruitTranslate[2] + " OR " +
				"ipmapp.pests.FruitDistorted" + " = " +  fruitTranslate[3] + " OR " +
				"ipmapp.pests.FruitGalls" + " = " +  fruitTranslate[4] + " OR " +
				"ipmapp.pests.FruitRot" + " = " +  fruitTranslate[5] + " OR " +
				"ipmapp.pests.StemHoles" + " = " +  branchTranslate[1] + " OR " +
				"ipmapp.pests.StemDiscolored" + " = " +  branchTranslate[0] + " OR " +
				"ipmapp.pests.StemSpots" + " = " +  branchTranslate[2] + " OR " +
				"ipmapp.pests.StemDistorted" + " = " +  branchTranslate[3] + " OR " +
				"ipmapp.pests.StemGalls" + " = " +  branchTranslate[4] + " OR " +
				"ipmapp.pests.StemRot" + " = " +  branchTranslate[5] + " OR " +
				"ipmapp.pests.Wilt" + " = " +  plantTranslate[0] + " OR " +
				"ipmapp.pests.Dieback" + " = " +  plantTranslate[1] + " OR " +
				"ipmapp.pests.Stunted" + " = " +  plantTranslate[2] + " OR " +
				"ipmapp.pests.Distorted" + " = " +  plantTranslate[5] + " OR " +
				"ipmapp.pests.Dead" + " = " +  plantTranslate[3] + " OR " +
				"ipmapp.pests.Adjacent" + " = " +  plantTranslate[6] + " OR " +
				"ipmapp.pests.caterpillar" + " = " +  bugTranslate[1] + " OR " +
				"ipmapp.pests.LeafFeeding" + " = " +  bugTranslate[3] + " OR " +
				"ipmapp.pests.borer" + " = " +  bugTranslate[0] + " OR " +
				"ipmapp.pests.moth" + " = " +  bugTranslate[2] +
				" ORDER BY ipmapp.pests.PestName";
		
		// Execut the SQL statement for the resulting pest list. 
		try 
		{
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(statement);
			
			// Create the resulting QueryResult objects to be returned to the app
			while (rs.next()) {
	            String name = rs.getString("PestName");
	            String text = rs.getString("text");
	            String link = rs.getString("links");
	            int id = 0;	   
	            
	            content.add(new QueryResult(id, name, text, link));
	            
	            System.out.println(name + "\t" + id);
	        }
		} 
		catch (SQLException e) 
		{		
			throw new RuntimeException("Error creating statement: ", e);
		}
		
		return content;
	}
}