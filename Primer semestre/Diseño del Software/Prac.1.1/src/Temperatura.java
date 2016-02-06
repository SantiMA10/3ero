
public class Temperatura {
	
	public static final int AMBOS = 0;
	public static final int CELSIUS = 1;
	public static final int FAHRENHEIT = 2;
	
	private double celsius;	
	private double fahrenheit;
	private int modo;
	
	public Temperatura(double temp){
		modo = AMBOS;
		this.celsius = temp;
		this.fahrenheit =  celsius * 1.8 + 32;
	}
	
	public Temperatura(double temp, int modo){
		
		if(modo == AMBOS){
			this.celsius = temp;
			this.fahrenheit =  celsius * 1.8 + 32;
		}
		else if(modo == CELSIUS){
			this.celsius = temp;
		}
		else if(modo == FAHRENHEIT){
			this.fahrenheit = temp;
		}
		this.modo = modo;
		
	}
	
	public void setCelsius(int temp){
		if(modo == AMBOS){
			this.celsius = temp;
			this.fahrenheit =  celsius * 1.8 + 32;
		}
		else if(modo == CELSIUS){
			this.celsius = temp;
		}
		else if(modo == FAHRENHEIT){
			this.fahrenheit = temp;
		}
	}
	
	public void setFahrenheit(int temp){
		if(modo == AMBOS){
			this.celsius = ( temp - 32 ) / 1.8; 
			this.fahrenheit = temp;
		}
		else if(modo == CELSIUS){
			this.celsius = ( temp - 32 ) / 1.8; 
		}
		else if(modo == FAHRENHEIT){
			this.fahrenheit = temp;
		}
		
	}
	
	public double getCelsius(){
		return celsius;
	}
	
	public double getFahrenheit(){
		return fahrenheit;
	}

}
