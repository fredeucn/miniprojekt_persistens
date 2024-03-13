package model;

	public class Product {
		private String name;
		private float purchasePrice;
		private float salesPrice;
		private float rentPrice;
		private String countryOfOrigin;
		private int minStock;
		private int id;
		
	public Product(String name, float purchasePrice, float salesPrice, float rentPrice, String countryOfOrigin, int minStock, int id) {
		this.setName(name);
		this.setPurchasePrice(purchasePrice);
		this.setSalesPrice(salesPrice);
		this.setRentPrice(rentPrice);
		this.setCountryOfOrigin(countryOfOrigin);
		this.setMinStock(minStock);
		this.setId(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(float purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public float getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(float salesPrice) {
		this.salesPrice = salesPrice;
	}

	public float getRentPrice() {
		return rentPrice;
	}

	public void setRentPrice(float rentPrice) {
		this.rentPrice = rentPrice;
	}

	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}

	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}

	public int getMinStock() {
		return minStock;
	}

	public void setMinStock(int minStock) {
		this.minStock = minStock;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
