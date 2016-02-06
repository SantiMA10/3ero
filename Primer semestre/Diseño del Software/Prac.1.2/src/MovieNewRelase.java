
public class MovieNewRelase extends Movie{
	
	private double normalPrice = 3.0;

	public MovieNewRelase(String title) {
		super(title);
	}
	
	@Override
	protected double getPrice(int days){
		return normalPrice * days;
	}
	
	@Override
	protected int getPoints(int days){
		
		if(days > 1){
			return 2;
		}
		return 1;
		
	}
	

}
