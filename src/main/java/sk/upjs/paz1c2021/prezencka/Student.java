package sk.upjs.paz1c2021.prezencka;

public class Student {
	private Long id;
	private String name;
	private String surname;
	private Long subjectId;
	
	public Long getSubjectId() {
		return subjectId;
	}
	public void setSubject_id(Long subjectId) {
		this.subjectId = subjectId;
	}
	public Student(long id, String name, String surname, Long subjectId) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.subjectId = subjectId;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", surname=" + surname + "]";
	}
	public Student(String name, String surname, Long subjectId) {
		super();
		this.name = name;
		this.surname = surname;
		this.subjectId = subjectId;

	}
	
	public Long getId() {
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
