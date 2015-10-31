package com.caflores.sales_manager.dao;

import com.caflores.sales_manager.model.Customer;

public interface CustomerDao {
	Customer findCustomer(long customerId);
}
