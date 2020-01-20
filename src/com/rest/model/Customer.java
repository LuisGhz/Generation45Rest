package com.rest.model;

public class Customer {
	private int idCustomer;
	private String nameCustomer;
	private String addressCustomer;
	private byte ageCustomer;
	private double heightCustomer;
	private double weightCustomer;
	private boolean isSingle;
	
	public Customer() {}
	
	public Customer(int idCustomer, String nameCustomer, String addressCustomer, byte ageCustomer, double heightCustomer,
			double weightCustomer, boolean isSingle) {
		super();
		this.idCustomer = idCustomer;
		this.nameCustomer = nameCustomer;
		this.addressCustomer = addressCustomer;
		this.ageCustomer = ageCustomer;
		this.heightCustomer = heightCustomer;
		this.weightCustomer = weightCustomer;
		this.isSingle = isSingle;
	}

	public int getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
	}

	public String getNameCustomer() {
		return nameCustomer;
	}

	public void setNameCustomer(String nameCustomer) {
		this.nameCustomer = nameCustomer;
	}

	public String getAddressCustomer() {
		return addressCustomer;
	}

	public void setAddressCustomer(String addressCustomer) {
		this.addressCustomer = addressCustomer;
	}

	public byte getAgeCustomer() {
		return ageCustomer;
	}

	public void setAgeCustomer(byte ageCustomer) {
		this.ageCustomer = ageCustomer;
	}

	public double getHeightCustomer() {
		return heightCustomer;
	}

	public void setHeightCustomer(double heightCustomer) {
		this.heightCustomer = heightCustomer;
	}

	public double getWeightCustomer() {
		return weightCustomer;
	}

	public void setWeightCustomer(double weightCustomer) {
		this.weightCustomer = weightCustomer;
	}

	public boolean getIsSingle() {
		return isSingle;
	}

	public void setIsSingle(boolean isSingle) {
		this.isSingle = isSingle;
	}
	
	
}
