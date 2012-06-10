package com.example.wineanddine;

public class Alcohol {
	static String alcoholName;
	String alcoholDepartment;
	String alcoholCountry;
	Integer alcoholRating;
	
	public static String getName() {
		return alcoholName;
	}
	public void setName(String name) {
		this.alcoholName = name;
	}
	public String getDepartment() {
		return alcoholDepartment;
	}
	public void setDepartment(String department) {
		this.alcoholDepartment = department;
	}
	public String getCountry() {
		return alcoholCountry;
	}
	public void setCountry(String country) {
		this.alcoholCountry = country;
	}
	public Integer getRating() {
		return alcoholRating;
	}
	public void setRating(Integer rating) {
		this.alcoholRating = rating;
	}
	
}
