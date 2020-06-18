package dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entites.Customer;

@Repository
public class CustomerDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int add(Customer objItem) {
		return jdbcTemplate.update("insert into customer(id_bill,id_user,address,city,phone,note) values(?,?,?,?,?,?)",new Object[]{objItem.getId_bill(),objItem.getId_user(),objItem.getAddress(),objItem.getCity(),objItem.getPhone(),objItem.getNote()});
	}
}
