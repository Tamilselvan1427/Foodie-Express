package com.tap.dao;

import com.tap.model.CartItem;

public interface CartDAO {

	void addCartItem(CartItem cartItem);

	void updateCartItem(int itemId, int quantity);

	void removeCartItem(int itemId);

}
