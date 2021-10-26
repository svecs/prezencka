package sk.upjs.paz1c2021.prezencka;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Attendance {
	public Long getId() {
		return id;
	}
	
	public Subject getSubject() {
		return subject;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getWhen() {
		return when;
	}

	public void setWhen(LocalDateTime when) {
		this.when = when;
	}

	public List<Student> getPresentStudents() {
		return presentStudents;
	}

	public void setPresentStudents(List<Student> presentStudents) {
		this.presentStudents = presentStudents;
	}

	public Attendance(long id, LocalDateTime when, Subject subject, List<Student> presentStudents) {
		super();
		this.id = id;
		this.when = when;
		this.presentStudents = presentStudents;
		this.subject = subject;
	}
	
	public Attendance(long id, LocalDateTime when, Subject subject) {
		super();
		this.id = id;
		this.when = when;
		this.presentStudents = new ArrayList<Student>();
		this.subject = subject;
	}

	private Long id;
	private LocalDateTime when;
	private Subject subject;
	
	private List<Student> presentStudents;
	
	
}
