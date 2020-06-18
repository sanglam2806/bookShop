package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entites.RollUser;

@Repository
public class RollUserDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public  List<RollUser> getItems(){
		return jdbcTemplate.query("select * from rolluser ", new BeanPropertyRowMapper<RollUser>(RollUser.class));
	}
	public int countItems() {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM rolluser ", Integer.class);
	}
	public Object getItems(int offset, int itemInPage) {
		return jdbcTemplate.query("select * from rolluser order by id desc limit ?,?",
				new Object[] { offset, itemInPage }, new BeanPropertyRowMapper<RollUser>(RollUser.class));
	}
	public int addItem(RollUser objItem) {
		return jdbcTemplate.update("insert into rolluser(name) values(?)",new Object[]{objItem.getName()});
	}
	public RollUser getItems(int id) {
		return jdbcTemplate.queryForObject("select * from rolluser where id = ?", new Object[]{id}, new BeanPropertyRowMapper<RollUser>(RollUser.class));
	}
	public int editItem(int id, RollUser objcat) {
		return jdbcTemplate.update("update rolluser set name=? WHERE id=? limit 1",new Object[]{objcat.getName(),id});
	}
	public int delItem(int id) {
		return jdbcTemplate.update("delete from rolluser where id=?",new Object[]{id});
	}
}
