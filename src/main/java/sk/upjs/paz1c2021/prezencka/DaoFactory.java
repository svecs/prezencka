package sk.upjs.paz1c2021.prezencka;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mysql.cj.jdbc.MysqlDataSource;

public enum DaoFactory {
	INSTANCE;
	
	private JdbcTemplate jdbcTemplate;
	private StudentDao studentDao;
	private boolean testing = false;
	
	public void testing() {
		testing = true;
	}
	
	public StudentDao getStudentDao() {
		if(studentDao == null) {
			studentDao = new MysqlStudentDao(jdbcTemplate);
		}
		return studentDao;
	}
	
	public JdbcTemplate getJdbcTemplate() {
		if(jdbcTemplate == null) {
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setUser("paz1c2021");
			dataSource.setPassword("javajesuper1");
			if(testing) {
				dataSource.setUrl("jdbc:mysql://localhost:3306/paz1c2021_prezencka_test?serverTimezone=Europe/Bratislava");
				jdbcTemplate = new JdbcTemplate(dataSource);
			}
			else {

				dataSource.setUrl("jdbc:mysql://localhost:3306/paz1c2021_prezencka?serverTimezone=Europe/Bratislava");
				jdbcTemplate = new JdbcTemplate(dataSource);
			}
		}
		return jdbcTemplate;
	}
	
	
}
