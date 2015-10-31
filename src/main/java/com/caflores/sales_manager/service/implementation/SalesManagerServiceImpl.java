package com.caflores.sales_manager.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caflores.sales_manager.dao.BillDao;
import com.caflores.sales_manager.dao.CustomerDao;
import com.caflores.sales_manager.dao.ProductDao;
import com.caflores.sales_manager.model.Bill;
import com.caflores.sales_manager.model.Customer;
import com.caflores.sales_manager.model.Product;
import com.caflores.sales_manager.service.SalesManagerService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service("salesManagerService")
public class SalesManagerServiceImpl implements SalesManagerService{
	
	@Autowired
	BillDao billDao;
	
	@Autowired
	CustomerDao customerDao;
	
	@Autowired
	ProductDao productDao;

	private Gson gson = new GsonBuilder().disableHtmlEscaping().create();

	public boolean addBill(String params) {
		Map parameters = gson.fromJson(params, Map.class);
		long userId = 0;
		System.out.println(parameters.get("id_user"));
		userId = Long.parseLong(""+parameters.get("id_user"));
		List<Long> productsId = null;
		productsId = (List) parameters.get("products");
		if(productsId == null) return false;
		if(userId == 0) return false;
		Bill bill = new Bill();
		bill.setCustomerId(userId);
		bill.setProductsId(productsId);
		long id_bill = billDao.addBill(bill);
		bill.setId(id_bill);
		billDao.addBillDetails(bill);
		return true;
	}

	public List<Product> getProducts(long userId) {
		Customer customer = customerDao.findCustomer(userId);
		List<Bill> bills = billDao.billsByCustomer(customer.getId());
		ArrayList<Long> billsId = getBillsId(bills);
		List<Product> products = productDao.productsByBills(billsId);
		return products;
	}
	
	private ArrayList<Long> getBillsId(List<Bill> bills){
		ArrayList<Long> billsId = new ArrayList<Long>();
		for(Bill bill : bills){
			billsId.add(bill.getId());
		}
		return billsId;
	}

}
