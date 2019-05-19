package com.yc.bean;

public class PackagesBean {
	private String tc_id;
	private String name;
	private Integer count;
	private Double totalPrice;
	private String lable;
	private DishesBean dishes;
	public String getTc_id() {
		return tc_id;
	}
	public String getName() {
		return name;
	}
	public Integer getCount() {
		return count;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public String getLable() {
		return lable;
	}
	public void setTc_id(String tc_id) {
		this.tc_id = tc_id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public void setLable(String lable) {
		this.lable = lable;
	}
	public PackagesBean(String tc_id, String name, Integer count, Double totalPrice, String lable) {
		super();
		this.tc_id = tc_id;
		this.name = name;
		this.count = count;
		this.totalPrice = totalPrice;
		this.lable = lable;
	
	}
	
	public PackagesBean(String tc_id, String name, Integer count, Double totalPrice, String lable, DishesBean dishes) {
		super();
		this.tc_id = tc_id;
		this.name = name;
		this.count = count;
		this.totalPrice = totalPrice;
		this.lable = lable;
		this.dishes = dishes;
	}
	public PackagesBean() {
		super();
	}
	public DishesBean getDishes() {
		return dishes;
	}
	public void setDishes(DishesBean dishes) {
		this.dishes = dishes;
	}
	
	
}
