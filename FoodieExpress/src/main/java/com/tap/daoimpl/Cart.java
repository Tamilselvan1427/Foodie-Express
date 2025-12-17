package com.tap.daoimpl;

import java.util.LinkedHashMap;
import java.util.Map;

import com.tap.dao.CartDAO;
import com.tap.model.CartItem;

public class Cart implements CartDAO {
	
	Map<Integer, CartItem>cart = new LinkedHashMap<>();
	
	@Override
	public void addCartItem(CartItem cartItem) {
		int itemId = cartItem.getItemId();
		if(cart.containsKey(itemId)) {
			CartItem existingItem = cart.get(itemId);
			existingItem.setQuantity(existingItem.getQuantity()+cartItem.getQuantity());
		}
		else {
			cart.put(itemId, cartItem);
		}
	}
	
	@Override
	public void updateCartItem(int itemId, int quantity) {
		
		
		if(cart.containsKey(itemId)) {
			if(quantity<=0) {
			  cart.remove(itemId);
			}
			
			else {
				
				cart.get(itemId).setQuantity(quantity);
				
			}
			
					
		}
		
		
	}
	
	@Override
	public void removeCartItem(int itemId) {
		cart.remove(itemId);
		
	}
	
	
	public Map<Integer, CartItem> getCart() {
	    return cart;
	}
	
	public void clearAllItems() {
		cart.clear();
	}
	

}
