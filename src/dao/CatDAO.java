package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
// khai báo class thực hiện model dao

import entites.Category;
@Repository
public class CatDAO {
		// tự động thêm dữ liệu vào
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public  List<Category> getItems(){
		return jdbcTemplate.query("select * from category", new BeanPropertyRowMapper<Category>(Category.class));
	}
	public Category getItems(int id){
		return jdbcTemplate.queryForObject("select * from category where id = ?", new Object[]{id}, new BeanPropertyRowMapper<Category>(Category.class));
	}
	public int addItem(Category objItem){
		return jdbcTemplate.update("insert into category(name) values(?)",new Object[]{objItem.getName()});
	}
	public int editItem(int id,Category objc){
		return jdbcTemplate.update("update category set name=? WHERE id=? limit 1",new Object[]{objc.getName(),id});
	}
	public int delItem(int id) {
		return jdbcTemplate.update("delete from category where id=?",new Object[]{id});
	}
	public int countItems() {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM category ", Integer.class);
	}
	public List<Category> getItems(int offset, int itemInPage) {
		return jdbcTemplate.query("select * from category order by id desc limit ?,?",
				new Object[] { offset, itemInPage }, new BeanPropertyRowMapper<Category>(Category.class));
	}
}

