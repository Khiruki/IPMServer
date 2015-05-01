package server;

public class QueryResult 
{
    private final int ID;    
    private final String Name;
    private final String Text;
    private final String Link;
    
    public QueryResult(int id, String name, String text, String link) 
	{
        this.ID = id;
		this.Name = name;
		this.Text = text;
		this.Link = link;
    }

    public QueryResult(int id, String name) 
	{
        this.ID = id;
		this.Name = name;
		this.Text = "";
		this.Link = "";
    }
    
	public String getText() {
		return Text;
	}

	public String getName() {
		return Name;
	}

	public String getLink() {
		return Link;
	}

	public int getID() {
		return ID;
	}
}