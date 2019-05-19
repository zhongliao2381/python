package com.yc.bean;

public class DishesBean {
	private Integer cp_id;
	private String name;
	private Double price;
	private String lable;
	private String pic;
	private String style;
	private String temp1;
	private String temp2;
	
	
	public String getStyle() {
		return style;
	}
	public String getTemp1() {
		return temp1;
	}
	public String getTemp2() {
		return temp2;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public void setTemp1(String temp1) {
		this.temp1 = temp1;
	}
	public void setTemp2(String temp2) {
		this.temp2 = temp2;
	}
	public Integer getCp_id() {
		return cp_id;
	}
	public String getName() {
		return name;
	}
	public Double getPrice() {
		return price;
	}
	public String getLable() {
		return lable;
	}
	public String getPic() {
		return pic;
	}
	public void setCp_id(Integer cp_id) {
		this.cp_id = cp_id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public void setLable(String lable) {
		this.lable = lable;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public DishesBean(Integer cp_id, String name, Double price, String lable, String pic) {
		super();
		this.cp_id = cp_id;
		this.name = name;
		this.price = price;
		this.lable = lable;
		this.pic = pic;
	}
	public DishesBean() {
		super();
	}
	
	
}
