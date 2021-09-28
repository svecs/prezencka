package sk.upjs.paz1c2021.prezencka;

import java.io.File;
import java.util.List;

public class Subject {
	private String name;
	private int studyYear;
	private List<Student> students;
	private List<Attendance> attendanceList;
	
	public void addStudent(Student student) {
		throw new UnsupportedOperationException();
	}
	
	public void importStudents(File csvFile) {
		
	}
	
}
