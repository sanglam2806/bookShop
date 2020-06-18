package entites;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.NotEmpty;

public class News {
	private int id_news;
	@NotEmpty (message="Nhập tên tin tức")
	private String name;
	@NotEmpty (message="Nhập mô tả tin")
	private String preview_text;
	@NotEmpty (message="Nhập chi tiết tin")
	private String detail_text;
	private int id_cat;
	private String picture;
	private Timestamp date_create;
	private String nameCAT;
	
	public String getNameCAT() {
		return nameCAT;
	}
	public void setNameCAT(String nameCAT) {
		this.nameCAT = nameCAT;
	}
	public int getId_news() {
		return id_news;
	}
	public void setId_news(int id_news) {
		this.id_news = id_news;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPreview_text() {
		return preview_text;
	}
	public void setPreview_text(String preview_text) {
		this.preview_text = preview_text;
	}
	public String getDetail_text() {
		return detail_text;
	}
	public void setDetail_text(String detail_text) {
		this.detail_text = detail_text;
	}
	public int getId_cat() {
		return id_cat;
	}
	public void setId_cat(int id_cat) {
		this.id_cat = id_cat;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Timestamp getDate_create() {
		return date_create;
	}
	public void setDate_create(Timestamp date_create) {
		this.date_create = date_create;
	}
	
}
