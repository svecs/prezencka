package sk.upjs.paz1c2021.prezencka;

import java.util.List;

public interface StudentDao {
	
	List<Student> getAll();
	
	Student getById(long studentId);
	
	List<Student> getBySubjectId(long subjectId);
	
	Student save(Student student) throws EntityNotFoundException;

	Student delete(long idStudent) throws EntityNotFoundException;
	
}
