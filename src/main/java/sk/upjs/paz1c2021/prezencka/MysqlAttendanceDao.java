package sk.upjs.paz1c2021.prezencka;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class MysqlAttendanceDao implements AttendanceDao {
	
	private JdbcTemplate jdbcTemplate;

	public MysqlAttendanceDao(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Attendance> getbySubject(Subject subject) {
		// TODO Auto-generated method stub
		String sql = "SELECT attendance_id, `when`, attendance.subject_id, student_id, name, surname FROM attendance\r\n"
				+ "LEFT JOIN student_attendance sa ON attendance.id = sa.attendance_id\r\n"
				+ "LEFT JOIN student ON sa.student_id = student.id\r\n"
				+ "WHERE attendance.subject_id = 1\r\n"
				+ "ORDER BY attendance_id, surname;";
		jdbcTemplate.query(sql, new ResultSetExtractor<List<Attendance>>() {

			@Override
			public List<Attendance> extractData(ResultSet rs) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				List<Attendance> result = new ArrayList<Attendance>();
				Attendance att = null;
				while(rs.next()) {
					long id = rs.getLong("attendance_id");
					if(att == null || att.getId() != id) {
						LocalDateTime when = rs.getTimestamp("when").toLocalDateTime();
						att = new Attendance(id, when, subject);
						result.add(att);
					}
					long studentId = rs.getLong("student_id");
					String studentName = rs.getString("name");
					String studentSurname = rs.getString("surname");
					Student student = new Student(studentId, studentName, studentSurname, subject.getId());
					att.getPresentStudents().add(student);
				}
				return result;
			}
			
		});
		return null;
	}

	@Override
	public Attendance save(Attendance attendance) throws EntityNotFoundException, NullPointerException {
		// TODO Auto-generated method stub
		if(attendance.getId() == null) { //INSERT
			SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
			insert.withTableName("student");
			insert.usingGeneratedKeyColumns("id");
			insert.usingColumns("when", "subject_id");
			
			Map<String, Object> values = new HashMap<String, Object>();
			values.put("when", attendance.getWhen());
			values.put("subject_id", attendance.getSubject().getId());
			
			long v = insert.executeAndReturnKey(values).longValue();
			Attendance savedAttendance = new Attendance(v,attendance.getWhen(), attendance.getSubject(), attendance.getPresentStudents());
			
			insertStudents(savedAttendance.getPresentStudents(), savedAttendance.getId());
			return savedAttendance;
			
			/*try {
				
				
				
			} catch (DataIntegrityViolationException e) {
				e.printStackTrace();
				//throw new EntityNotFoundException("Cannot insert student, subject with id " + student.getId() + " (probably a subject with such id does not exist in the system?)", e);
				//throw new DataIntegrityViolationException();
			}*/
		} else { //UPDATE
			
		}
		return null;
	}

	
	private void insertStudents(List<Student> students, long attendanceId) {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO student_attendance (attendance_id, student_id) VALUES ");
		for(Student student: students) {
			sb.append("(").append(attendanceId).append(",").append(student.getId()).append("),");
		}
		String sql = sb.substring(0, sb.length() - 1);
	}
	
	@Override
	public Attendance delete(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
