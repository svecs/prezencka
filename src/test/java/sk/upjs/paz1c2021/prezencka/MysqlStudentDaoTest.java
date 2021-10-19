package sk.upjs.paz1c2021.prezencka;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class MysqlStudentDaoTest {

	private StudentDao studentDao;
	
	public MysqlStudentDaoTest() {
		DaoFactory.INSTANCE.testing();
		studentDao = DaoFactory.INSTANCE.getStudentDao();
	}
	
	@BeforeEach
	void setUp() throws Exception {
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetAll() {
		List<Student> students = studentDao.getAll();
		assertNotNull(students);
		assertTrue(students.size() > 0);
		System.out.println(students);
	}

}
