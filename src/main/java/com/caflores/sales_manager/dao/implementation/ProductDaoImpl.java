package com.caflores.sales_manager.dao.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.caflores.sales_manager.dao.ProductDao;
import com.caflores.sales_manager.model.Product;

@Repository("productDao")
public class ProductDaoImpl implements ProductDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<Product> productsByBills(List<Long> billsId) {
		List<Product> products = null;
		String idString = getIdsString(billsId);
		String query = "SELECT id, name, price "
				+ " FROM t_products "
				+ " WHERE id IN ("+idString+")";
//		Set<Long> ids = new HashSet<Long>(billsId);
//		MapSqlParameterSource parameters = new MapSqlParameterSource();
//		parameters.addValue("ids", ids);
//		products = jdbcTemplate.query(query, new ProductMapper(), Collections.singletonMap("ids", billsId));
		products = jdbcTemplate.query(query, new ProductMapper());
		return products;
	}
	
	private String getIdsString(List<Long> billsId) {
		String ids = "";
		for(Long id : billsId){
			ids += id + ",";
		}
		ids = ids.substring(0, ids.length()-1);
		return ids;
	}

	private static final class ProductMapper implements RowMapper<Product> {
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product product = new Product();
			product.setId(rs.getLong("id"));
			product.setName(rs.getString("name"));
			product.setPrice(rs.getDouble("price"));
			return product;
		}
	}
	
}
