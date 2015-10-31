package com.caflores.sales_manager.dao;

import java.util.List;

import com.caflores.sales_manager.model.Product;

public interface ProductDao {
	List<Product> productsByBills(List<Long> billsId);
}
