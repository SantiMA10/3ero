public class Main {

	public static void main(String[] args) {

		Customer raul = new Customer("Raúl");
		
		Movie hotFuzz = new MovieNewRelase("Hot Fuzz");
		Movie toyStory = new MovieChildrens("Toy Story");
		Movie zombiesParty = new MovieRegular("Zombies Party");

		raul.addRental(new Rental(hotFuzz, 2));
		raul.addRental(new Rental(toyStory, 6));
		raul.addRental(new Rental(zombiesParty, 8));

		System.out.println(raul.status());

	}
}
