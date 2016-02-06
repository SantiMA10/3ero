public abstract class Movie 
{
	
	private String title;
	
	public Movie(String title) 
	{
		this.title = title;
	}
	
	public String getTitle() 
	{
		return title;
	}

	abstract int getPoints(int days);

	abstract double getPrice(int days);
}
