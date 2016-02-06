
public class MovieRegular extends Movie{
	
	private double normalPrice = 2.0;
	private double offerPrice = 1.5;
	private int offerDays = 2;

	public MovieRegular(String title) {
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
