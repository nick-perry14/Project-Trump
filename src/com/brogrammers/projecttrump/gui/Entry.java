package com.brogrammers.projecttrump.gui;

import java.io.Serializable;

public class Entry implements Serializable{
private static final long serialVersionUID = 1L;
private String name;
private String developer;
private byte rating;
private String category;

public Entry(String name, String developer, byte rating, String category) {
	this.name = name;
	this.developer = developer;
	this.rating = rating;
	this.category = category;
}

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDeveloper() {
	return developer;
}
public void setDeveloper(String developer) {
	this.developer = developer;
}
public byte getRating() {
	return rating;
}
public void setRating(byte rating) {
	this.rating = rating;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}


}
