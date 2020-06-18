package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
// khai báo class thực hiện model dao

import entites.User;
@Repository
public class UserDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public  List<User> getItems(){
		return jdbcTemplate.query("select user.id,user.username,user.fullname,user.active,user.role,rolluser.name as role_name from user inner join rolluser on user.role=rolluser.id order by id desc", new BeanPropertyRowMapper<User>(User.class));
	}
	public int addItem(User objItem) {
		return jdbcTemplate.update("insert into user(username,fullname,password,role) values(?,?,?,?)", new Object[]{objItem.getUsername(),objItem.getFullname(),objItem.getPassword(),objItem.getRole()});
	}
	public User getItem(int id) {
		return jdbcTemplate.queryForObject("select * from user where id=?", new Object[]{id},new BeanPropertyRowMapper<User>(User.class));
	}
	public int editItem(User obju) {
		return jdbcTemplate.update("update user set fullname=?, password=? where id=?",new Object[]{obju.getFullname(),obju.getPassword(),obju.getId()});
	}
	public int delItem(int id) {
		return jdbcTemplate.update("delete from user where id=? ", new Object[]{id});
	}
	public int countItems() {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM user ", Integer.class);
	}
	public List<User> getItems(int offset, int itemInPage) {
		return jdbcTemplate.query("select user.id,user.username,user.fullname,user.active,user.role,rolluser.name as role_name from user inner join rolluser on user.role=rolluser.id order by id desc limit ?,?",
				new Object[] { offset, itemInPage }, new BeanPropertyRowMapper<User>(User.class));
	}
	public User getItem(String name) {
		return jdbcTemplate.queryForObject("select * from user where username=?", new Object[]{name},new BeanPropertyRowMapper<User>(User.class));
	}
	public int setActive(int aid, int aactive){
		return jdbcTemplate.update("update user set active=? where id=?",new Object[]{aactive,aid});
	}
}
