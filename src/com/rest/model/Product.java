package com.rest.model;

public class Product {
	private int idProduct;
	private String product;
	private String productName;
	private double productPrice;
	
	public Product() {}
	
	public Product(int idProduct, String product, String productName, double productPrice)
	{
		this.idProduct = idProduct;
		this.product = product;
		this.productName = productName;
		this.productPrice = productPrice;
	}
	
	public int getIdProduct()
	{
		return idProduct;
	}
	
	public void setIdProduct(int idProduct)
	{
		this.idProduct = idProduct;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	
	@Override
	public String toString()
	{
		return String.format("idProduct: %s, Product: %s, productName: %s, productPrice: %f", idProduct, product, productName, productPrice);
	}
}
