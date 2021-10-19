package sk.upjs.paz1c2021.prezencka;

public class Student {
	private long id;
	private String name;
	private String surname;
	
	public Student(long id, String name, String surname) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", surname=" + surname + "]";
	}
	public Student(String name, String surname) {
		super();
		this.name = name;
		this.surname = surname;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
}
