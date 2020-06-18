package entites;

import org.hibernate.validator.constraints.NotEmpty;

public class RollUser {
	private int id;
	@NotEmpty(message="Không được để trống")
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
