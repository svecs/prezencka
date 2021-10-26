package sk.upjs.paz1c2021.prezencka;

import java.util.Objects;

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
		setSurname(surname);
		this.subjectId = subjectId;
	}
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", surname=" + surname + ", subjectId=" + subjectId + "]";
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
	@Override
	public int hashCode() {
		return Objects.hash(id, name, subjectId, surname);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(subjectId, other.subjectId) && Objects.equals(surname, other.surname);
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
	public void setSurname(String surname) throws NullPointerException {
		if(surname == null) throw new NullPointerException("Surname cannot be null");
		this.surname = surname;
	}
	
}
