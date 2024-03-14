package model;

	public class Product {
		private String name;
		private float purchasePrice;
		private float salesPrice;
		private float rentPrice;
		private String countryOfOrigin;
		private int minStock;
		private int barcode;
		
	public Product(String name, float purchasePrice, float salesPrice, float rentPrice, String countryOfOrigin, int minStock, int barcode) {
		this.setName(name);
		this.setPurchasePrice(purchasePrice);
		this.setSalesPrice(salesPrice);
		this.setRentPrice(rentPrice);
		this.setCountryOfOrigin(countryOfOrigin);
		this.setMinStock(minStock);
		this.setBarcode(barcode);
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

	public int getBarcode() {
		return barcode;
	}

	public void setBarcode(int barcode) {
		this.barcode = barcode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (barcode != other.barcode)
			return false;
		if (countryOfOrigin == null) {
			if (other.countryOfOrigin != null)
				return false;
		} else if (!countryOfOrigin.equals(other.countryOfOrigin))
			return false;
		if (minStock != other.minStock)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Float.floatToIntBits(purchasePrice) != Float.floatToIntBits(other.purchasePrice))
			return false;
		if (Float.floatToIntBits(rentPrice) != Float.floatToIntBits(other.rentPrice))
			return false;
		if (Float.floatToIntBits(salesPrice) != Float.floatToIntBits(other.salesPrice))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", purchasePrice=" + purchasePrice + ", salesPrice=" + salesPrice
				+ ", rentPrice=" + rentPrice + ", countryOfOrigin=" + countryOfOrigin + ", minStock=" + minStock
				+ ", barcode=" + barcode + "]";
	}
}
