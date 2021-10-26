package sk.upjs.paz1c2021.prezencka;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class MysqlStudentDao implements StudentDao {
	
	private JdbcTemplate jdbcTemplate;

	public MysqlStudentDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<Student> getAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT id, name, surname, subject_id FROM student";
		return jdbcTemplate.query(sql, new StudentRowMapper());
	}

	@Override
	public Student save(Student student) throws EntityNotFoundException {
		if(student.getId() == null) {
			SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
			insert.withTableName("student");
			insert.usingGeneratedKeyColumns("id");
			insert.usingColumns("name", "surname","subject_id");
			Map<String, Object> values = new HashMap<String, Object>();
			values.put("name", student.getName());
			values.put("surname", student.getSurname());
			System.out.println("subjectid: " + student.getSubjectId());
			values.put("subject_id", student.getSubjectId());
			try {
				long v = insert.executeAndReturnKey(values).longValue();
				System.out.println(student + " : " + v);
				return new Student(v, student.getName(), student.getSurname(), student.getSubjectId());
			} catch (DataIntegrityViolationException e) {
				e.printStackTrace();
				throw new EntityNotFoundException("Cannot insert student, subject with id " + student.getId() + " (probably a subject with such id does not exist in the system?)", e);
			}
		} else {
			String sql = "UPDATE student SET name = ?, surname = ?, subject_id = ? WHERE id = ?";
			int changed = jdbcTemplate.update(sql, student.getName(), student.getSurname(), student.getSubjectId(), student.getId());
			if(changed == 1) {
				return student;
			} else {
				throw new EntityNotFoundException("Student with id " + student.getId() + " not found in DB.");
			}
		}
	}

	@Override
	public Student delete(long idStudent) {
		// TODO Auto-generated method stub
		Student byIdStudent = getById(idStudent);
		String sql = "DELETE FROM student WHERE id = ?";
	
		try {
			jdbcTemplate.update(sql, byIdStudent.getId());
		}
		catch (DataIntegrityViolationException e) {
			throw new EntityUndeletableException("This student is a part of attendance, thus cannot be deleted.");
		}
		
		return byIdStudent;
		
	}

	@Override
	public Student getById(long studentId) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM student WHERE id = ?";
		try {
			return jdbcTemplate.queryForObject(sql, new StudentRowMapper(), studentId);
		} catch(EmptyResultDataAccessException e) {
			throw new EntityNotFoundException("Student with id " + studentId + " not found in DB!");
		}
	}
	
	private class StudentRowMapper implements RowMapper<Student> {

		@Override
		public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			long id = rs.getLong("id");
			String name = rs.getString("name");
			String surname = rs.getString("surname");
			long subjectId = rs.getLong("subject_id");
			return new Student(id, name, surname, subjectId);
		}
		
	}

	@Override
	public List<Student> getBySubjectId(long subjectId) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM student WHERE subject_id = ?";
		return jdbcTemplate.query(sql, new StudentRowMapper(), subjectId);
	}

}
