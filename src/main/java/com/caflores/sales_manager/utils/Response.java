package com.caflores.sales_manager.utils;

import java.util.List;

import com.caflores.sales_manager.model.Product;

public class Response {
	private int status;
	private String message;
	private List<Product> data;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Product> getData() {
		return data;
	}
	public void setData(List<Product> data) {
		this.data = data;
	}
}
