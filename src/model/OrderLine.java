package model;

public class OrderLine {
	private int amount;
	private Product product;
	
public OrderLine(int amount, Product product) {
	this.setAmount(amount);
	this.setProduct(product);
}

public int getAmount() {
	return amount;
}

public void setAmount(int amount) {
	this.amount = amount;
}

public Product getProduct() {
	return product;
}

public void setProduct(Product product) {
	this.product = product;
}	
}
