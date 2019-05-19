package com.yc.bean;

public class OrderBean {
	private String dd_id;
	private String cp_id;
	private String name;
	private String tel;
	private Integer table_number;
	private Double totalPrice;
	private String order_time;
	public String getDd_id() {
		return dd_id;
	}
	public String getCp_id() {
		return cp_id;
	}
	public String getName() {
		return name;
	}
	public String getTel() {
		return tel;
	}
	public Integer getTable_number() {
		return table_number;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public String getOrder_time() {
		return order_time;
	}
	public void setDd_id(String dd_id) {
		this.dd_id = dd_id;
	}
	public void setCp_id(String cp_id) {
		this.cp_id = cp_id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public void setTable_number(Integer table_number) {
		this.table_number = table_number;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public void setOrder_time(String order_time) {
		this.order_time = order_time;
	}
	public OrderBean(String dd_id, String cp_id, String name, String tel, Integer table_number, Double totalPrice,
			String order_time) {
		super();
		this.dd_id = dd_id;
		this.cp_id = cp_id;
		this.name = name;
		this.tel = tel;
		this.table_number = table_number;
		this.totalPrice = totalPrice;
		this.order_time = order_time;
	}
	public OrderBean() {
		super();
	}
	
	
}
