package entites;


import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class Category {
	private int id;
	@NotEmpty(message="Không được để trống")
	@Size(min=8,message="Ít nhât 8 ký tự")
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
