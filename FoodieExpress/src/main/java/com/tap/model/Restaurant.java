package com.tap.model;

import java.util.Scanner;

public class Restaurant {
	private int restaurantId;
	private String restaurantName;
	private String address;
	private String phone;
	private double rating;
	private String cuisineType;
	private int eta;
	private int adminUserId;
	private String imagePath;
	private boolean isActive;

	public Restaurant() {
	}

	public Restaurant(int restaurantId, String restaurantName, String address, String phone, double rating,
			String cuisineType, int eta, int adminUserId, String imagePath, boolean isActive) {
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.address = address;
		this.phone = phone;
		this.rating = rating;
		this.cuisineType = cuisineType;
		this.eta = eta;
		this.adminUserId = adminUserId;
		this.imagePath = imagePath;
		this.isActive = isActive;
	}

	// Getters & Setters
	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getCuisineType() {
		return cuisineType;
	}

	public void setCuisineType(String cuisineType) {
		this.cuisineType = cuisineType;
	}

	public int getEta() {
		return eta;
	}

	public void setEta(int eta) {
		this.eta = eta;
	}

	public int getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(int adminUserId) {
		this.adminUserId = adminUserId;

	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	@Override
	public String toString() {
		return "RestaurantServlet [restaurantId=" + restaurantId + ", restaurantName=" + restaurantName + ", address="
				+ address + ", phone=" + phone + ", rating=" + rating + ", cuisineType=" + cuisineType + ", eta=" + eta
				+ ", adminUserId=" + adminUserId + ", imagePath=" + imagePath + ", isActive=" + isActive + "]";
	}

	public static Restaurant getRestaurantDetails() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the RestaurantId:");
		int restaurantId = scan.nextInt();
		scan.nextLine();

		System.out.println("Enter the RestaurantName:");
		String restaurantName = scan.nextLine();

		System.out.println("Enter the RestaurantServlet Address:");
		String address = scan.nextLine();

		System.out.println("Enter the RestaurantServlet phone no:");
		String phone = scan.nextLine();
		System.out.println("Enter the RestaurantServlet Rating:");

		double rating = scan.nextDouble();
		scan.nextLine();

		System.out.println("Enter the Cuisine Type:");
		String cuisineType = scan.nextLine();

		System.out.println("Enter the eta:");
		int eta = scan.nextInt();

		System.out.println("Enter the Admin UserID:");
		int adminUserId = scan.nextInt();

		System.out.println("Enter the RestaurantServlet image Path:");
		String imagePath = scan.next();

		System.out.println("Enter IsActive:");
		boolean isActive = scan.nextBoolean();

		Restaurant restaurant = new Restaurant(restaurantId, restaurantName, address, phone, rating, cuisineType, eta,
				adminUserId, imagePath, isActive);

		return restaurant;

	}
}
