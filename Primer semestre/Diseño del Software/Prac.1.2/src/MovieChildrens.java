
public class MovieChildrens extends Movie{
	
	private double normalPrice = 1.5;
	private double offerPrice = 1.5;
	private int offerDays = 3;

	public MovieChildrens(String title) {
		super(title);
	}
	
	@Override
	protected double getPrice(int days){
		double price = normalPrice;
		
		if (days > offerDays){
			price += (days - offerDays) * offerPrice;
		}
		
		return price;
	}
	
	@Override
	protected int getPoints(int days){
		
		return 1;
		
	}

}
