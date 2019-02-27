package com.zjgsu.shuidiansys.pojo;

public class UserCategory {
	
	private int userCategoryId;
	
	private String userCategoryname;
	
	private double waterPrice;
	
	private double electricityPrice;
	
	private int waterFreeQuata;
	
	private int electricityFreeQuata;
	
	private double taxWaterPrice;
	
	private double taxElectricityPrice;

	public int getUserCategoryId() {
		return userCategoryId;
	}

	public void setUserCategoryId(int userCategoryId) {
		this.userCategoryId = userCategoryId;
	}

	public String getUserCategoryname() {
		return userCategoryname;
	}

	public void setUserCategoryname(String userCategoryname) {
		this.userCategoryname = userCategoryname;
	}

	public double getWaterPrice() {
		return waterPrice;
	}

	public void setWaterPrice(double waterPrice) {
		this.waterPrice = waterPrice;
	}

	public double getElectricityPrice() {
		return electricityPrice;
	}

	public void setElectricityPrice(double electricityPrice) {
		this.electricityPrice = electricityPrice;
	}

	public int getWaterFreeQuata() {
		return waterFreeQuata;
	}

	public void setWaterFreeQuata(int waterFreeQuata) {
		this.waterFreeQuata = waterFreeQuata;
	}

	public int getElectricityFreeQuata() {
		return electricityFreeQuata;
	}

	public void setElectricityFreeQuata(int electricityFreeQuata) {
		this.electricityFreeQuata = electricityFreeQuata;
	}

	public double getTaxWaterPrice() {
		return taxWaterPrice;
	}

	public void setTaxWaterPrice(double taxWaterPrice) {
		this.taxWaterPrice = taxWaterPrice;
	}

	public double getTaxElectricityPrice() {
		return taxElectricityPrice;
	}

	public void setTaxElectricityPrice(double taxElectricityPrice) {
		this.taxElectricityPrice = taxElectricityPrice;
	}
	
	
}
