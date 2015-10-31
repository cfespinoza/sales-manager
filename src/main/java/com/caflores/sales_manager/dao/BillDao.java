package com.caflores.sales_manager.dao;

import java.util.List;

import com.caflores.sales_manager.model.Bill;

public interface BillDao {
//	Bill getBill(long id);
//	List<Bill> getBills();
	long addBill(Bill bill);
	void addBillDetails(Bill bill);
	List<Bill> billsByCustomer(long customerId);
}
