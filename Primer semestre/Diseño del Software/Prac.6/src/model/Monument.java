package model;

public class Monument{

	private String name;
	private String author;
	private String address;

	public Monument(String name, String author, String address) {
		this.name = name;
		this.author = author;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}
	
	public String getAuthor() {
		return author;
	}

}
