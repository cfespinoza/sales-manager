package com.caflores.sales_manager.dao.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.caflores.sales_manager.dao.BillDao;
import com.caflores.sales_manager.model.Bill;

@Repository("billDao")
public class BillDaoImpl implements BillDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@SuppressWarnings("deprecation")
	public long addBill(Bill bill) {
		String query = "INSERT INTO t_bills (customer_id) VALUES(?) RETURNING id";
		long billId = jdbcTemplate.queryForLong(query, bill.getCustomerId());
		return billId;
	}

	@SuppressWarnings("deprecation")
	public void addBillDetails(Bill bill) {
		String query = "INSERT INTO t_details (bill_id, product_id) VALUES(?, ?) RETURNING id";
		for(Object productId : bill.getProductsId()){
			jdbcTemplate.queryForLong(query, bill.getId(), Long.parseLong(""+productId));
		}
	}

	public List<Bill> billsByCustomer(long customerId) {
		List<Bill> bills = null;
		String query = "SELECT id, customer_id "
				+ " FROM t_bills "
				+ " WHERE customer_id = "+customerId;
		bills = jdbcTemplate.query(query, new BillMapper());
		return bills;
	}
	
	private static final class BillMapper implements RowMapper<Bill> {
		public Bill mapRow(ResultSet rs, int rowNum) throws SQLException {
			Bill bill = new Bill();
			bill.setId(rs.getLong("id"));
			bill.setCustomerId(rs.getLong("customer_id"));
			return bill;
		}
	}

}
