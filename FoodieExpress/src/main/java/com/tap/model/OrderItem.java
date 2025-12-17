package com.tap.model;

public class OrderItem {

    private int orderItemId;
    private int orderId;
    private int menuId;
    private int quantity;
    private double totalPrice;

    // UI fields
    private String itemName;
    private String imagePath;

    public OrderItem() {}

    public int getOrderItemId() { return orderItemId; }
    public void setOrderItemId(int orderItemId) { this.orderItemId = orderItemId; }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public int getMenuId() { return menuId; }
    public void setMenuId(int menuId) { this.menuId = menuId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public OrderItem(int orderItemId, int orderId, int menuId, int quantity, double totalPrice, String itemName, String imagePath) {
		
		this.orderItemId = orderItemId;
		this.orderId = orderId;
		this.menuId = menuId;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.itemName = itemName;
		this.imagePath = imagePath;
	}
    public OrderItem(int orderId, int menuId, int quantity, double totalPrice) {
    	
    	
    	this.orderId = orderId;
    	this.menuId = menuId;
    	this.quantity = quantity;
    	this.totalPrice = totalPrice;
    	
    	
    }

	

	public OrderItem(int orderId, int menuId, int quantity, double totalPrice, String itemName,
			String imagePath) {
		
		this.orderId = orderId;
		this.menuId = menuId;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.itemName = itemName;
		this.imagePath = imagePath;
	}
	

	@Override
	public String toString() {
		return "OrderItem [orderItemId=" + orderItemId + ", orderId=" + orderId + ", menuId=" + menuId + ", quantity="
				+ quantity + ", totalPrice=" + totalPrice + ", itemName=" + itemName + ", imagePath=" + imagePath + "]";
	}

	public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
}
