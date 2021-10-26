package sk.upjs.paz1c2021.prezencka;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class MysqlStudentDaoTest {

	private StudentDao studentDao;
	private Student savedStudent;

	public MysqlStudentDaoTest() {
		DaoFactory.INSTANCE.testing();
		studentDao = DaoFactory.INSTANCE.getStudentDao();
	}

	@BeforeEach
	void setUp() throws Exception {
		Student testerStudent = new Student("Csaba", "Torok", 1L);
		savedStudent = studentDao.save(testerStudent);
	}

	@AfterEach
	void tearDown() throws Exception {
		studentDao.delete(savedStudent.getId());
	}

	@Test
	void testGetAll() {
		List<Student> students = studentDao.getAll();
		assertNotNull(students);
		assertTrue(students.size() > 0);
		System.out.println(students);
	}

	@Test
	void testUpdate() {
		Student changedStudent = new Student(savedStudent.getId(), "A", "B", (long) 1);
		Student saveChangedStudent = studentDao.save(changedStudent);
		assertEquals(saveChangedStudent.getName(), "A");
		assertEquals(saveChangedStudent.getSurname(), "B");
		assertEquals(saveChangedStudent.getSubjectId(), 1);
		assertEquals(saveChangedStudent.getId(), changedStudent.getId());

		List<Student> all = studentDao.getAll();
		boolean found = false;
		for (Student s : all) {
			if (s.getId() == changedStudent.getId()) {
				found = true;
				assertEquals(saveChangedStudent.getName(), "A");
				assertEquals(saveChangedStudent.getSurname(), "B");
				assertEquals(saveChangedStudent.getId(), changedStudent.getId());
				break;
			}
		}

		assertTrue(found);
		
		changedStudent.setId(-1L);
		assertThrows(EntityNotFoundException.class, new Executable() {

			@Override
			public void execute() throws Throwable {
				// TODO Auto-generated method stub
				studentDao.save(saveChangedStudent);
			}
			
		});
		
		assertThrows(NullPointerException.class, new Executable() {

			@Override
			public void execute() throws Throwable {
				// TODO Auto-generated method stub
				studentDao.save(null);
			}
			
		});
	}

	@Test
	void testInsert() {
		int initialSize = studentDao.getAll().size();
		Student student = new Student("testovac","insertu",1L);
		Student savedNewStudent = studentDao.save(student);
		assertEquals(student.getName(), savedNewStudent.getName());
		assertEquals(student.getSurname(), savedNewStudent.getSurname());
		assertEquals(student.getSubjectId(), savedNewStudent.getSubjectId());
		assertNotNull(savedNewStudent.getId());
		assertEquals(initialSize + 1, studentDao.getAll().size());
		studentDao.delete(savedNewStudent.getId());
	}
	
	@Test
	void testGetById() {
		Student byIdStudent = studentDao.getById(savedStudent.getId());
		assertEquals(byIdStudent.getName(), savedStudent.getName());
		assertEquals(byIdStudent.getSurname(), savedStudent.getSurname());
		assertEquals(byIdStudent.getSubjectId(), savedStudent.getSubjectId());
		assertEquals(byIdStudent.getId(), savedStudent.getId());
		assertThrows(EntityNotFoundException.class, new Executable() {

			@Override
			public void execute() throws Throwable {
				// TODO Auto-generated method stub
				studentDao.getById(-1L);
			}
			
		});
	}
	
	@Test
	void testDelete() {
		Student studentToDelete = new Student("Tester","OfDelete",1L);
		Student saved = studentDao.save(studentToDelete);
		Student saved2 = studentDao.delete(saved.getId());
		assertEquals(saved, saved2);
		//assertNotNull(saved2);
		assertThrows(EntityUndeletableException.class, new Executable() {

			@Override
			public void execute() throws Throwable {
				// TODO Auto-generated method stub
				studentDao.delete(1L);
			}
			
		});
	}
	
	@Test
	void testGetBySubjectId() {
		Student s1 = new Student("Test","Jeden",4L);
		Student s2 = new Student("Test","Dva",4L);
		Student s3 = new Student("Test","Tri",4L);
		
		Student s1Saved = studentDao.save(s1);
		Student s2Saved = studentDao.save(s2);
		Student s3Saved = studentDao.save(s3);
		
		List<Student> studenti = studentDao.getBySubjectId(4L);
		assertEquals(studenti.size(),3);
		
		studentDao.delete(s1Saved.getId());
		studentDao.delete(s2Saved.getId());
		studentDao.delete(s3Saved.getId());

	}
}
