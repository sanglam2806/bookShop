package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entites.Bill;

@Repository
public class BillDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int countItems() {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM bill ", Integer.class);
	}

	public List<Bill> getItems(int offset, int itemInPage) {
		return jdbcTemplate.query(
				"select bill.id,bill.date_create,bill.money,user.username as name_user from bill"
				+ " inner join user on bill.id_user= user.id order by id desc limit ?,?",
				new Object[] { offset, itemInPage }, new BeanPropertyRowMapper<Bill>(Bill.class));
	}

	public int add(int tong, int id) {
		return jdbcTemplate.update("insert into bill(id_user,money) values(?,?)", new Object[] { id, tong });
	}

	public Bill getItems() {
		return jdbcTemplate.queryForObject("select * from bill order by id desc LIMIT 0,1",
				new BeanPropertyRowMapper<Bill>(Bill.class));
	}

	public int delItem(int id) {
		return jdbcTemplate.update("delete from bill where id=?", new Object[] { id });
	}

	public List<Bill> getItem(int id) {
		return jdbcTemplate.query(
				"select bill.id,bill.date_create,bill.money,user.username as name_user from bill "
				+ "inner join user on bill.id_user= user.id where user.id=? order by bill.id ",
				new Object[] { id }, new BeanPropertyRowMapper<Bill>(Bill.class));
	}
}
