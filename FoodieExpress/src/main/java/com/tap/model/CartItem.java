package com.tap.model;

public class CartItem {

    private int itemId;        // menuId
    private String itemName;
    private double price;
    private int quantity;
    private String imagePath;  // ✅ ADD THIS

    public CartItem(int itemId, String itemName, double price, int quantity, String imagePath) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.imagePath = imagePath;
    }

    public int getItemId() { return itemId; }
    public String getItemName() { return itemName; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public String getImagePath() { return imagePath; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
}

	
	
	


