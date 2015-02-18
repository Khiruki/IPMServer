package server;

import java.util.ArrayList;

public class Response 
{
    private final long id;
    private final ArrayList<String> Name;
	private final ArrayList<String> Content;
	
    public Response(long id, ArrayList<String> name, ArrayList<String> content) 
	{
        this.id = id;
        this.Name = name;
		this.Content = content;
    }

	public Response(Request req) 
	{
		this.id = req.getID();
		this.Name = req.generateName();
		this.Content = req.generateContent();
    }
	
    public long getId() 
	{
        return id;
    }
	
	public ArrayList<String> getName()
	{
		return Name;
	}
	
	public ArrayList<String> getContent()
	{
		return Content;
	}
}