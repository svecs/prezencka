package sk.upjs.paz1c2021.prezencka;

import java.util.List;

public interface AttendanceDao {
	List<Attendance> getbySubject(Subject subject);
	
	Attendance save(Attendance attendance) throws EntityNotFoundException, NullPointerException;
	
	Attendance delete(long id);
}
