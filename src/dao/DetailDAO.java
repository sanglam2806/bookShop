package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entites.Details;

@Repository
public class DetailDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Details> getItems(int id_bill) {
		return jdbcTemplate.query("select id_bill,detail.id_book,detail.quantity,total,user.username as user_name,book.name as book_name from detail inner join user on detail.id_user= user.id inner join book on detail.id_book= book.id_book where id_bill=? ",
				new Object[] { id_bill}, new BeanPropertyRowMapper<Details>(Details.class));
	}

	public int add(int id_book, int id, int i, int tien, int hoaDon) {
		return jdbcTemplate.update("insert into detail(id_bill,id_user,id_book,quantity,total) values(?,?,?,?,?)",new Object[]{hoaDon,id,id_book,i,tien});
	}

	public int delItem(int id) {
		return jdbcTemplate.update("delete from detail where id_bill=?",new Object[]{id});
	}
}
