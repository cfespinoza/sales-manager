package com.caflores.sales_manager.model;

import java.util.List;

public class Bill {
	private long id;
	private long customerId;
	private List<Long> productsId;
	private double total;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public List<Long> getProductsId() {
		return productsId;
	}
	public void setProductsId(List<Long> productsId) {
		this.productsId = productsId;
	}
}
