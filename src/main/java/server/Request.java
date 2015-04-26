package server;
//Greg R comment test edited
//test John Chow
import java.util.ArrayList;

public class Request 
{

    private final long id;
    private final String leaf;
	private final String fruit;
	private final String bugs;
	private final String branches;
	private final String noResponse = "Sorry! We were unable to match your query with a response.";
	
    public Request(long id, String leaf, String fruit, String bugs, String branches) 
	{
        this.id = id;
        this.leaf = leaf;
		this.fruit = fruit;
		this.bugs = bugs;
		this.branches = branches;
    }

    public long getID() 
	{
        return id;
    }
	
	public ArrayList<String> generateName()
	{
		ArrayList<String> names = new ArrayList<String>();
		
		String[] leafData = leaf.split(",");
		System.out.println(leafData.length);
		boolean[] leafTranslate = new boolean[7];
		String[] fruitData = fruit.split(",");
		boolean[] fruitTranslate = new boolean[8];
		
		for (int i = 0; i < leafData.length; i++) 
		{
			try 
			{
				leafTranslate[i] = (Integer.parseInt(leafData[i]) != 0);
			} 
			catch (NumberFormatException nfe) 
			{};
		}
		if (leafTranslate[0])
		{
			names.add("Verticillium Wilt");
			names.add("Fusarium");
		}
		if (leafTranslate[1])
		{
			names.add("Flea beetles");
		}
		if (leafTranslate[2])
		{
			names.add("");
		}
		if (leafTranslate[3])
		{
			names.add("");
		}
		if (leafTranslate[4])
		{	
			names.add("Colorado Potato Beetle");
		}
		if (leafTranslate[5])
		{
			names.add("");
		}
		if (leafTranslate[6])
		{
			names.add("Potato Leaf Hopper");
		}
		
		for (int i = 0; i < fruitData.length; i++) 
		{
			try 
			{
				fruitTranslate[i] = (Integer.parseInt(fruitData[i]) != 0);
			} 
			catch (NumberFormatException nfe) 
			{};
		}
		if (fruitTranslate[0])
		{
			names.add("");
		}
		if (fruitTranslate[1])
		{
			names.add("");
		}
		if (fruitTranslate[2])
		{
			names.add("");
		}
		if (fruitTranslate[3])
		{
			names.add("");
		}
		if (fruitTranslate[4])
		{
			names.add("");
		}
		if (fruitTranslate[5])
		{
			names.add("Pythium");
		}
		if (fruitTranslate[6])
		{
			names.add("Anthracnose");
			names.add("Colorado Potato Beetle");
		}
		
		return names;
	}
	
	public ArrayList<String> generateContent()
	{
		ArrayList<String> content = new ArrayList<String>();
		
		String[] leafData = leaf.split(",");
		boolean[] leafTranslate = new boolean[7];
		String[] fruitData = fruit.split(",");
		boolean[] fruitTranslate = new boolean[8];
		
		for (int i = 0; i < leafData.length; i++) 
		{
			try 
			{
				leafTranslate[i] = (Integer.parseInt(leafData[i]) != 0);
			} 
			catch (NumberFormatException nfe) 
			{};
		}
		if (leafTranslate[0])
		{
			content.add("Water-soaked, yellow and brown areas on leaves; leaves droop or wilt; drop/internal browning of lower stem.");
			content.add("Bleached white or yellow tissue along leaf veins, stunted plants");
		}
		if (leafTranslate[1])
		{
			content.add("Small black beetles that hop like fleas and pit leaves; holes get larger as leaves grow");
		}
		if (leafTranslate[2])
		{
			content.add("");
		}
		if (leafTranslate[3])
		{
			content.add("");
		}
		if (leafTranslate[4])
		{
			content.add("Adults have yellow and black stripes, larva pink with black spots and slug-like; Eat leaves, defoliate plants, and chew on/scars fruit");
		}
		if (leafTranslate[5])
		{
			content.add("");
		}
		if (leafTranslate[6])
		{
			content.add("Adults are light green and wedge-shaped; Nymphs are bright yellow-green and walk sideways. Hopperburn: Tips & edges of leaves yellow and curl up");
		}
		
		for (int i = 0; i < fruitData.length; i++) 
		{
			try 
			{
				fruitTranslate[i] = (Integer.parseInt(fruitData[i]) != 0);
			} 
			catch (NumberFormatException nfe) 
			{};
		}
		if (fruitTranslate[0])
		{
			content.add("");
		}
		if (fruitTranslate[1])
		{
			content.add("");
		}
		if (fruitTranslate[2])
		{
			content.add("");
		}
		if (fruitTranslate[3])
		{
			content.add("");
		}
		if (fruitTranslate[4])
		{
			content.add("");
		}
		if (fruitTranslate[5])
		{
			content.add("Girdles and kills young seedlings, fuzzy white growth on fruit.");
		}
		if (fruitTranslate[6])
		{
			content.add("Dark, water-soaked fruit lesions.");
			content.add("Adults have yellow and black stripes, larva pink with black spots and slug-like; Eat leaves, defoliate plants, and chew on/scars fruit");
		}
		
		return content;
	}
}