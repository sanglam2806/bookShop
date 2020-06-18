package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entites.Book;

@Repository
public class BookDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int countItems() {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM book ", Integer.class);
	}

	public List<Book> getItems(int offset, int itemInPage) {
		return jdbcTemplate.query(
				"select book.id_book,book.name,book.quantity,book.sell,book.cost,book.picture,category.name as cat_name from book inner join category on book.id_cat= category.id order by id_book desc limit ?,?",
				new Object[] { offset, itemInPage }, new BeanPropertyRowMapper<Book>(Book.class));
	}

	public List<Book> getNews() {
		return jdbcTemplate.query(
				"select book.id_book,book.name,book.quantity,book.sell,book.cost,book.picture,category.name as cat_name from book inner join category on book.id_cat= category.id order by id_book desc ",
				new BeanPropertyRowMapper<Book>(Book.class));
	}

	public int addItem(Book objItem) {
		return jdbcTemplate.update("insert into book(name,id_cat,quantity,cost,picture) values(?,?,?,?,?)",
				new Object[] { objItem.getName(), objItem.getId_cat(), objItem.getQuantity(), objItem.getCost(),
						objItem.getPicture() });
	}

	public Book getItemID(int id) {
		return jdbcTemplate.queryForObject("select * from book where id_book=?", new Object[] { id },
				new BeanPropertyRowMapper<Book>(Book.class));
	}

	public int delItem(int id) {
		return jdbcTemplate.update("delete from book where id_book=?", new Object[] { id });
	}

	public int editItem(Book objb) {
		return jdbcTemplate.update("update book set name=?,cost=?,quantity=?,id_cat=?,picture=? where id_book=?",
				new Object[] { objb.getName(), objb.getCost(), objb.getQuantity(), objb.getId_cat(), objb.getPicture(),
						objb.getId_book() });
	}

	public List<Book> getSearch(String name) {
		return jdbcTemplate.query(
				"select *from book order by id_book desc "
				, new BeanPropertyRowMapper<Book>(Book.class));
	}

	public List<Book> getSearch(String name, int cost) {
		return jdbcTemplate.query(
				"select book.id_book,book.name,book.quantity,book.sell,book.cost,book.picture,category.name as cat_name from book inner join category on book.id_cat= category.id  where book.name LIKE '%"+name+"%' and (cost > ("+cost+"-10000) and cost < ("+cost+"+10000)) order by id_book desc",
				 new BeanPropertyRowMapper<Book>(Book.class));
	}

	public List<Book> getSearch(String name, int cost, int id_cat) {
		return jdbcTemplate.query(
				"select book.id_book,book.name,book.quantity,book.sell,book.cost,book.picture,category.name as cat_name from book inner join category on book.id_cat= category.id where book.name LIKE '%"+name+"%' and (cost > ("+cost+"-10000) and cost < ("+cost+"+10000)) and book.id_cat=? order by id_book desc",
				new Object[] { id_cat }, new BeanPropertyRowMapper<Book>(Book.class));
	}

	public List<Book> getSearchID(String name, int idcat) {
		return jdbcTemplate.query(
				"select book.id_book,book.name,book.quantity,book.sell,book.cost,book.picture,category.name as cat_name from book inner join category on book.id_cat= category.id where book.name LIKE '%"+name+"%' and book.id_cat=? order by id_book desc",
				new Object[] { idcat }, new BeanPropertyRowMapper<Book>(Book.class));
	}

	public  List<Book> getItemShow() {
		return jdbcTemplate.query(
				"select book.id_book,book.name,book.quantity,book.sell,book.cost,book.picture,category.name as cat_name from book inner join category on book.id_cat= category.id where quantity >0 order by id_book desc limit 0,8 ",
				new BeanPropertyRowMapper<Book>(Book.class));
	}

	public List<Book> getItemSell() {
		return jdbcTemplate.query(
				"select book.id_book,book.name,book.quantity,book.sell,book.cost,book.picture,category.name as cat_name from book inner join category on book.id_cat= category.id where quantity >0 order by sell desc limit 0,4 ",
				new BeanPropertyRowMapper<Book>(Book.class));
	}

	public List<Book> getItemsIdCat(int id) {
		return jdbcTemplate.query(
				"select book.id_book,book.name,book.quantity,book.sell,book.cost,book.picture,category.name as cat_name from book inner join category on book.id_cat= category.id where category.id=? and quantity >0 order by id_book desc  ",
				new Object[] { id },new BeanPropertyRowMapper<Book>(Book.class));
	}

	public int buyItem(int id_book, int buy, int quan) {
		return jdbcTemplate.update("update book set quantity=?,sell=? where id_book=?",
				new Object[] { quan,buy,id_book });
	}

	public int countItems(int id) {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM book where id_book=?", new Object[] { id }, Integer.class);
	}

	public List<Book> getItems(int offset, int itemInPage, int id) {
		return jdbcTemplate.query(
				"select book.id_book,book.name,book.quantity,book.sell,book.cost,book.picture,category.name as cat_name from book inner join category on book.id_cat= category.id where book.id_cat=? order by id_book desc limit ?,?",
				new Object[] { id, offset, itemInPage }, new BeanPropertyRowMapper<Book>(Book.class));
	}
}
