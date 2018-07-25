package com.mitrais.bootcamp.model;

public class Book {
	private long id = 0;
	private String title = "";
	private String author = "";
	private int stock = 0;
	private boolean available = false;
	
	public Book(){}
	
	public Book(long newId, String newAuthor, String newTitle, int newStock){
		id = newId;
		author = newAuthor;
		title = newTitle;
		stock = newStock;
		available = newStock>0?true:false;
	}

	public Book(String newTitle, String newAuthor, int newStock) {
		title = newTitle;
		author = newAuthor;
		stock = newStock;
		available = newStock>0?true:false;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
}
