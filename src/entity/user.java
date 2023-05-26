package entity;

public class user {
	private int Id  ; 
	private String name ; 
	private String lastName ;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return "user [Id=" + Id + ", name=" + name + ", lastName=" + lastName + "]";
	}
	public user(int id, String name, String lastName) {
		super();
		Id = id;
		this.name = name;
		this.lastName = lastName;
	} 
	
}
