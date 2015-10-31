package com.caflores.sales_manager.dao.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.caflores.sales_manager.dao.CustomerDao;
import com.caflores.sales_manager.model.Customer;

@Repository("customerDao")
public class CustomerDaoImpl implements CustomerDao{
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	public Customer findCustomer(long customerId) {
		Customer customer = null;
		String query = "SELECT id, name, dni "
				+ "FROM t_customers "
				+ "WHERE id = ?";
		customer = jdbcTemplate.queryForObject(query, new Object[]{customerId}, new CustomerMapper());
		return customer;
	}
	
	private static final class CustomerMapper implements RowMapper<Customer> {
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Customer customer = new Customer();
			customer.setId(rs.getLong("id"));
			customer.setName(rs.getString("name"));
			customer.setDni(rs.getString("dni"));
			return customer;
		}
	}

}
